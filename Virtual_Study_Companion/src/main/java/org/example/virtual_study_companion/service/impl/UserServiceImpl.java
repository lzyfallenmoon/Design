package org.example.virtual_study_companion.service.impl;


import org.example.virtual_study_companion.mapper.UserMapper;
import org.example.virtual_study_companion.pojo.User;
import org.example.virtual_study_companion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //添加
        userMapper.add(username,password);
    }


}
