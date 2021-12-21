package com.simon.shardingsphere.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 单号
     */
    private String userName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 修改人
     */
    private Long modifiedBy;

    /**
     * 修改时间
     */
    private Date modifiedDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}