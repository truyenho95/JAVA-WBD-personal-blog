package com.truyenho.controller;

import com.truyenho.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/")
  public ModelAndView home() {
    return new ModelAndView("index");
  }

}
