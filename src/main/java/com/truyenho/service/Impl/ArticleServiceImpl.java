package com.truyenho.service.Impl;

import com.truyenho.model.Article;
import com.truyenho.repository.ArticleRepository;
import com.truyenho.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public Article findById(Long id) {
    return articleRepository.findOne(id);
  }

  @Override
  public Page<Article> findAll(Pageable pageable) {
    return articleRepository.findAll(pageable);
  }

  @Override
  public void save(Article article) {
    articleRepository.save(article);
  }

  @Override
  public void remove(Long id) {
    articleRepository.delete(id);
  }
}
