package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    //增
    @Override
    public String addBanner(Banner banner) {
        //生成UUID
        banner.setId(UUID.randomUUID().toString());
        //创建时间为当前时间
        banner.setCreate_date(new Date());
        int i = bannerDao.insertSelective(banner);
        if(i == 0){
            throw new RuntimeException("添加失败");
        }
        return banner.getId();
    }

    //删除
    @Override
    public void deleteBanner(String id, HttpServletRequest request) {
        //通过ID查询某个banner
        Banner banner = bannerDao.selectByPrimaryKey(id);
        int i = bannerDao.deleteByPrimaryKey(id);
        if(i == 0){
            throw new RuntimeException("删除失败");
        }else {
            //获取封面
            String cover = banner.getCover();
            //获取封面图片
            File file = new File(request.getServletContext().getRealPath("banner/img/"), cover);
            //删除封面图片
            boolean b = file.delete();
            if(b == false){
                throw new RuntimeException("图片删除失败");
            }
        }
    }

    //修改
    @Override
    public void updateBanner(Banner banner) {
        //判断cover封面是否修改，没有修改则依旧为原图
        if("".equals(banner.getCover())){
            banner.setCover(null);
        }
        try {
            bannerDao.updateByPrimaryKeySelective(banner);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }

    }

    //查全部+分页
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Banner banner = new Banner();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Banner> list = bannerDao.selectByRowBounds(banner, rowBounds);
        int count = bannerDao.selectCount(banner);

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);//总共有几页
        map.put("records",count);//总共有多少条数据

        return map;
    }
}
