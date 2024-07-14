package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.OrderInfoService;
import com.mac.spzx.model.dto.order.OrderStatisticsDto;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import com.mac.spzx.model.vo.order.OrderStatisticsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月14日, 12:29:40
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/admin/order/orderInfo")
public class OrderInfoController {

    private OrderInfoService orderInfoService;

    @Autowired
    public OrderInfoController(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @Operation(summary = "订单统计")
    @GetMapping("/statistics")
    public Result statistics(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo statisticsVos = orderInfoService.statistics(orderStatisticsDto);
        return Result.build(statisticsVos, ResultCodeEnum.SUCCESS);
    }
}