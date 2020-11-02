package com.huc.service.impl;

import com.huc.bean.Teacher;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.dao.TeacherMapper;
import com.huc.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    //管理员查询教师资料
    @Override
    public RespData queryTeacher() {
        List<Teacher> teacherList = teacherMapper.queryTeacher();
        return new RespData(RespCode.SUCCESS,teacherList);
    }
}
