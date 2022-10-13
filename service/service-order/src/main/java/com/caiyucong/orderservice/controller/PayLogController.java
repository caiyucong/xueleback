package com.caiyucong.orderservice.controller;


import com.caiyucong.commonutils.R;
import com.caiyucong.orderservice.service.PayLogService;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-10-11
 */
@RestController
@RequestMapping("/orderservice/pay-log")
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    @GetMapping("{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        return R.ok().data(payLogService.createNative(orderNo));
    }

    @GetMapping("status/{orderNo}")
    public R status(@PathVariable String orderNo) {
        val map = payLogService.queryPayStatus(orderNo);
        if (map == null)
            return R.error().setMessage("支付除出错了");
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrderStatus(map);
            return R.ok().setMessage("支付成功");
        }

        return R.ok().setMessage("支付中").setCode(25000);
    }

}

