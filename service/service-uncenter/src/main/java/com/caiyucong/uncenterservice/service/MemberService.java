package com.caiyucong.uncenterservice.service;

import com.caiyucong.uncenterservice.domain.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caiyucong.uncenterservice.domain.vo.LoginVo;
import com.caiyucong.uncenterservice.domain.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-27
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    boolean register(RegisterVo registerVo);

    Member getByOpenId(String openid);

    Integer getCount(String date);

}
