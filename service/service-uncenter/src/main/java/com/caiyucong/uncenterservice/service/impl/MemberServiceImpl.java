package com.caiyucong.uncenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.MD5;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import com.caiyucong.uncenterservice.domain.Member;
import com.caiyucong.uncenterservice.domain.vo.LoginVo;
import com.caiyucong.uncenterservice.domain.vo.RegisterVo;
import com.caiyucong.uncenterservice.mapper.MemberMapper;
import com.caiyucong.uncenterservice.service.MemberService;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-27
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Integer getCount(String date) {
        return baseMapper.getCount(date);
    }

    @Override
    public String login(LoginVo loginVo) {
        val password = loginVo.getPassword();
        val mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile))
            throw new MyCustomException(20001, "账号或密码错误！");
        val query = new QueryWrapper<Member>();
        query.eq(Member.MOBILE, loginVo.getMobile());
        val mobileMember = getOne(query);
        if (mobileMember == null)
            throw new MyCustomException(20001, "手机未注册");
        if (!mobileMember.getPassword().equals(MD5.encrypt(loginVo.getPassword())))
            throw new MyCustomException(20001, "账号或密码错误！");
        if (mobileMember.getIsDisabled())
            throw new MyCustomException(20001, "该账户已封禁");
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        if (StringUtils.isEmpty(registerVo.getNickname()) || StringUtils.isEmpty(registerVo.getMobile())
                || StringUtils.isEmpty(registerVo.getPassword()) || StringUtils.isEmpty(registerVo.getCode())) {
            throw new MyCustomException(20001, "注册失败！");
        }
        if (count(new QueryWrapper<Member>().eq(Member.MOBILE, registerVo.getMobile())) > 0) {
            throw new MyCustomException(20001, "手机号已经存在");
        }
        String redisCode = redisTemplate.opsForValue().get(registerVo.getMobile());
        if (redisCode == null || !redisCode.equals(registerVo.getCode())) {
            throw new MyCustomException(20001, "验证码错误！");
        }

        val member = new Member();
        member.setPassword(MD5.encrypt(registerVo.getPassword()));
        member.setNickname(registerVo.getNickname());
        member.setMobile(registerVo.getMobile());
        member.setIsDisabled(false);
        member.setAvatar("https://cyc-duc-1301500198.cos.ap-guangzhou.myqcloud.com/images/test.jpg");
        return save(member);
    }

    @Override
    public Member getByOpenId(String openid) {
        return getOne(new QueryWrapper<Member>().eq(Member.OPENID, openid));
    }
}
