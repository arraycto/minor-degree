package com.huc.service.impl;

import com.huc.bean.Student;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.dao.StudentMapper;
import com.huc.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public RespData queryStudent() {
        List<Student> studentList = studentMapper.queryStudent();
        return new RespData(RespCode.SUCCESS,studentList);
    }
}
