package com.huc.service;

import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IMinorService {
    RespData queryMinor(int page);
}
