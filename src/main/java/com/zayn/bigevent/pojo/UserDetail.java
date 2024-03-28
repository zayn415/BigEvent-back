package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author zayn
 * * @date 2024/3/27/22:40
 */
@Data
@TableName("user_detail")
public class UserDetail {
    @NotNull
    private Long id;
    
    @NotNull
    @TableField("user_id")
    private Long userId;
    private String nickname;
    private String avatar;
    private String phone;
    @NotNull
    private Date createTime;
    @NotNull
    private Date updateTime;
}
