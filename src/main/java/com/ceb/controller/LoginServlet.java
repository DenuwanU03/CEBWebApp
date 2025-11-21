package com.ceb.controller;

import com.ceb.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // if already logged in, go to a sensible page
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("userRole") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email") == null ? "" : req.getParameter("email").trim();
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Email and password are required.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        // NOTE: For the assignment demo we compare plaintext bytes to VARBINARY column.
        // (In production you would store a salted hash.)
        String sql = "SELECT id, role, name FROM users WHERE email=? AND password_hash=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setBytes(2, password.getBytes());   // matches VARBINARY data for demo

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession s = req.getSession(true);
                    s.setAttribute("userId",   rs.getInt("id"));
                    s.setAttribute("userRole", rs.getString("role"));   // ADMIN / USER
                    s.setAttribute("userName", rs.getString("name"));

                    // Where to go next
                    if ("ADMIN".equalsIgnoreCase(rs.getString("role"))) {
                        resp.sendRedirect(req.getContextPath() + "/admin/events");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/events");
                    }
                } else {
                    req.setAttribute("error", "Invalid email or password.");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
