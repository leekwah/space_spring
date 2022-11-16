package com.kwah.springex.mapper;

import com.kwah.springex.domain.TodoVO;

public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO);
}
