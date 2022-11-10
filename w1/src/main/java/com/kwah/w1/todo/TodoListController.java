package com.kwah.w1.todo;

import com.kwah.w1.todo.dto.TodoDTO;
import com.kwah.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");

        List<TodoDTO> dtoList = TodoService.INSTANCE.getList();

        req.setAttribute("list", dtoList); // setAttribute()를 통해서 "list"라는 이름으로 List<TodoDTO> 객체를 보관

        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        // getRequestDispatcher로 경로 설정, forward는 현재까지의 모든 Response 내용은 무시하고 JSP가 작성하는 내용만 브라우저로 전달하게 한다.
        // forward의 반대로는 include가 있다.
    }
}
