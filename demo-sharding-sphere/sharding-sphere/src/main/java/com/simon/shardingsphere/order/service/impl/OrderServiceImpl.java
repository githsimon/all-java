package com.simon.shardingsphere.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.demo.common.cache.RedisUtil;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.domain.OrderItem;
import com.simon.shardingsphere.order.domain.User;
import com.simon.shardingsphere.order.mapper.OrderMapper;
import com.simon.shardingsphere.order.service.OrderItemService;
import com.simon.shardingsphere.order.service.OrderService;
import com.simon.shardingsphere.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;

    @Override
    public String saveOrder() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        //随机查询一个用户
        List<User> users = userService.getBaseMapper().selectByMap(null);
        if(CollUtil.isNotEmpty(users)){
            int index = (int)(Math.random() * users.size());
            User user = users.get(index);
            order.setUserId(user.getId());
        }else{
            return "Fail";
        }
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        order.setBusinessNum("REV" + System.currentTimeMillis());
        order.setOrderType("REV");
        order.setCreatedBy(1L);
        order.setCreatedDate(new Date());
        order.setModifiedBy(2L);
        order.setModifiedDate(new Date());
        this.save(order);

        orderItem.setOrderId(order.getId());
        orderItem.setUserId(order.getUserId());
        orderItem.setCreatedBy(1L);
        orderItem.setCreatedDate(new Date());
        orderItem.setModifiedBy(1L);
        orderItem.setModifiedDate(new Date());
        orderItemService.save(orderItem);

        log.info("info46 insert success");
        return "OK";
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




