package com.truyenho.service.Impl;

import com.truyenho.model.Category;
import com.truyenho.repository.CategoryRepository;
import com.truyenho.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category findById(Long id) {
    return categoryRepository.findOne(id);
  }

  @Override
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public void remove(Long id) {
    categoryRepository.delete(id);
  }
}
