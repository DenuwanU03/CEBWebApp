package com.ceb.service;

import com.ceb.dao.EventDAO;
import com.ceb.dao.EventDAOImpl;
import com.ceb.exceptions.ValidationException;
import com.ceb.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceImpl implements EventService {
    private final EventDAO dao = new EventDAOImpl();

    @Override
    public List<Event> list() throws Exception { return dao.findAll(); }

    @Override
    public Event add(Event e) throws ValidationException, Exception {
        if (e.getCategoryId() <= 0) throw new ValidationException("Category is required");
        if (e.getTitle() == null || e.getTitle().trim().isEmpty()) throw new ValidationException("Title required");
        if (e.getTitle().length() > 120) throw new ValidationException("Title too long");
        if (e.getVenue() == null || e.getVenue().trim().isEmpty()) throw new ValidationException("Venue required");
        if (e.getEventDate() == null || e.getEventDate().isBefore(LocalDateTime.now()))
            throw new ValidationException("Event date must be in the future");
        if (e.getPrice() < 0) throw new ValidationException("Price cannot be negative");
        if (e.getSeatsTotal() < 0) throw new ValidationException("Seats total cannot be negative");
        if (e.getSeatsAvailable() < 0 || e.getSeatsAvailable() > e.getSeatsTotal())
            throw new ValidationException("Seats available must be between 0 and seats total");
        if (e.getStatus() == null) e.setStatus("ACTIVE");
        return dao.create(e);
    }
    @Override
    public Event update(Event e) throws ValidationException, Exception {
        // reuse same validations as add()
        if (e.getId() <= 0) throw new ValidationException("Invalid id");
        add(e); // will validate fields; but don't insert — so copy validation from add() into a private method:
        return dao.update(e) ? e : null;
    }

    @Override public boolean remove(int id) throws Exception { return dao.delete(id); }

}
