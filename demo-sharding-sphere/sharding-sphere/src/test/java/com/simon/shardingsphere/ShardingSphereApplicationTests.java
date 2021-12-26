package com.simon.shardingsphere;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simon.shardingsphere.fba.order.domain.FbaOrder;
import com.simon.shardingsphere.fba.order.service.FbaOrderService;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.domain.User;
import com.simon.shardingsphere.order.service.OrderService;
import com.simon.shardingsphere.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Runner.class)
@SpringBootTest
@Slf4j
class ShardingSphereApplicationTests {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Value("${test.name}")
    private String name;

    @Autowired
    private FbaOrderService fbaOrderService;

    @Test
    void getName() {
        System.out.println("name=" + name);
    }

    @Test
    void insertOrder(){
        Order order;
        for (int i = 0; i < 30; i++) {
            order = new Order();
            order.setUserId(Long.parseLong((i % 5) + ""));
            order.setBusinessNum("REV" + System.currentTimeMillis());
            order.setOrderType("REV");
            order.setCreatedBy(Long.parseLong((i) + ""));
            order.setCreatedDate(new Date());
            order.setModifiedBy(Long.parseLong((i%2) + ""));
            order.setModifiedDate(new Date());
            orderService.getBaseMapper().insert(order);
            log.info("{}插入成功{}",name,i);
        }
    }
    @Test
    void getUsers() {
        List<User> users = userService.listByMap(null);
        users.forEach(e -> {
            log.info(JSON.toJSONString(e));
        });
    }

    @Test
    void getOrderDate() {
        List<Order> orders = orderService.listByMap(null);
        orders.forEach(e -> {
            log.info(JSON.toJSONString(e));
        });
        List<Order> collect = orders.stream().sorted(Comparator.comparing(Order::getId)).collect(Collectors.toList());
        log.info("==============id排序后================");
        collect.forEach(e->{
            log.info(JSON.toJSONString(e));
        });
    }
    @Test
    void rangeOrders(){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.between("id",681784811551436801L,681784811874398208L);
        List<Order> list = orderService.list(wrapper);
        log.info("总计{}条",list.size());
        list.forEach(e->{
            log.info(JSON.toJSONString(e));
        });
    }
    @Test
    void pageOrders(){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.between("id",681784811320750081L, 681784811803095041L);
        List<Order> list = orderService.list(wrapper);
        log.info("总计{}条",list.size());
        list.forEach(e->{
            log.info(JSON.toJSONString(e));
        });
    }

    /**
     * 测试没配置fba_order会怎样
     * 结果： 报错
     */
    @Test
    void insertFbaOrder(){
        FbaOrder fbaOrder;
        for (int i = 0; i < 10; i++) {
            fbaOrder = new FbaOrder();
            //id没有配置主键生成规则
            fbaOrder.setId(Long.parseLong(i + 1000 + ""));
            fbaOrder.setUserId(Long.parseLong((i % 5) + ""));
            fbaOrder.setBusinessNum("REV" + System.currentTimeMillis());
            fbaOrder.setOrderType("REV");
            fbaOrder.setCreatedBy(Long.parseLong((i) + ""));
            fbaOrder.setCreatedDate(new Date());
            fbaOrder.setModifiedBy(Long.parseLong((i%2) + ""));
            fbaOrder.setModifiedDate(new Date());
            fbaOrderService.getBaseMapper().insert(fbaOrder);
            log.info("{}插入成功{}",name,i);
        }
    }
}
