package com.ceb.controller;

import com.ceb.dao.EventDAO;
import com.ceb.dao.EventDAOImpl;
import com.ceb.model.Event;
import com.ceb.service.CategoryService;
import com.ceb.service.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/admin/event/edit")
public class EventEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EventDAO eventDao = new EventDAOImpl();
    private final CategoryService catService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Event e = eventDao.findById(id).orElse(null);
            if (e == null) { resp.sendRedirect(req.getContextPath()+"/admin/events?notfound=1"); return; }
            req.setAttribute("event", e);
            req.setAttribute("categories", catService.list());
            req.getRequestDispatcher("/WEB-INF/views/admin/event-edit.jsp").forward(req, resp);
        } catch (Exception ex) { throw new ServletException(ex); }
    }
}
