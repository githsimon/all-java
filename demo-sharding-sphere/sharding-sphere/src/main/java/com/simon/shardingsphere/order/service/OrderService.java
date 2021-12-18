package com.simon.shardingsphere.order.service;

import com.simon.shardingsphere.order.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author simon
* @description 针对表【t_order_1】的数据库操作Service
* @createDate 2021-12-13 23:19:16
*/
public interface OrderService extends IService<Order> {

    String getOrders();

    String test();
}
