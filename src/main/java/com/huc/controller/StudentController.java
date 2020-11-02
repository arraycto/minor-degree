package com.huc.controller;

import com.huc.common.response.RespData;
import com.huc.service.IStudentService;
import org.springframework.stereotype.Controller;
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
}
