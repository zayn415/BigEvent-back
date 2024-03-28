package com.zayn.bigevent.controller;

import com.zayn.bigevent.annotations.Password;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.pojo.UserDetail;
import com.zayn.bigevent.service.UserService;
import com.zayn.bigevent.utils.JWTUtil;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/26/15:09
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public Result register(@Email String email, @Password String password) {
        if (userService.findUserByEmail(email) != null) {
            return Result.error("Email already exists");
        }
        userService.register(email, password);
        return Result.success();
    }
    
    @PostMapping("/login")
    public Result login(@Email String email, @Password String password) {
        try {
            String token = userService.login(email, password);
            return Result.success(token);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/info")
    public Result getUserInfo(@RequestHeader(name = "Authorization") String token) {
        try {
            Map<String, Object> claims = ThreadLocalUtil.get();
            String id = claims.get("id").toString();
            UserDetail ud = userService.getUserInfo(id);
            return Result.success(ud);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
