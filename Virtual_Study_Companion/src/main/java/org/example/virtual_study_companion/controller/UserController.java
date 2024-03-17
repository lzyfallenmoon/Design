package org.example.virtual_study_companion.controller;


import org.example.virtual_study_companion.pojo.Result;
import org.example.virtual_study_companion.pojo.User;
import org.example.virtual_study_companion.service.UserService;
import org.example.virtual_study_companion.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result register(String username, String password) {
        if (username != null && username.length() >= 5 && username.length() <= 16 &&
                password != null && password.length() >= 5 && password.length() <= 16) {
            //查询用户
            User u = userService.findByUserName(username);
            if (u == null) {
                //没有占用
                //注册
                userService.register(username, password);
                return Result.success();
            } else {
                return Result.error("用户名已被占用");
            }
        } else{
            return Result.error("参数名不合法");
        }
    }


    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        if (username != null && username.length() >= 5 && username.length() <= 16 &&
                password != null && password.length() >= 5 && password.length() <= 16) {
            //根据用户名查询用户
            User loginUser = userService.findByUserName(username);
            //判断用户是否存在
            if(loginUser==null) {
                return Result.error("用户名错误");
            }
            if (password.equals(loginUser.getPassword())) {
                //登陆成功

                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("username", loginUser.getUsername());
                String token = JwtUtil.genToken(claims);

                return Result.success(token);
            }
            return Result.error("密码错误");
        } else{
            return Result.error("参数名不合法");
        }
    }
}
