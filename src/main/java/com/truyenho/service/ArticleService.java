package com.truyenho.service;

import com.truyenho.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
  Article findById(Long id);

  Page<Article> findAll(Pageable pageable);

  void save(Article article);

  void remove(Long id);
}
