package com.huc.service.impl;

import com.huc.bean.Course;
import com.huc.bean.EntryForm;
import com.huc.bean.Teacher;
import com.huc.bean.User;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultCourse;
import com.huc.dao.CourseMapper;
import com.huc.dao.EntryFormMapper;
import com.huc.dao.TeacherMapper;
import com.huc.dao.UserMapper;
import com.huc.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public RespData queryMyCourse(int userId) {
        User user = userMapper.queryUserNameById(userId);
        EntryForm entryForm = entryFormMapper.queryEntryFormByUserName(user.getUsername());
        if (entryForm == null){
            return new RespData(RespCode.ENPTY);
        }
        if (entryForm.getChecked() == 1){//报名信息没有审核
            return new RespData(RespCode.WAIT_CHECK);
        }
        List<Course> courseList = courseMapper.queryMyCourse(entryForm.getMinorId());
        if (courseList.isEmpty()){
            //成功查询，但是数据为空
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,courseList);
    }

    @Override
    public RespData queryTeacherCourse(int id) {
        Teacher teacher = teacherMapper.queryTeacherId(id);
        if (teacher == null)
            return new RespData(RespCode.WRONG);
        List<Course> courses = courseMapper.queryTeacherCourse(teacher.getTeacherId());
        if (courses == null)
            return new RespData(RespCode.SUCCESS,null);
        if (courses.isEmpty())
            return new RespData(RespCode.SUCCESS,null);
        return new RespData(RespCode.SUCCESS,courses);
    }

    @Override
    public RespData queryAboutCourse(int minorId) {
        List<Course> courseList = courseMapper.queryMyCourse(minorId);
        if (courseList.isEmpty()){
            return new RespData(RespCode.WRONG);
        }
        List<ResultCourse> list = convertList(courseList);
        return new RespData(RespCode.SUCCESS,list);
    }

    private List<ResultCourse> convertList(List<Course> list){
        List<ResultCourse> courseList = new ArrayList<>();
        for (Course c: list) {
            ResultCourse course = new ResultCourse();
            course.setCourseId(c.getCourseId());
            course.setCourseName(c.getCourseName());
            course.setTimeTable(c.getTimeTable());
            Teacher teacher = teacherMapper.queryTeacherName(c.getTeacherId());
            course.setTeacherName(teacher.getUserName());
            courseList.add(course);
        }
        return courseList;
    }
}
