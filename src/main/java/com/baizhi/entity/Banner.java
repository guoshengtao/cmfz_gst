package com.baizhi.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//@Table(name="t_banner")//对应数据库表名
@Data                //修饰范围：用在类上  给类生成有参构造 toString/hashcode/eqyals/get/set方法
@AllArgsConstructor  //修饰范围：用在类上 给类生成全部参数构造方法
@NoArgsConstructor   //修饰范围：用在类上 给类生成无参构造方法
public class Banner {

    @Id
    private String id;
    private String name;
    private String cover;
    private String description;
    private String status;
    //序列化
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
}
