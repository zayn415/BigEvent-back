package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * @author zayn
 * * @date 2024/3/27/22:40
 */
@Data
@TableName("user_detail")
public class UserDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    @NotNull
    @TableField("user_id")
    private Integer userId;
    
    private String nickname;
    
    @URL
    private String avatar;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
