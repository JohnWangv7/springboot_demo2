package com.esen.swglpt.service;

import com.esen.swglpt.entity.User;

public interface UserService {

    User findUserByAccountAndPassword(String username, String password);

    User findUserByUserId(Long userId);
}
