package com.ceb.dao;

import com.ceb.model.Event;
import java.util.List;
import java.util.Optional;

public interface EventDAO {
    List<Event> findAll() throws Exception;               // joined with category name
    Optional<Event> findById(int id) throws Exception;
    Event create(Event e) throws Exception;               // returns with id
    boolean update(Event e) throws Exception;
    boolean delete(int id) throws Exception;
}
