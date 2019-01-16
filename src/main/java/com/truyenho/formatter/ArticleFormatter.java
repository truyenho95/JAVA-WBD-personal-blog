package com.truyenho.formatter;

import com.truyenho.model.Article;
import com.truyenho.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ArticleFormatter implements Formatter<Article> {

  private ArticleService articleService;

  @Autowired
  public ArticleFormatter(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Override
  public Article parse(String text, Locale locale) throws ParseException {
    return articleService.findById(Long.parseLong(text));
  }

  @Override
  public String print(Article object, Locale locale) {
    return "[" + object.getId() + ", " + object.getTitle() + "]";
  }

}
