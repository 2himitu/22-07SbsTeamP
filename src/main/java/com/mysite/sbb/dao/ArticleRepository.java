package com.mysite.sbb.dao;

import com.mysite.sbb.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    ArrayList<Article> Articles =  new ArrayList<>();

    List<Article> findByTitle(String title);

    boolean existsByTitle(String title);

    boolean existsByBody(String body);

    List<Article> findByBody(String body);

    boolean existsByTitleAndBody(String title, String body);

    List<Article> findByTitleAndBody(String title, String body);
}
