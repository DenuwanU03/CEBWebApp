package com.ceb.service;

import com.ceb.dao.CategoryDAO;
import com.ceb.dao.CategoryDAOImpl;
import com.ceb.exceptions.ValidationException;
import com.ceb.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO dao = new CategoryDAOImpl();

    @Override
    public List<Category> list() throws Exception { return dao.findAll(); }

    @Override
    public Category add(String name, String description) throws ValidationException, Exception {
        if (name == null || name.trim().isEmpty()) throw new ValidationException("Name is required");
        name = name.trim();
        if (name.length() > 60) throw new ValidationException("Name too long");
        if (dao.existsByName(name)) throw new ValidationException("Category already exists");
        if (description != null && description.length() > 255) throw new ValidationException("Description too long");
        return dao.create(new Category(name, description));
    }

    @Override
    public boolean remove(int id) throws Exception {
        return dao.delete(id);
    }
    
    @Override
    public Category update(int id, String name, String description)
            throws com.ceb.exceptions.ValidationException, Exception {

        if (id <= 0) throw new com.ceb.exceptions.ValidationException("Invalid id");
        if (name == null || name.trim().isEmpty())
            throw new com.ceb.exceptions.ValidationException("Name is required");
        name = name.trim();
        if (name.length() > 60)
            throw new com.ceb.exceptions.ValidationException("Name too long");
        if (description != null && description.length() > 255)
            throw new com.ceb.exceptions.ValidationException("Description too long");

        // Optional: avoid duplicate name clash with other rows
        var all = dao.findAll();
        for (var c : all) {
            if (c.getName().equalsIgnoreCase(name) && c.getId() != id)
                throw new com.ceb.exceptions.ValidationException("Category name already exists");
        }

        Category c = new Category(id, name, description);
        boolean ok = dao.update(c);
        if (!ok) throw new com.ceb.exceptions.ValidationException("Update failed");
        return c;
    }

}
