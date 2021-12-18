package com.simon.shardingsphere.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 逻辑表
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class Order implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     *
     */
    private String businessNum;

    /**
     *
     */
    private String orderType;

    /**
     *
     */
    private String memo;

    /**
     *
     */
    private Long createdBy;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Long modifiedBy;

    /**
     *
     */
    private Date modifiedDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}