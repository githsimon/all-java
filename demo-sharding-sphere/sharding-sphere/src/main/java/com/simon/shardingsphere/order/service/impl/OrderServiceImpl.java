package com.simon.shardingsphere.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.shardingsphere.order.domain.Order;
import com.simon.shardingsphere.order.service.OrderService;
import com.simon.shardingsphere.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 大神
* @description 针对表【t_order_1】的数据库操作Service实现
* @createDate 2021-12-13 23:19:16
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




