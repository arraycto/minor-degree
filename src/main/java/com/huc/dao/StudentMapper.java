package com.huc.dao;

import com.huc.bean.Student;

import java.util.List;

public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

    List<Student> queryStudent();

    Student selectMessageById(int studentId);

    void updateStudent(int studentId,String userName,String classes,String majorName,String academyName);

    void deleteStudentMessage(int userId);
}