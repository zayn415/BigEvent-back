package com.zayn.bigevent.service;

import com.zayn.bigevent.pojo.User;
import com.zayn.bigevent.pojo.UserDetail;

/**
 * @author zayn
 * * @date 2024/3/26/15:11
 */
public interface UserService {
    User findUserByEmail(String email);
    UserDetail getUserInfo(String userId);
    
    void register(String email, String password);
    
    String login(String email, String password);
    
    UserDetail updateInfo(UserDetail userDetail);
    
    void updateAvatar(String avatar);
    
    void updatePwd(String password);
}
