package com.huc.dao;

import com.huc.bean.Minor;

import java.util.List;

public interface MinorMapper {
    int insert(Minor record);

    int insertSelective(Minor record);

    List<Minor> queryMinor();

    Minor queryCount(int minorId);

    void updateCount(int count,int minorId);

    Minor queryMinorName(int minorId);
}