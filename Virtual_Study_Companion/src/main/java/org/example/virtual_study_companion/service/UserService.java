package org.example.virtual_study_companion.service;

import org.example.virtual_study_companion.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {

    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
}
