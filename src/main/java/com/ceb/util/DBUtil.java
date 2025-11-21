package com.ceb.util;

import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final String URL;
    private static final String USER;
    private static final String PASS;

    static {
        try {
            Properties p = new Properties();
            p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            URL = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASS = p.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB config/driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
