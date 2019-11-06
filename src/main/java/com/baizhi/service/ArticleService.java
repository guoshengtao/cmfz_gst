package com.baizhi.service;


import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {

    Map<String,Object> findAll(Integer page,Integer rows);

    String addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(String id);

}
