package com.ceb.controller;

import com.ceb.util.DBUtil;
import com.ceb.model.Event;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/events")
public class EventPublicListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sql = """
            SELECT e.*, c.name AS category_name
            FROM events e
            JOIN categories c ON e.category_id=c.id
            WHERE e.status='ACTIVE'
            ORDER BY e.event_date ASC
        """;
        List<Event> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setCategoryId(rs.getInt("category_id"));
                e.setCategoryName(rs.getString("category_name"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setVenue(rs.getString("venue"));
                Timestamp ts = rs.getTimestamp("event_date");
                e.setEventDate(ts != null ? ts.toLocalDateTime() : null);
                e.setPrice(rs.getDouble("price"));
                e.setSeatsTotal(rs.getInt("seats_total"));
                e.setSeatsAvailable(rs.getInt("seats_available"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        req.setAttribute("events", list);
        req.getRequestDispatcher("/WEB-INF/views/public/events.jsp").forward(req, resp);
    }
}
