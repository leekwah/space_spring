package com.kwah.springex.controller;

import com.kwah.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    @RequestMapping("/list")
    public void list() {
        log.info("todo list .....");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("todo register .....");
    }

    @PostMapping("/register")
    public void registerPost(TodoDTO todoDTO) {
        log.info("POST todo register .....");
        log.info(todoDTO);
    }
}
