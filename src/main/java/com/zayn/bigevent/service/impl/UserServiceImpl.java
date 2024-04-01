package com.zayn.bigevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zayn.bigevent.mapper.UserDetailMapper;
import com.zayn.bigevent.mapper.UserMapper;
import com.zayn.bigevent.pojo.User;
import com.zayn.bigevent.pojo.UserDetail;
import com.zayn.bigevent.service.UserService;
import com.zayn.bigevent.utils.JWTUtil;
import com.zayn.bigevent.utils.Md5Util;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/26/15:12
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email", email);
        log.info("查询用户：{}", email);
        return userMapper.selectOne(qw);
    }
    
    @Override
    public UserDetail getUserInfo(String userId) {
        QueryWrapper<UserDetail> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        log.info("查询用户信息, userId: {}", userId);
        return userDetailMapper.selectOne(qw);
    }
    
    @Override
    public void register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(Md5Util.getMD5String(password));
        log.info("注册用户：{}", user);
        userMapper.insert(user);
    }
    
    @Override
    public String login(String email, String password) {
        User user = findUserByEmail(email);
        if(user == null) {
            log.info("邮箱不存在：{}", email);
            return "Email does not exist";
        }
        if (!user.getPassword().equals(Md5Util.getMD5String(password))) {
            log.info("密码错误");
            return "Password error";
        }
        
        Map<String, Object> claims = Map.of("id", user.getId(), "email", email);
        log.info("用户登录：{}", email);
        return JWTUtil.createToken(claims);
    }
    
    @Override
    public void updatePwd(String password) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        log.info("修改密码：{}", id);
        userMapper.updatePassword(id, Md5Util.getMD5String(password));
    }
    
    @Override
    public UserDetail updateInfo(UserDetail userDetail) {
        try {
            Map<String, Object> claims = ThreadLocalUtil.get();
            Integer id = (Integer) claims.get("id");
            log.info("更新用户信息：{}", id);
            userDetail.setUpdateTime(LocalDateTime.now());
            userDetailMapper.update(userDetail, new QueryWrapper<UserDetail>().eq("user_id", id));
            return userDetailMapper.selectOne(new QueryWrapper<UserDetail>().eq("user_id", id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void updateAvatar(String avatar) {
        try {
            Map<String, Object> claims = ThreadLocalUtil.get();
            Integer user_id = (Integer) claims.get("id");
            userDetailMapper.updateAvatar(user_id, avatar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
