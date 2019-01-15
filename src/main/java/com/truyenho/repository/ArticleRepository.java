package com.truyenho.repository;

import com.truyenho.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
  Page<Article> findAllByTitleContaining(String title, Pageable pageable);
  Page<Article> findAllByDateContaining(LocalDate date, Pageable pageable);
}
