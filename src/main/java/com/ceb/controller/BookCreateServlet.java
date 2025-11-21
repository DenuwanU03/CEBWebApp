package com.ceb.controller;

import com.ceb.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

@WebServlet("/book/create")
public class BookCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        int userId = 1; // demo user

        try (Connection con = DBUtil.getConnection()) {
            try {
                con.setAutoCommit(false);

                // lock the row to avoid race conditions
                try (PreparedStatement ps1 = con.prepareStatement(
                        "SELECT seats_available FROM events WHERE id=? FOR UPDATE")) {
                    ps1.setInt(1, eventId);
                    try (ResultSet rs = ps1.executeQuery()) {
                        if (!rs.next()) throw new RuntimeException("Event not found");
                        int avail = rs.getInt(1);
                        if (qty <= 0 || qty > avail)
                            throw new RuntimeException("Not enough seats");
                    }
                }

                // insert ticket
                try (PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO tickets(user_id, event_id, qty) VALUES (?,?,?)")) {
                    ps2.setInt(1, userId);
                    ps2.setInt(2, eventId);
                    ps2.setInt(3, qty);
                    ps2.executeUpdate();
                }

                // decrement seats
                try (PreparedStatement ps3 = con.prepareStatement(
                        "UPDATE events SET seats_available = seats_available - ? WHERE id=?")) {
                    ps3.setInt(1, qty);
                    ps3.setInt(2, eventId);
                    ps3.executeUpdate();
                }

                con.commit();
                resp.sendRedirect(req.getContextPath()+"/events?booked=1");
            } catch (Exception ex) {
                con.rollback();
                req.setAttribute("error", ex.getMessage());
                req.getRequestDispatcher("/book?eventId="+eventId).forward(req, resp);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception ex) { throw new ServletException(ex); }
    }
}
