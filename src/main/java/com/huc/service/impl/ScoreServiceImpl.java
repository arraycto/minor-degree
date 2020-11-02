package com.huc.service.impl;

import com.huc.bean.Score;
import com.huc.bean.Teacher;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.dao.ScoreMapper;
import com.huc.dao.TeacherMapper;
import com.huc.service.IScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public RespData queryMyStudent(int userId) {
        Teacher teacher = teacherMapper.queryTeacherId(userId);
        List<Score> scoreList = scoreMapper.queryMyStudent(teacher.getTeacherId());
        if (scoreList.isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,scoreList);
    }
}
