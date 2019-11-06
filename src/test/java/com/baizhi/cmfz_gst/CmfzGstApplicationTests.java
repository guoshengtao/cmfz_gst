package com.baizhi.cmfz_gst;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.util.SecurityCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CmfzGstApplicationTests {

    @Autowired
    private AdminDao adminDao;
    private BannerDao bannerDao;

    @Test
    void contextLoads() {
//        List<Admin> list = adminDao.selectAll();
//        for (Admin admin : list) {
//            System.out.println(admin);
//        }
        List<Banner> list = bannerDao.selectAll();
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }

}
