package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> list = articleDao.selectByRowBounds(article, rowBounds);
        int count = articleDao.selectCount(article);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String addArticle(Article article) {
        System.out.println("addService");
        //生成UUID
        article.setId(UUID.randomUUID().toString());
        //创建时间为当前时间
        article.setCreateDate(new Date());
        int i = articleDao.insertSelective(article);
        if (i == 0 ){
            throw new RuntimeException("添加文章失败");
        }
        return article.getId();
    }

    @Override
    public void updateArticle(Article article) {
        //判断Content内容是否修改，没有修改则依旧为原内容
        if("".equals(article.getContent())){
            article.setContent(null);
        }
        try {
            articleDao.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void deleteArticle(String id) {
        int i = articleDao.deleteByPrimaryKey(id);
        if (i == 0){
            throw new RuntimeException("删除文章失败");
        }

    }

}
