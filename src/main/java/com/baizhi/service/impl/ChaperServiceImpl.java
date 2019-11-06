package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service
@Transactional
public class ChaperServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows, String albumId) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Chapter> list = chapterDao.selectByRowBounds(chapter, rowBounds);
        int count = chapterDao.selectCount(chapter);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);//总共有几页
        map.put("records",count);//总共有多少条数据
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        int i = chapterDao.insertSelective(chapter);
        if (i == 0){
            throw new RuntimeException("添加章节失败");
        }
        return chapter.getId();
    }

    @Override
    public void updateChapter(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if (i == 0){
            throw new RuntimeException("修改章节失败");
        }
    }
}
