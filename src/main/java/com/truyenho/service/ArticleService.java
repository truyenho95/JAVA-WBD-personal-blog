package com.truyenho.service;

import com.truyenho.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ArticleService {
  Article findById(Long id);

  Page<Article> findAll(Pageable pageable);
  Page<Article> findAllByTitleContaining(String title, Pageable pageable);
//  Page<Article> findAllByDateContaining(LocalDate date, Pageable pageable);

  void save(Article article);

  void remove(Long id);
}
