package com.huc.service.impl;

import com.huc.bean.Major;
import com.huc.dao.MajorMapper;
import com.huc.service.IMajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MajorServiceImpl implements IMajorService {
    @Resource
    private MajorMapper majorMapper;

//    @Override
//    public Major selectMajorId(String majorName) {
//       Major major = majorMapper.selectIdByMajorName(majorName);//查询主专业,仅查询id
//       return major;
//    }
}
