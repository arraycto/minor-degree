package com.huc.dao;

import com.huc.bean.User;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User login(String username, String password);

    void delUser(int id);

    List<User> adminQueryUser(int start);

    void updateUser(int userId,String username,String password,String name,String sex,int age,String phones,String email,String identity);

    User updateUserSelectById(int userId);

    User queryUserUsername(String name);

    User queryUserCardId(int userId);

    User queryUserNameById(int userId);

    User queryUserNameByUsername(String username);

    void deleteUserById(int userId);
}