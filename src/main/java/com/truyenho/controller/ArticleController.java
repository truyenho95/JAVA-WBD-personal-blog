package com.truyenho.controller;

import com.truyenho.model.Article;
import com.truyenho.service.ArticleService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/")
  public ModelAndView home() {
    return new ModelAndView("redirect:/article/list");
  }

  @GetMapping("/article/list")
  public ModelAndView listArticles(@RequestParam("title") Optional<String> title, Pageable pageable) {
    try {
      Page<Article> articles;
      if (title.isPresent()) {
        articles = articleService.findAllByTitleContaining(title.get(), pageable);
      } else {
        articles = articleService.findAll(new PageRequest(pageable.getPageNumber(), 3));
      }

      ModelAndView modelAndView = new ModelAndView("index");
      modelAndView.addObject("articles", articles);
      return modelAndView;

    } catch (HibernateException hibernateEx) {
      ModelAndView modelAndView = new ModelAndView("error");
      modelAndView.addObject("exception", hibernateEx.fillInStackTrace());
      return modelAndView;
    }
  }


}
