package com.simon.shardingsphere;

import com.simon.shardingsphere.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@RunWith(Runner.class)
@SpringBootTest
class ShardingSphereApplicationTests {

    @Autowired
    OrderService orderService;

    @Test
    void getOrder1Date() {
        List<Map<String, Object>> result = orderService.listMaps();
        System.out.println(result.size());
    }

}
