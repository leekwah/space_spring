package com.kwah.springex.service;

import com.kwah.springex.dto.PageRequestDTO;
import com.kwah.springex.dto.PageResponseDTO;
import com.kwah.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    // List<TodoDTO> getAll();
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
