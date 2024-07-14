package com.mac.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mac.spzx.manager.mapper.OrderStatisticsMapper;
import com.mac.spzx.manager.service.OrderInfoService;
import com.mac.spzx.model.dto.order.OrderStatisticsDto;
import com.mac.spzx.model.entity.order.OrderStatistics;
import com.mac.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月14日, 12:30:47
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    public OrderInfoServiceImpl(OrderStatisticsMapper orderStatisticsMapper) {
        this.orderStatisticsMapper = orderStatisticsMapper;
    }

    /**
     * 订单统计结果
     *
     * @param orderStatisticsDto
     * @return OrderStatisticsVo
     */
    @Override
    public OrderStatisticsVo statistics(OrderStatisticsDto orderStatisticsDto) {
        // 查询数据
        List<OrderStatistics> orderStatistics = orderStatisticsMapper.statistics(orderStatisticsDto);
        // 对数据抽离封装
        // 日期
        List<String> dateList = orderStatistics.stream()
                .map(item -> DateUtil.format(item.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());

        // 订单数
        List<Integer> CountList = orderStatistics.stream()
                .map(item -> item.getTotalNum())
                .collect(Collectors.toList());

        // 订单金额
        List<BigDecimal> amountList  = orderStatistics.stream()
                .map(OrderStatistics::getTotalAmount)
                .collect(Collectors.toList());
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setCountList(CountList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}