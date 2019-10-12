package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByAccountAndPassword(User user);

    User findUserByUserId(Long userId);
}