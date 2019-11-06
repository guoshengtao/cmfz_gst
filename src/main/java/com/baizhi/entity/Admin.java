package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

//@Table(name = "t_admin")  //对应数据库表名
@Data                //修饰范围：用在类上  给类生成有参构造 toString/hashcode/eqyals/get/set方法
@AllArgsConstructor  //修饰范围：用在类上 给类生成全部参数构造方法
@NoArgsConstructor   //修饰范围：用在类上 给类生成无参构造方法
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;
    private String nickname;

}
