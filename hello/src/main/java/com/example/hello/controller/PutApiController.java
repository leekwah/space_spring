package com.example.hello.controller;

import com.example.hello.dto.PutPostRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    public void put(@RequestBody PutPostRequestDTO requestDTO, @PathVariable(name = "userId") Long id) {
        System.out.println(id);
        System.out.println(requestDTO);
    }

}
