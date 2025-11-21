package com.ceb.controller;

import com.ceb.exceptions.ValidationException;
import com.ceb.service.CategoryService;
import com.ceb.service.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/admin/category/create")
public class CategoryCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        try {
            service.add(name, description);
            resp.sendRedirect(req.getContextPath() + "/admin/categories?added=1");
        } catch (ValidationException ve) {
            req.setAttribute("error", ve.getMessage());
            req.getRequestDispatcher("/admin/categories").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
