package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zayn
 * * @date 2024/3/27/22:40
 */
@Data
@TableName("user_detail")
public class UserDetail {
    private Long id;
    @NotNull
    @TableField("user_id")
    private Long userId;
    private String nickname;
    private String avatar;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
