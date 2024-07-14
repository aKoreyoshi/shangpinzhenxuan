package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月14日, 12:31:14
 */
@Mapper
public interface OrderInfoMapper {
    OrderStatistics queryOrder(String yesterday);
}