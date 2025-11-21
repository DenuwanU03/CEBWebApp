package com.ceb.controller;

import com.ceb.model.Event;
import com.ceb.service.EventService;
import com.ceb.service.EventServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/events")
public class EventListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EventService service = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Event> events = service.list();
            req.setAttribute("events", events);
            req.getRequestDispatcher("/WEB-INF/views/admin/events.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
