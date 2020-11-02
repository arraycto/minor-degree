package com.huc.dao;

import com.huc.bean.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface ScoreMapper {
    @Select({"select username,name,minor_name from tb_score s,tb_course c where c.course_id = s.course_id and teacher_id = #{teacherId}"})
    List<Score> queryMyStudent(int teacherId);

    @Select({"select * from tb_score limit 0,10"})
    List<Score> queryScore();
}
