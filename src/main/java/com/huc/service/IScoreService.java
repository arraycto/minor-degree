package com.huc.service;

import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IScoreService {
    RespData queryMyStudent(int userId);

    RespData queryScore();

    RespData queryMyScore(String username);
}
