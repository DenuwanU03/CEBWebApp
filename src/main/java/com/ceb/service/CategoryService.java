package com.ceb.service;

import com.ceb.exceptions.ValidationException;
import com.ceb.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> list() throws Exception;
    Category add(String name, String description) throws ValidationException, Exception;
    boolean remove(int id) throws Exception;
    Category update(int id, String name, String description)
            throws com.ceb.exceptions.ValidationException, Exception;
    
}
