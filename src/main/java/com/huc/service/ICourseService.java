package com.huc.service;

import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface ICourseService {
    RespData queryMyCourse(int userId);

    RespData queryTeacherCourse(int id);

    RespData queryAboutCourse(int minorId);
}
