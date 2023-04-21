package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<HandlerKey, Controller> mappings = new HashMap<>(); // K는 URL, V는 Controller

    void init() {
        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
//        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
    }

    public Controller findHandler(HandlerKey handlerKey) { // uriPath와 동일한 Controller를 return 해주는 메서드
        return mappings.get(handlerKey);
    }
}
