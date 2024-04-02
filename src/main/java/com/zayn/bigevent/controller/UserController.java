package com.zayn.bigevent.controller;

import com.zayn.bigevent.annotations.Password;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.pojo.UpdatePasswordRequest;
import com.zayn.bigevent.pojo.User;
import com.zayn.bigevent.pojo.UserDetail;
import com.zayn.bigevent.service.UserService;
import com.zayn.bigevent.utils.Md5Util;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.URL;
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
    
    /*
        * 用户注册
     */
    @PostMapping("/register")
    public Result register(@Email String email, @Password String password) {
        if (userService.findUserByEmail(email) != null) {
            return Result.error("Email already exists");
        }
        userService.register(email, password);
        return Result.success();
    }
    
    /*
        * 用户登录
     */
    @PostMapping("/login")
    public Result login(@Email String email, @Password String password) {
        try {
            String token = userService.login(email, password);
            return Result.success(token);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /*
        * 获取用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo() {
        try {
            Map<String, Object> claims = ThreadLocalUtil.get();
            String id = claims.get("id").toString();
            UserDetail ud = userService.getUserInfo(id);
            return Result.success(ud);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /*
        * 更新用户信息
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody @Valid UserDetail userDetail) {
        try {
            UserDetail ud = userService.updateInfo(userDetail);
            return Result.success(ud);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /*
        * 更新用户头像
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarURL) {
        try {
            userService.updateAvatar(avatarURL);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /*
        * 更新用户密码
        * @param pwds 旧密码、新密码和确认密码
     */
    @PatchMapping("/updatePwd")
    public Result updatePassword(@RequestBody @Valid UpdatePasswordRequest pwds) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String email = claims.get("email").toString();
        User loginUser = userService.findUserByEmail(email);
        if (!Md5Util.getMD5String(pwds.getOldPwd()).equals(loginUser.getPassword())) {
            return Result.error("The old password is incorrect");
        }
        if (!pwds.getNewPwd().equals(pwds.getCfmPwd())) {
            return Result.error("The two passwords are inconsistent");
        }
        userService.updatePwd(pwds.getNewPwd());
        return Result.success();
    }
}
