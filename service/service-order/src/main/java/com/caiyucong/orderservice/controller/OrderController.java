package com.caiyucong.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.R;
import com.caiyucong.orderservice.domain.Order;
import com.caiyucong.orderservice.service.OrderService;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/orderservice/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        val order = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().putData("orderId", order);
    }

    @GetMapping("{orderId}")
    public R getOrder(@PathVariable String orderId) {
        val queryWrapper = new QueryWrapper<Order>();
        queryWrapper.eq(Order.ORDER_NO, orderId);
        return R.ok().putData("order", orderService.getOne(queryWrapper));
    }

    @GetMapping("buy/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable String courseId, @PathVariable String memberId) {
        val queryWrapper = new QueryWrapper<Order>();
        queryWrapper.eq(Order.COURSE_ID, courseId);
        queryWrapper.eq(Order.MEMBER_ID, memberId);
        queryWrapper.eq(Order.STATUS, 1);
        queryWrapper.last("limit 1");
        return orderService.count(queryWrapper) > 0;
    }
}

