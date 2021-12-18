package com.simon.shardingsphere.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_order_item
 */
@TableName(value ="t_order_item")
@Data
public class OrderItem implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private Long orderId;

    /**
     * 
     */
    private String item;

    /**
     * 
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}