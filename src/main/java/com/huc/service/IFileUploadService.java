package com.huc.service;

import com.huc.common.response.RespData;
import com.huc.common.result.FileResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface IFileUploadService {

    FileResult uploadFile(MultipartFile file,String userName);

    boolean isImg(MultipartFile file) throws IOException;

    RespData check(int id);

    RespData downFile(String filename, HttpServletResponse res);
}
