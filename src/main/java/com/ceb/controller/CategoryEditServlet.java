package com.ceb.controller;

import com.ceb.dao.CategoryDAO;
import com.ceb.dao.CategoryDAOImpl;
import com.ceb.model.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/admin/category/edit")
public class CategoryEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryDAO dao = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Category c = dao.findById(id).orElse(null);
            if (c == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/categories?notfound=1");
                return;
            }
            req.setAttribute("cat", c);
            req.getRequestDispatcher("/WEB-INF/views/admin/category-edit.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
