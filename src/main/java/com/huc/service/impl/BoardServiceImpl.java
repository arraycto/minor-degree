package com.huc.service.impl;

import com.huc.bean.Board;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.dao.BoardMapper;
import com.huc.service.IBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BoardServiceImpl implements IBoardService {
    @Resource
    private BoardMapper boardMapper;

    //查询通知
    @Override
    public RespData queryBoard() {
        List<Board> boardList = boardMapper.queryBoard();
        if (boardList.isEmpty()){
            return new RespData(RespCode.SUCCESS,null);
        }
        return new RespData(RespCode.SUCCESS,boardList);
    }
}
