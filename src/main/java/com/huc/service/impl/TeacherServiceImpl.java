package com.huc.service.impl;

import com.huc.bean.Teacher;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultTeacher;
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

    /**
     * 管理员修改老师数据之前查询出数据，为前端input框里提供原始数据
     * @param teacherId
     * @return
     */
    @Override
    public RespData selectTeacherById(int teacherId) {
        Teacher teacher = teacherMapper.selectTeacherById(teacherId);
        ResultTeacher resultTeacher = ResultTeacher(teacher);
        return new RespData(RespCode.SUCCESS,resultTeacher);
    }

    /**
     * 管理员修改老师数据
     * @param teacherId
     * @param userName
     * @param academyName
     * @param professional
     */
    @Override
    public void adminUpdateTeacherMessage(int teacherId, String userName, String academyName, String professional) {
        teacherMapper.updateTeacher(teacherId,userName,academyName,professional);
    }

    @Override
    public void deleteTeacherMessage(int userId) {
        teacherMapper.deleteTeacherMessage(userId);
    }

    private ResultTeacher ResultTeacher(Teacher teacher){
        ResultTeacher teachers = new ResultTeacher();
        teachers.setTeacherId(teacher.getTeacherId());
        teachers.setTeacherProfessional(teacher.getProfessional());
        teachers.setTeacherName(teacher.getUserName());
        teachers.setAcademyName(teacher.getAcademyName());
        return teachers;
    }
}
