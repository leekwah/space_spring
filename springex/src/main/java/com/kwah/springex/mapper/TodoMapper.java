package com.kwah.springex.mapper;

import com.kwah.springex.domain.TodoVO;
import com.kwah.springex.dto.PageRequestDTO;

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
    // 수정하는 기능
    void update(TodoVO todoVO);
    // 페이징 기능
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    // 전체 페이지 수 처리
    int getCount(PageRequestDTO pageRequestDTO);
}
