package com.caiyucong.orderservice.service;

import com.caiyucong.orderservice.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-10
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);

}
