package com.kwah.w1.todo.service;

import com.kwah.w1.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {
    INSTANCE; // 객체를 하나만 생성해서 사용하는 패턴, 즉 싱글톤 패턴

    public void register(TodoDTO todoDTO) {
        System.out.println("DEBUG..." + todoDTO);
    }

    public List<TodoDTO> getList() {

        List<TodoDTO> todoDTOS = IntStream.range(0, 10).mapToObj(i -> { // 10개의 TodoDTO 객체를 만들어서 반환하도록 구성
            TodoDTO dto = new TodoDTO();
            dto.setTno((long)i);
            dto.setTitle("Todo..." + i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());

        return todoDTOS;
    }

    public TodoDTO get(Long tno) {

        TodoDTO dto = new TodoDTO();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);

        return dto;
    }
}
