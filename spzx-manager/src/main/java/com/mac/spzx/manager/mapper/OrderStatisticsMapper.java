package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.order.OrderStatisticsDto;
import com.mac.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月14日, 12:32:13
 */
@Mapper
public interface OrderStatisticsMapper {
    List<OrderStatistics> statistics(OrderStatisticsDto orderStatisticsDto);

    void insert(OrderStatistics orderStatistics);
}