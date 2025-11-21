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

@WebServlet("/admin/event/update")
public class EventUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EventService service = new EventServiceImpl();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Event e = new Event();
            e.setId(Integer.parseInt(req.getParameter("id")));
            e.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            e.setTitle(req.getParameter("title"));
            e.setDescription(req.getParameter("description"));
            e.setVenue(req.getParameter("venue"));
            e.setEventDate(LocalDateTime.parse(req.getParameter("eventDate"), FMT));
            e.setPrice(Double.parseDouble(req.getParameter("price")));
            e.setSeatsTotal(Integer.parseInt(req.getParameter("seatsTotal")));
            e.setSeatsAvailable(Integer.parseInt(req.getParameter("seatsAvailable")));
            e.setStatus(req.getParameter("status"));

            // reuse service validations by calling add() for new, or update via DAO through service
            // we'll just call dao through service by adding update method:
            // (Add the method to EventService/Impl now)
            serviceUpdate(e, resp, req);
        } catch (ValidationException ve) {
            req.setAttribute("error", ve.getMessage());
            req.getRequestDispatcher("/admin/event/edit?id="+req.getParameter("id")).forward(req, resp);
        } catch (Exception ex) { throw new ServletException(ex); }
    }

    private void serviceUpdate(Event e, HttpServletResponse resp, HttpServletRequest req)
            throws Exception {
        // add this method in EventService/Impl per below changes
        new EventServiceImpl().update(e);
        resp.sendRedirect(req.getContextPath()+"/admin/events?updated=1");
    }
}
