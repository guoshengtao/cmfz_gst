package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page,Integer rows){
        Map<String,Object> map = bannerService.findAll(page, rows);
        return map;
    }

    @RequestMapping("updateBanner")
    public Map<String,Object> updateBanner(String oper, Banner banner, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)){
                String id = bannerService.addBanner(banner);
                map.put("message",id);
            }
            if ("edit".equals(oper)){
                bannerService.updateBanner(banner);
            }
            if("del".equals(oper)){
                bannerService.deleteBanner(banner.getId(),request);
            }
            map.put("status",true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile cover,String id,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        System.out.println(cover == null);
        System.out.println(cover.getOriginalFilename());
        try {
            //文件上传
            cover.transferTo(new File(request.getServletContext().getRealPath("/banner/img"),cover.getOriginalFilename()));
            //修改banner对象中cover属性
            Banner banner = new Banner();
            banner.setId(id);
            banner.setCover(cover.getOriginalFilename());
            System.out.println(banner);
            bannerService.updateBanner(banner);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }
}
