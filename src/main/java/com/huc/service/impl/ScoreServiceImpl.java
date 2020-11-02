package com.huc.service.impl;

import com.huc.bean.Course;
import com.huc.bean.Score;
import com.huc.bean.Teacher;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultScore;
import com.huc.dao.CourseMapper;
import com.huc.dao.EntryFormMapper;
import com.huc.dao.ScoreMapper;
import com.huc.dao.TeacherMapper;
import com.huc.service.IScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements IScoreService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private CourseMapper courseMapper;

    @Override
    public RespData queryMyStudent(int userId) {
        Teacher teacher = teacherMapper.queryTeacherId(userId);
        List<Score> scoreList = scoreMapper.queryMyStudent(teacher.getTeacherId());
        if (scoreList.isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,scoreList);
    }

    @Override
    public RespData queryScore() {
        List<Score> scores = scoreMapper.queryScore();
        if (scores.isEmpty())
            return new RespData(RespCode.WRONG);
        List<ResultScore> scoreList = convertScoreList(scores);
        return new RespData(RespCode.SUCCESS,scoreList);
    }

    private List<ResultScore> convertScoreList(List<Score> scoreList){
        List<ResultScore> list = new ArrayList();
        for (int i = 0;i < scoreList.size();i ++){
            ResultScore resultScore = new ResultScore();
            Score score = scoreList.get(i);
            resultScore.setUserName(score.getUsername());
            resultScore.setName(score.getName());
            Course course = courseMapper.queryCourseName(score.getCourseId());
            resultScore.setCourseName(course.getCourseName());
            resultScore.setMinorName(score.getMinorName());
            resultScore.setScore(score.getScore());
            list.add(resultScore);
        }
        return list;
    }

    @Override
    public RespData queryMyScore(String username) {
        return null;
    }
}
