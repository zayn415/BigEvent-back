package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private Integer id;

    @Email(message = "邮箱格式不正确")
    private String email;
    
    @JsonIgnore
    private String password;
}