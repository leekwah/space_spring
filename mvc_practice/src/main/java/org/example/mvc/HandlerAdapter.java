package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean supports(Object handler); // 전달된 핸들러를 지원하는 어댑터를 확인

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception; // 지원시 핸들로 수행
}