package com.huc.service;

import com.huc.bean.User;
import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    RespData login(String username, String password);

    RespData register(User user);

    RespData delUser(int userId);

    RespData adminQueryUser(int page);

    RespData adminUpdateUser(User user);

    RespData adminSelectById(int userId);

    void deleteUserMessage(int userId);
}
