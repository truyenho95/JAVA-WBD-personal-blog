package com.truyenho.service;

import com.truyenho.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

  Category findById(Long id);

  Page<Category> findAll(Pageable pageable);

  void save(Category category);

  void remove(Long id);

}
