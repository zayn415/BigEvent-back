package com.zayn.bigevent;

import com.zayn.bigevent.pojo.UserDetail;
import com.zayn.bigevent.service.UserService;
import com.zayn.bigevent.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/28/01:08
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InfoTest {
    @Autowired
    private UserService userService;
    
    @Test
    public void test() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjbGFpbXMiOnsiZW1haWwiOiIxMjMxMjMxMjNAcXEuY29tIiwiaWQiOjE3NzI5OTE3ODc0NTE4NTQ4NTB9LCJleHAiOjE3MTE2MDEwODd9.aIbQY79YjsydQHRZ0TOWRXRGUVmeO2HwAmLPv4D-9dQ";
        Map<String, Object> claims = JWTUtil.parseToken(token);
        String id = claims.get("id").toString();
        UserDetail ud = userService.getUserInfo(id);
        System.out.println(ud);
    }
}
