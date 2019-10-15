package com.esen.swglpt.service;

import com.esen.swglpt.entity.User;

import java.util.List;

public interface UserService {

    User findUserByAccountAndPassword(String username, String password);

    User findUserByUserId(Long userId);

    User selectUserByName(String username);

    List<User> list();

    void insertSelective(User user);
}
