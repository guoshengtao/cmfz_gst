package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


//@Table(name = "t_user")  //对应数据库表名
@Data                //修饰范围：用在类上  给类生成有参构造 toString/hashcode/eqyals/get/set方法
@AllArgsConstructor  //修饰范围：用在类上 给类生成全部参数构造方法
@NoArgsConstructor   //修饰范围：用在类上 给类生成无参构造方法
public class User {
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "用户名")
    private String username;
    private String password;
    private String salt; //盐
    @Excel(name = "昵称")
    private String nickname;
    @Excel(name = "联系方式")
    private String phone;
    @Excel(name = "省份")
    private String province; //省
    @Excel(name = "城市")
    private String city; //市
    @Excel(name = "签名")
    private String sign; //签名
    @Excel(name = "头像",type = 2)
    private String photo; //照片
    @Excel(name = "性别")
    private String sex;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间",format = "yyyy-MM-dd")
    private Date createDate;
    @Excel(name = "关注明星ID")
    private String starId;
}
