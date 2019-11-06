package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findUsersByStarId")
    public Map<String,Object> findUsersByStarId(Integer page,Integer rows,String starId){
        Map<String, Object> map = userService.findUsersByStarId(page, rows, starId);
        return map;
    }

    @RequestMapping("export")
    public void export(HttpServletResponse resp){
        //获取数据
        List<User> list = userService.export();
        for (User user : list) {
            user.setPhoto("D:/IdeaWorkspaces/project02/cmfz_gst/src/main/webapp/user/img/"+user.getPhoto());
        }
        //下载文件设置
        //文件内容格式设置
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "所有用户"), User.class, list);
        //下载文件名设置
        String fileName = "用户列表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";

        //处理中文下载名乱码
        try {
            fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition","attachment;filename="+fileName);
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
