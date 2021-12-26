package com.simon.shardingsphere.fba.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName 逻辑表 t_fba_order
 */
@TableName(value ="t_fba_order")
@Data
public class FbaOrder implements Serializable {
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