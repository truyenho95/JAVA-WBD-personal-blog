package com.truyenho.controller;

import com.truyenho.model.Article;
import com.truyenho.service.ArticleService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    } catch (Exception ex) {
      System.out.println("ERROR SOMETHING AT: GET /article/list");
      ex.getStackTrace();
    }
    return null;
  }

  @GetMapping("/article/create")
  public ModelAndView showCreateArticle() {
    ModelAndView modelAndView = new ModelAndView("create");
    modelAndView.addObject("article", new Article());
    return modelAndView;
  }

  @PostMapping("/article/create")
  public ModelAndView createArticle(@ModelAttribute("article") Article article) {
    try {
      articleService.save(article);
      ModelAndView modelAndView = new ModelAndView("create");
      modelAndView.addObject("article", new Article());
      modelAndView.addObject("success", "Create new article successful");
      return modelAndView;
    } catch (Exception ex) {
      System.out.println("ERROR SOMETHING AT: POST /article/create");
      ex.getStackTrace();
    }
    return null;
  }

  @GetMapping("/article/edit/{id}")
  public ModelAndView showEditArticle(@PathVariable("id") Article article) {
    if (article == null) {
      return new ModelAndView("error");
    } else {
      ModelAndView modelAndView = new ModelAndView("edit");
      modelAndView.addObject("article", article);
      return modelAndView;
    }
  }

  @PostMapping("/article/edit")
  public ModelAndView editArticle(@ModelAttribute("article") Article article) {
    articleService.save(article);
    ModelAndView modelAndView = new ModelAndView("edit");
    modelAndView.addObject("success", "Edit Article Successful");
    modelAndView.addObject("article", new Article());
    return modelAndView;
  }

}
