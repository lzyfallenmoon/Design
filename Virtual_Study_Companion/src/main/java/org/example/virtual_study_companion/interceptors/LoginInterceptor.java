package org.example.virtual_study_companion.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.virtual_study_companion.pojo.Result;
import org.example.virtual_study_companion.utils.JwtUtil;
import org.example.virtual_study_companion.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            //if(redisToken==null) {
            //    throw new RuntimeException();
            //}
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
