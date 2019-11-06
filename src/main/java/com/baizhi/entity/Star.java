package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//@Table(name = "t_star")  //对应数据库表名
@Data                //修饰范围：用在类上  给类生成有参构造 toString/hashcode/eqyals/get/set方法
@AllArgsConstructor  //修饰范围：用在类上 给类生成全部参数构造方法
@NoArgsConstructor   //修饰范围：用在类上 给类生成无参构造方法
public class Star {
    @Id
    private String id;
    private String nickname; //艺名
    private String realname; //真名
    private String photo; //照片
    private String sex; //性别
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bir;
}
