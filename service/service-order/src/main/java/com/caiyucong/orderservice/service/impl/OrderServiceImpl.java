package com.caiyucong.orderservice.service.impl;

import com.caiyucong.orderservice.client.CourseClient;
import com.caiyucong.orderservice.client.UCenterClient;
import com.caiyucong.orderservice.domain.Order;
import com.caiyucong.orderservice.mapper.OrderMapper;
import com.caiyucong.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caiyucong.orderservice.utils.OrderNoUtil;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private CourseClient courseClient;

    @Resource
    private UCenterClient uCenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        val courseInfo = courseClient.getCourseInfo(courseId);
        val userInfo = uCenterClient.getUserInfo(memberId);
        val order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfo.getTitle());
        order.setCourseCover(courseInfo.getCover());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());
        order.setMemberId(memberId);
        order.setNickname(userInfo.getNickname());
        order.setMobile(userInfo.getMobile());
        order.setPayType(1);
        order.setStatus(0);
        if (save(order)) {
            return order.getOrderNo();
        }
        return null;

    }

}
