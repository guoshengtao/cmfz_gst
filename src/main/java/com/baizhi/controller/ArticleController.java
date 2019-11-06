package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows){
        return articleService.findAll(page, rows);
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Article article,String id){
        Map<String, Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)){

                 articleService.addArticle(article);

            }
            if ("edit".equals(oper)){
                articleService.updateArticle(article);
            }
            if("del".equals(oper)){
                System.out.println(id);
                articleService.deleteArticle(id);
            }
            map.put("satatus",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile articleImg, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("article/img"), articleImg.getOriginalFilename());
        try {
            articleImg.transferTo(file);
            map.put("error",0);
            map.put("url","http://localhost:8081/cmfz/article/img"+articleImg.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
        }
        return map;
    }

    @RequestMapping("browse")
    public Map<String,Object> browse (HttpServletRequest request){
       Map<String, Object> map = new HashMap<>();
        File file = new File(request.getServletContext().getRealPath("article/img"));
        File[] files = file.listFiles();
        List<Object> list = new ArrayList<>();
        for (File img : files) {
            HashMap<String, Object> imgObject = new HashMap<>();
            imgObject.put("is_dir",false);
            imgObject.put("has_file",false);
            imgObject.put("filesize",img.length());
            imgObject.put("is_photo",true);
            imgObject.put("filetype", FilenameUtils.getExtension(img.getName()));
            imgObject.put("filename",img.getName());
            imgObject.put("datetime","2008-08-08 08:08:08");
            list.add(imgObject);
        }
        map.put("file_list",list);
        map.put("total_count",list.size());
        map.put("current_url","http://localhost:8081/cmfz/article/img");
        return map;
    }



}
