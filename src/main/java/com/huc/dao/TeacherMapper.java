package com.huc.dao;

import com.huc.bean.Teacher;

import java.util.List;

public interface TeacherMapper {
    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> queryTeacher();

    Teacher queryTeacherId(int userId);

    Teacher queryTeacherName(int teacherId);
}