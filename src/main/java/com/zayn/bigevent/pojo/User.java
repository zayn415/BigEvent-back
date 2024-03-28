package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @NotNull
    private Long id;

    @Email
    private String email;
    
    @JsonIgnore
    private String password;
}