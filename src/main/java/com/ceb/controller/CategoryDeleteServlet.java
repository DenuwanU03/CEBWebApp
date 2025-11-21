package com.ceb.controller;

import com.ceb.service.CategoryService;
import com.ceb.service.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/admin/category/delete")
public class CategoryDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean ok = service.remove(id);
            String q = ok ? "deleted=1" : "deleted=0";
            resp.sendRedirect(req.getContextPath() + "/admin/categories?" + q);
        } catch (Exception e) {
            // If a category is used by events, FK may block deletion
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                resp.sendRedirect(req.getContextPath() + "/admin/categories?inuse=1");
            } else {
                throw new ServletException(e);
            }
        }
    }
}
