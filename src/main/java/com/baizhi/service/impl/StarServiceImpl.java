package com.baizhi.service.impl;

import com.baizhi.dao.StarDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Star;
import com.baizhi.service.StarService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StarServiceImpl implements StarService {

    @Autowired
    private StarDao starDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        //创建一个star对象
        Star star = new Star();
        //获取当前页展示数据                 参数1：起始行数    参数2：每页展示条数
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Star> list = starDao.selectByRowBounds(star, rowBounds);
        //获取总条数
        int count = starDao.selectCount(star);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);//总共有几页
        map.put("records",count);//总共有多少条数据
        return map;
    }

    @Override
    public String addStar(Star star) {
        //生成UUID
        star.setId(UUID.randomUUID().toString());
        int i = starDao.insertSelective(star);
        if (i == 0){
            throw new RuntimeException("添加明星失败");
        }
        return star.getId();
    }

    @Override
    public void updateStar(Star star) {
        try {
            starDao.updateByPrimaryKeySelective(star);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改明星失败");
        }

    }

    //查询所有明星
    @Override
    public List<Star> getAllStarForSelect() {
        List<Star> list = starDao.selectAll();
        return list;
    }


}
