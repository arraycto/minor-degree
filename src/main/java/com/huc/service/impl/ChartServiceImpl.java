package com.huc.service.impl;

import com.huc.bean.Academy;
import com.huc.bean.Major;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultHistogramData;
import com.huc.common.result.ResultPieData;
import com.huc.dao.AcademyMapper;
import com.huc.dao.EntryFormMapper;
import com.huc.dao.MajorMapper;
import com.huc.service.IChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartServiceImpl implements IChartService {
    @Resource
    private AcademyMapper academyMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private EntryFormMapper entryFormMapper;

    //生成饼图
    @Override
    public RespData statics(int minorId) {
        List<Academy> academyList = academyMapper.queryAcademyIdAndName();
        List<ResultPieData> list = new ArrayList<>();
        for (int i = 0;i < academyList.size();i++){
            ResultPieData resultPieData = new ResultPieData();
            List<Major> majors = majorMapper.queryMajorByAcademy(academyList.get(i).getAcademyId());
            int integer = 0;
            for (int j = 0;j < majors.size();j ++){
                integer += entryFormMapper.queryEntryForm(majors.get(j).getMajorName(),minorId);
            }
            resultPieData.setName(academyList.get(i).getAcademyName());
            resultPieData.setValue(integer);
            list.add(resultPieData);
        }
        return new RespData(RespCode.SUCCESS,list);
    }

    //生成柱状图
    @Override
    public RespData histogram(int minorId, String academyName) {
        ResultHistogramData resultHistogramData = new ResultHistogramData();
        Academy academy = academyMapper.queryAcademyId(academyName);
        List<Major> majorList = majorMapper.queryMajorByAcademy(academy.getAcademyId());
        String[] x = new String[majorList.size()];
        int[] y = new int[majorList.size()];
        for (int i = 0;i < majorList.size();i ++){
            x[i] = majorList.get(i).getMajorName();
            Integer integer = entryFormMapper.queryEntryFormByMajorNameAndMinorId(majorList.get(i).getMajorName(), minorId);
            y[i] = integer;
        }
        resultHistogramData.setDataX(x);
        resultHistogramData.setDataY(y);
        return new RespData(RespCode.SUCCESS,resultHistogramData);
    }
}
