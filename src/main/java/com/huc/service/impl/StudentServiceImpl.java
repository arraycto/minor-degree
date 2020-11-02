package com.huc.service.impl;

import com.huc.bean.Student;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultStudent;
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

    /**
     * 管理员修改学生数据之前实现的查询数据，向前端input标签里提供数据
     * @param studentId
     * @return
     */
    @Override
    public RespData selectMessage(int studentId) {
        Student student = studentMapper.selectMessageById(studentId);
        ResultStudent resultStudent = ResultStudent(student);
        return new RespData(RespCode.SUCCESS,resultStudent);
    }

    /**
     * 管理员修改学生数据
     * @param studentId
     * @param userName
     * @param classes
     * @param majorName
     * @param academyName
     */
    @Override
    public void adminUpdateMessage(int studentId,String userName,String classes,String majorName,String academyName) {
        studentMapper.updateStudent(studentId,userName,classes,majorName,academyName);
    }

    /**
     * 删除学生信息
     * @param studentId
     */
    @Override
    public void deleteStudentMessage(int studentId) {
        studentMapper.deleteStudentMessage(studentId);
    }

    private ResultStudent ResultStudent(Student student){
        ResultStudent resultStudent = new ResultStudent();
        resultStudent.setStudentId(student.getStudentId());
        resultStudent.setStudentName(student.getUserName());
        resultStudent.setClasses(student.getClasses());
        resultStudent.setMajorName(student.getMajorName());
        resultStudent.setAcademyName(student.getAcademyName());
        return resultStudent;
    }
}
