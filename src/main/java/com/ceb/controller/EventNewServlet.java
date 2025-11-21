package com.ceb.controller;

import com.ceb.model.Category;
import com.ceb.service.CategoryService;
import com.ceb.service.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/event/new")
public class EventNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Category> cats = categoryService.list();
            req.setAttribute("categories", cats);
            req.getRequestDispatcher("/WEB-INF/views/admin/event-form.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
