package com.simon.shardingsphere.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.shardingsphere.order.domain.OrderItem;
import com.simon.shardingsphere.order.service.OrderItemService;
import com.simon.shardingsphere.order.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author simon
* @description 针对表【t_order_item0】的数据库操作Service实现
* @createDate 2021-12-18 16:01:17
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




