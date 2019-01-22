package com.truyenho.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "article")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private LocalDate date = LocalDate.now();
  private String content;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Article() {
  }

  public Article(String title, LocalDate date, String content, Category category) {
    this.title = title;
    this.date = date;
    this.content = content;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return String.format("Article[title='%s', date='%s', category='%s']", title, date.toString(), category.getCategoryName());
  }
}
