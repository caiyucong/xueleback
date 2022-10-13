package com.caiyucong.uncenterservice.mapper;

import com.caiyucong.uncenterservice.domain.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-27
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer getCount(String date);
}
