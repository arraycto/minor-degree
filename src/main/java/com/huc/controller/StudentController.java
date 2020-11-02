package com.huc.controller;

import com.huc.common.response.RespData;
import com.huc.common.result.ResultStudent;
import com.huc.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class StudentController {
    @Resource
    private IStudentService studentService;

    /**
     * 查询全部学生
     * @return
     */
    @RequestMapping("admin/queryStudent")
    @ResponseBody
    public RespData queryStudent(){
        RespData respData = studentService.queryStudent();
        return respData;
    }

    @RequestMapping("admin/selectStudent")
    public String selectStudent(int studentId, Model model){
        RespData respData = studentService.selectMessage(studentId);
        model.addAttribute("student",(ResultStudent)respData.getData());
        return "admin/update_student";
    }

    @RequestMapping("admin/updateStudent")
    public String updateStudent(int student_id,String user_name,String classes,String major_name,String academy_name){
        studentService.adminUpdateMessage(student_id,user_name,classes,major_name,academy_name);
        return "admin/success";
    }

    @RequestMapping("admin/deleteStudent")
    public String deleteStudent(int studentId){
        studentService.deleteStudentMessage(studentId);
        return "admin/success";
    }
}
