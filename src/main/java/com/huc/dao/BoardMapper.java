package com.huc.dao;

import com.huc.bean.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardMapper {
    @Select({"select * from tb_board"})
    List<Board> queryBoard();
}
