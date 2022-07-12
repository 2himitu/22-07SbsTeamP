package com.mysite.sbb.dao;

import com.mysite.sbb.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    ArrayList<Article> Articles =  new ArrayList<>();

    List<Article> findByTitle(String title);
}
