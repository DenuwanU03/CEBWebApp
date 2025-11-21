package com.ceb.controller;

import com.ceb.service.EventService;
import com.ceb.service.EventServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/admin/event/delete")
public class EventDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EventService service = new EventServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean ok = service.remove(id);
            resp.sendRedirect(req.getContextPath()+"/admin/events?deleted="+(ok?"1":"0"));
        } catch (Exception e) { throw new ServletException(e); }
    }
}
