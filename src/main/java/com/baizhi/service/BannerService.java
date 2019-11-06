package com.baizhi.service;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BannerService {

    //增
    String addBanner(Banner banner);
    //删
    void deleteBanner(String id, HttpServletRequest request);
    //改
    void updateBanner(Banner banner);
    //查全部+分页
     Map<String,Object> findAll(Integer page,Integer rows);

}
