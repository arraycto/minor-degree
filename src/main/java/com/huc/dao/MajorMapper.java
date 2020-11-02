package com.huc.dao;

import com.huc.bean.Major;

import java.util.List;

public interface MajorMapper {
    int insert(Major record);

    int insertSelective(Major record);

    Major queryMajorByName(String majorName);

    List<Major> queryMajorByAcademy(int academyId);
}