package com.huc.service;

import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IChartService {
    RespData statics(int minorId);

    RespData histogram(int minorId, String academyName);
}
