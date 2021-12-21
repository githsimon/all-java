package com.simon.shardingsphere;

import com.alibaba.fastjson.JSON;
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

import java.util.List;

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

    @Test
    void getName() {
        System.out.println("name=" + name);
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
    }
}
