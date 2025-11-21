package com.ceb.controller;

import com.ceb.util.DBUtil;
import com.ceb.model.Event;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/book")
public class BookFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        String sql = "SELECT * FROM events WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    resp.sendRedirect(req.getContextPath()+"/events?notfound=1");
                    return;
                }
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setCategoryId(rs.getInt("category_id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setVenue(rs.getString("venue"));
                Timestamp ts = rs.getTimestamp("event_date");
                e.setEventDate(ts != null ? ts.toLocalDateTime() : null);
                e.setPrice(rs.getDouble("price"));
                e.setSeatsTotal(rs.getInt("seats_total"));
                e.setSeatsAvailable(rs.getInt("seats_available"));
                e.setStatus(rs.getString("status"));

                req.setAttribute("event", e);
                req.getRequestDispatcher("/WEB-INF/views/public/book.jsp").forward(req, resp);
            }
        } catch (Exception ex) { throw new ServletException(ex); }
    }
}
