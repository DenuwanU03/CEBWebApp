package com.ceb.controller;

import com.ceb.util.DBUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

@WebServlet("/dbtest")
public class DbTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT NOW()");
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            resp.getWriter().println("DB connection OK. Server time: " + rs.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("DB connection FAILED: " + e.getMessage());
        }
    }
}
