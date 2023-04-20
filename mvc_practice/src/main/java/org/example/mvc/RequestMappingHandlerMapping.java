package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<String, Controller> mappings = new HashMap<>(); // K는 URL, V는 Controller

    void init() {
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath) { // uriPath와 동일한 Controller를 return 해주는 메서드
        return mappings.get(uriPath);
    }
}
