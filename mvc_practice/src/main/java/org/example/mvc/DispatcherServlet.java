package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/") // 어떤 경로를 입력하더라도, DispatcherServlet이 실행된다.
public class DispatcherServlet extends HttpServlet { // 서블릿이어야 하기 때문에, HttpServlet을 상속하도록 한다.
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping rmhm;

    @Override
    public void init() throws ServletException { // 서블릿이 만들어질 때, 바로 초기화 하도록 해두었다.
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started");

        try {
            Controller handler = rmhm.findHandler(request.getRequestURI()); //
            String viewName = handler.handleRequest(request, response);// handler에게 작업을 위임한다.

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response); // viewName으로 forward()

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
