package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> findUsersByStarId(Integer page, Integer rows, String starId) {
        //创建一个user对象
        User user = new User();
        user.setStarId(starId);
        //获取当前页展示数据                   参数1：起始行数   参数2：每页展示条数
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> list = userDao.selectByRowBounds(user, rowBounds);
        //获取总条数
        int count = userDao.selectCount(user);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);//总共有几页
        map.put("records",count);//总共有多少条数据
        return map;
    }

    @Override
    public List<User> export() {
        return userDao.selectAll();
    }
}
