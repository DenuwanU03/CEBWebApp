package com.ceb.service;

import com.ceb.exceptions.ValidationException;
import com.ceb.model.Event;
import java.util.List;

public interface EventService {
    List<Event> list() throws Exception;
    Event add(Event e) throws ValidationException, Exception;
    Event update(Event e) throws ValidationException, Exception;
    boolean remove(int id) throws Exception;

}
