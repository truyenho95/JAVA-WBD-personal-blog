package com.truyenho.service.Impl;

import com.truyenho.model.Category;
import com.truyenho.repository.CategoryRepository;
import com.truyenho.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category findById(Long id) {
    return categoryRepository.findOne(id);
  }

  @Override
  public Page<Category> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public void save(Category category) {

  }

  @Override
  public void remove(Long id) {

  }
}
