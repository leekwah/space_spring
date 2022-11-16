package com.kwah.springex.mapper;

import com.kwah.springex.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    // 현재 시간을 아는 기능
    String getTime();
    // 등록기능
    void insert(TodoVO todoVO);
    // 리스트를 조회하는 기능
    List<TodoVO> selectAll();
    // 조회하는 기능
    TodoVO selectOne(Long tno);
    // 삭제하는 기능
    void delete(Long tno);
    void update(TodoVO todoVO);
}
