package com.ceb.controller;

import com.ceb.exceptions.ValidationException;
import com.ceb.model.Event;
import com.ceb.service.EventService;
import com.ceb.service.EventServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/admin/event/create")
public class EventCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EventService service = new EventServiceImpl();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // for datetime-local

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Event e = new Event();
            e.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            e.setTitle(req.getParameter("title"));
            e.setDescription(req.getParameter("description"));
            e.setVenue(req.getParameter("venue"));
            e.setEventDate(LocalDateTime.parse(req.getParameter("eventDate"), FMT));
            e.setPrice(Double.parseDouble(req.getParameter("price")));
            e.setSeatsTotal(Integer.parseInt(req.getParameter("seatsTotal")));
            e.setSeatsAvailable(Integer.parseInt(req.getParameter("seatsAvailable")));
            e.setStatus(req.getParameter("status"));

            service.add(e);
            resp.sendRedirect(req.getContextPath() + "/admin/events?added=1");
        } catch (ValidationException ve) {
            req.setAttribute("error", ve.getMessage());
            req.getRequestDispatcher("/admin/event/new").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
