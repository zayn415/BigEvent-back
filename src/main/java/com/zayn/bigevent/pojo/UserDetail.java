package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zayn
 * * @date 2024/3/27/22:40
 */
@Data
@TableName("user_detail")
public class UserDetail {
    private Integer id;
    
    @NotNull
    @TableField("user_id")
    private Integer userId;
    
    private String nickname;
    
    private String avatar;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
