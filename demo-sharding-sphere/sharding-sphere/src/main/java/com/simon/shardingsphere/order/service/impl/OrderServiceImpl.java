package com.simon.shardingsphere.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.demo.common.cache.RedisUtil;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.service.OrderService;
import com.simon.shardingsphere.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author simon
* @description 针对表【t_order_1】的数据库操作Service实现
* @createDate 2021-12-13 23:19:16
*/
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    RedisUtil redisUtil;

    @Override
    public String test() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setBusinessNum("REV" + System.currentTimeMillis());
        order.setOrderType("REV");
        order.setCreatedBy(1L);
        order.setCreatedDate(new Date());
        order.setModifiedBy(2L);
        order.setModifiedDate(new Date());
        int insert = this.baseMapper.insert(order);
        log.info("info46 insert is {}",insert);
        return String.valueOf(insert);
    }

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




