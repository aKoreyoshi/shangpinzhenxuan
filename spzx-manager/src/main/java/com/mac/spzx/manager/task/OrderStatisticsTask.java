package com.mac.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.mac.spzx.manager.mapper.OrderInfoMapper;
import com.mac.spzx.manager.mapper.OrderStatisticsMapper;
import com.mac.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Koreyoshi
 * @description: 定时任务的思路：统计前一天的订单金额、单数等，然后存入到统计表中，每天定时执行
 * @version: 1.0
 * @date: 2024年07月14日, 12:26:01
 */
@Component
@Slf4j
public class OrderStatisticsTask {

    private OrderInfoMapper orderInfoMapper;
    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    public OrderStatisticsTask(OrderInfoMapper orderInfoMapper,
                               OrderStatisticsMapper orderStatisticsMapper) {
        this.orderInfoMapper = orderInfoMapper;
        this.orderStatisticsMapper = orderStatisticsMapper;
    }

        @Scheduled(cron = "0 0 2 * * ?")  // 定义定时任务，使用@Scheduled注解指定调度时间表达式
//    @Scheduled(cron = "0 0/1 * * * ?")  // 测试
    public void orderStatistics() {
        // 获取到前一天的日期 昨天
        String yesterday = DateUtil.offsetDay(new Date(), -1)
                .toString(new SimpleDateFormat("yyyy-MM-dd"));
        log.info("定时任务开始执行，时间：{}", new Date());
        // 调用mapper接口，查询昨天的订单数据
        OrderStatistics orderStatistics = orderInfoMapper.queryOrder(yesterday);
        // 将结果插入到统计表中
        String string = DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd");
        if (!StrUtil.isEmpty(string)) {
            orderStatisticsMapper.insert(orderStatistics);
            log.info("定时任务执行结束，时间：{}", new Date());
        } else {
            log.info("定时任务执行结束，没有数据插入。时间：{}", new Date());
        }
    }
}