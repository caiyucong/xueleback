package com.caiyucong.aclservice.mapper;

import com.caiyucong.aclservice.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
