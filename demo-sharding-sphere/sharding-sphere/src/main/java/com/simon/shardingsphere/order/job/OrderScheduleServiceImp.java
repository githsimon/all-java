package com.simon.shardingsphere.order.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class OrderScheduleServiceImp {

    @Autowired
    private OrderService orderService;

    private Long lastTime = System.currentTimeMillis();

    private boolean exeOnce = false;

    @Scheduled(cron = "0/3 * * * * ?")
    private void insertOrder() {
        if (exeOnce) {
            if ((System.currentTimeMillis() - lastTime) / 1000 < 60 * 60) {
                return;
            }
        }
        lastTime = System.currentTimeMillis();
        exeOnce = true;

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        Order order = new Order();
        order.setBusinessNum("REV" + System.currentTimeMillis());
        order.setOrderType("REV");
        order.setCreatedBy(1L);
        order.setCreatedDate(new Date());
        order.setModifiedBy(2L);
        order.setModifiedDate(new Date());

        boolean save = orderService.save(order);
        log.info("{}保持成功{}", save, JSON.toJSONString(order));
    }
}
