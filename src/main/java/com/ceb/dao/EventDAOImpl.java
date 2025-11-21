package com.ceb.dao;

import com.ceb.model.Event;
import com.ceb.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDAOImpl implements EventDAO {

    private Event map(ResultSet rs) throws SQLException {
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
        // joined column (may be null if not joined)
        try { e.setCategoryName(rs.getString("category_name")); } catch (SQLException ignore) {}
        return e;
    }

    @Override
    public List<Event> findAll() throws Exception {
        String sql = """
          SELECT e.*, c.name AS category_name
          FROM events e
          JOIN categories c ON e.category_id = c.id
          ORDER BY e.event_date DESC
        """;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Event> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    @Override
    public Optional<Event> findById(int id) throws Exception {
        String sql = "SELECT * FROM events WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(map(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public Event create(Event e) throws Exception {
        String sql = """
          INSERT INTO events
          (category_id, title, description, venue, event_date, price, seats_total, seats_available, status)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getCategoryId());
            ps.setString(2, e.getTitle());
            ps.setString(3, e.getDescription());
            ps.setString(4, e.getVenue());
            ps.setTimestamp(5, Timestamp.valueOf(e.getEventDate()));
            ps.setDouble(6, e.getPrice());
            ps.setInt(7, e.getSeatsTotal());
            ps.setInt(8, e.getSeatsAvailable());
            ps.setString(9, e.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) e.setId(keys.getInt(1));
            }
            return e;
        }
    }

    @Override
    public boolean update(Event e) throws Exception {
        String sql = """
          UPDATE events SET category_id=?, title=?, description=?, venue=?,
                 event_date=?, price=?, seats_total=?, seats_available=?, status=?
          WHERE id=?
        """;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getCategoryId());
            ps.setString(2, e.getTitle());
            ps.setString(3, e.getDescription());
            ps.setString(4, e.getVenue());
            ps.setTimestamp(5, Timestamp.valueOf(e.getEventDate()));
            ps.setDouble(6, e.getPrice());
            ps.setInt(7, e.getSeatsTotal());
            ps.setInt(8, e.getSeatsAvailable());
            ps.setString(9, e.getStatus());
            ps.setInt(10, e.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM events WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
