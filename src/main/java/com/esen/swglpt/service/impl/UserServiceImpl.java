package com.esen.swglpt.service.impl;

import com.esen.swglpt.entity.User;
import com.esen.swglpt.mapper.UserMapper;
import com.esen.swglpt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByAccountAndPassword(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.findUserByAccountAndPassword(user);
    }

    @Override
    public User findUserByUserId(Long userId) {
        return userMapper.findUserByUserId(userId);
    }
}
