package com.huc.service;

import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IStudentService {
    RespData queryStudent();

    RespData selectMessage(int studentId);

    void adminUpdateMessage(int studentId,String userName,String classes,String majorName,String academyName);

    void deleteStudentMessage(int studentId);
}
