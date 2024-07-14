package com.mac.spzx.manager.service;

import com.mac.spzx.model.dto.order.OrderStatisticsDto;
import com.mac.spzx.model.vo.order.OrderStatisticsVo;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月14日, 12:30:40
 */
public interface OrderInfoService {

    // 统计结果
    OrderStatisticsVo statistics(OrderStatisticsDto orderStatisticsDto);
}