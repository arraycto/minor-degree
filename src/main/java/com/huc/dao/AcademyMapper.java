package com.huc.dao;

import com.huc.bean.Academy;

import java.util.List;

public interface AcademyMapper {
    int insert(Academy record);

    int insertSelective(Academy record);

    Academy queryAcademyById(int id);

    List<Academy> queryAcademyIdAndName();

    Academy queryAcademyId(String academyName);
}