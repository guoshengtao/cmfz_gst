package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {

    Map<String,Object> findAll(Integer page,Integer rows);

    //添加
    String addAlbum(Album album);

    //修改
    void updateAlbum(Album album);

    //查一个
    Album findOne(String id);

}
