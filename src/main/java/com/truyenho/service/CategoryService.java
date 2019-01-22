package com.truyenho.service;

import com.truyenho.model.Category;

public interface CategoryService {

  Category findById(Long id);

  Iterable<Category> findAll();

  void save(Category category);

  void remove(Long id);

}
