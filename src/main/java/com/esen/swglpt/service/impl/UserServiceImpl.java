package com.esen.swglpt.service.impl;

import com.esen.swglpt.common.utils.SHA256Util;
import com.esen.swglpt.entity.User;
import com.esen.swglpt.mapper.UserMapper;
import com.esen.swglpt.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }

    @Override
    public void insertSelective(User user) {
        if (StringUtils.isEmpty(user.getSalt())) {
            String salt = UUID.randomUUID().toString();
            user.setSalt(salt);
        }
        user.setPassword(SHA256Util.sha256(user.getPassword(), user.getSalt()));
        user.setStatus(1);
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
    }
}
