package com.huc.controller;

import com.huc.common.response.RespData;
import com.huc.service.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TeacherController {
    @Resource
    private ITeacherService teacherService;

    //以json数据格式返回
    @RequestMapping("admin/queryTeacher")
    @ResponseBody
    public RespData queryTeacher(){
        RespData respData = teacherService.queryTeacher();
        return respData;
    }
}
