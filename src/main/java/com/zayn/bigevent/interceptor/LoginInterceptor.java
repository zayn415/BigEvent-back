package com.zayn.bigevent.interceptor;

import com.zayn.bigevent.utils.JWTUtil;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/27/21:02
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 重写preHandle方法，验证token是否有效
     * 将解析出的claims放入ThreadLocal缓存
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String tokenInRedis = operations.get(token);
            if (tokenInRedis == null) {
                // token不存在或已过期
                throw new Exception("Token does not exist or has expired");
            }
            Map<String, Object> claims = JWTUtil.parseToken(token);
            ThreadLocalUtil.set(claims);
            return token.equals(tokenInRedis) && JWTUtil.verifyToken(token);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
    
    /*
      重写afterCompletion方法，清除ThreadLocal缓存
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
