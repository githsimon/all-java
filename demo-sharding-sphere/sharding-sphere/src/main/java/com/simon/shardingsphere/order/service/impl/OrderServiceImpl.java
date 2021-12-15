package com.simon.shardingsphere.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.demo.common.cache.RedisUtil;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.service.OrderService;
import com.simon.shardingsphere.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 大神
* @description 针对表【t_order_1】的数据库操作Service实现
* @createDate 2021-12-13 23:19:16
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    RedisUtil redisUtil;

    @Override
    public String getOrders() {
        List<Order> orders  = (List<Order>) redisUtil.getValueOps().get("orders");

        if(CollUtil.isEmpty(orders)){
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("id");
            queryWrapper.last("limit 10");
            orders = this.getBaseMapper().selectList(queryWrapper);
            if(CollUtil.isNotEmpty(orders)){
                redisUtil.getValueOps().set("orders",orders);
            }
        }
        return JSON.toJSONString(orders);
    }
}




