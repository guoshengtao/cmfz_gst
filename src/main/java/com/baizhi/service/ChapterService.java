package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> findAll(Integer page,Integer rows,String albumId);

    String addChapter(Chapter chapter);

    void updateChapter(Chapter chapter);
}
