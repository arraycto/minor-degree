package com.huc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huc.bean.Academy;
import com.huc.bean.Minor;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultMinor;
import com.huc.dao.AcademyMapper;
import com.huc.dao.MinorMapper;
import com.huc.service.IMinorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinorServiceImpl implements IMinorService {
    @Resource
    private MinorMapper minorMapper;
    @Resource
    private AcademyMapper academyMapper;

    @Override
    public RespData queryMinor(int page) {
        PageHelper.startPage(page, 3);//分页处理
        List<Minor> listMinor = minorMapper.queryMinor();//查询辅修数据
        List<ResultMinor> list = convertMinorList(listMinor);//将查询数据转换为我们所需要的字段
        PageInfo<ResultMinor> pageInfo = new PageInfo<ResultMinor>(list);
        return new RespData(RespCode.SUCCESS,pageInfo);
    }

    //数据转换，将辅修课程转换为所需数据
    private List<ResultMinor> convertMinorList(List<Minor> minorList){
        List<ResultMinor> list = new ArrayList<ResultMinor>();
        for (Minor minor : minorList) {
            Academy academy = academyMapper.queryAcademyById(minor.getAcademyId());//将辅修课程数据转换为前端需要的字段
            ResultMinor resultMinor = convertMinor(minor.getMinorId(), minor.getName(), academy.getAcademyName(),  minor.getCount());
            list.add(resultMinor);
        }
        return list;
    }

    //数据转换封装
    private ResultMinor convertMinor(int minorId,String name,String academyName,int count){
        ResultMinor resultMinor = new ResultMinor();
        resultMinor.setMinorId(minorId);
        resultMinor.setMinorName(name);
        resultMinor.setAcademy(academyName);
        resultMinor.setCount(count);
        return resultMinor;
    }
}
