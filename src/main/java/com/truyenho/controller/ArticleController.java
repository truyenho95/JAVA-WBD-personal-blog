package com.truyenho.controller;

import com.truyenho.model.Article;
import com.truyenho.model.Category;
import com.truyenho.service.ArticleService;
import com.truyenho.service.CategoryService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/")
  public ModelAndView home() {
    return new ModelAndView("redirect:/article/list");
  }

  @GetMapping("/article/list")
  public ModelAndView listArticles(@RequestParam("title") Optional<String> title, @PageableDefault(size = 5) Pageable pageable, @ModelAttribute("success") String success) {
    try {
      Page<Article> articles;
      if (title.isPresent()) {
        articles = articleService.findAllByTitleContaining(title.get(), pageable);
      } else {
        articles = articleService.findAll(pageable);
      }

      ModelAndView modelAndView = new ModelAndView("index");
      modelAndView.addObject("articles", articles);
      modelAndView.addObject("success", success);
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
    Iterable<Category> categories = categoryService.findAll();
    modelAndView.addObject("categories", categories);
    modelAndView.addObject("article", new Article());
    return modelAndView;
  }

  @PostMapping("/article/create")
  public ModelAndView createArticle(@ModelAttribute("article") Article article) {
    try {
      articleService.save(article);
      ModelAndView modelAndView = new ModelAndView("redirect:/article/list");
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
    ModelAndView modelAndView = new ModelAndView("edit");
    modelAndView.addObject("article", article);
    return modelAndView;
  }

  @PostMapping("/article/edit")
  public ModelAndView editArticle(@ModelAttribute("article") Article article) {
    articleService.save(article);
    ModelAndView modelAndView = new ModelAndView("redirect:/article/list");
    modelAndView.addObject("success", "Edit Article Successful");
    modelAndView.addObject("article", new Article());
    return modelAndView;
  }

  @GetMapping("/article/delete/{id}")
  public ModelAndView showDeleteArticle(@PathVariable("id") Article article) {
    ModelAndView modelAndView = new ModelAndView("delete");
    modelAndView.addObject("article", article);
    modelAndView.addObject("id", article.getId());
    return modelAndView;
  }

  @PostMapping("/article/delete")
  public ModelAndView deleteArticle(@ModelAttribute("id") Long id) {
    articleService.remove(id);
    ModelAndView modelAndView = new ModelAndView("redirect:/article/list");
    modelAndView.addObject("article", new Article());
    modelAndView.addObject("success", "Remove Article Successful");
    return modelAndView;
  }

  @GetMapping("/article/view/{id}")
  public ModelAndView showViewArticle(@PathVariable("id") Article article) {
    ModelAndView modelAndView = new ModelAndView("view");
    modelAndView.addObject("article", article);
    return modelAndView;
  }

}
