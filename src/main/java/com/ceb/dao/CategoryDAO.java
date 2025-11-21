package com.ceb.dao;

import com.ceb.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryDAO {
    List<Category> findAll() throws Exception;
    Optional<Category> findById(int id) throws Exception;
    boolean existsByName(String name) throws Exception;
    Category create(Category c) throws Exception;   // returns with id
    boolean update(Category c) throws Exception;
    boolean delete(int id) throws Exception;
}
