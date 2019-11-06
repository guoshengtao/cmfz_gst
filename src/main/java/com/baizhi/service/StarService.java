package com.baizhi.service;

import com.baizhi.entity.Star;

import java.util.List;
import java.util.Map;

public interface StarService {

    Map<String,Object> findAll(Integer page,Integer rows);

    //添加
    String addStar(Star star);

    //修改
    void updateStar(Star star);

    //查询所有明星
    List<Star> getAllStarForSelect();
}
