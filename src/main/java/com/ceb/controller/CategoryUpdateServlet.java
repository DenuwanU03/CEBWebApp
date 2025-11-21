package com.ceb.controller;

import com.ceb.exceptions.ValidationException;
import com.ceb.service.CategoryService;
import com.ceb.service.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/admin/category/update")
public class CategoryUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            service.update(id, name, description);
            resp.sendRedirect(req.getContextPath() + "/admin/categories?updated=1");
        } catch (ValidationException ve) {
            req.setAttribute("error", ve.getMessage());
            // return to the same edit page
            req.getRequestDispatcher("/admin/category/edit?id=" + req.getParameter("id")).forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
