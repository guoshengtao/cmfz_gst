package com.baizhi.cmfz_gst;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ceshi {

    public static void main(String[] args) {

        //99乘法表
        //打印行数
      /*  for (int i = 1; i < 10; i++) {
            //打印条数
            for (int j = 1; j <= i; j++) {
                System.out.print(j+"*"+i+"="+i*j+'\t');
            }
            System.out.println();
        }*/

        // TODO Auto-generated method stub
        String str = "as5d46q4d6a4d6as4dwa9d7";
		/*
		 记录每个字符出现的次数    字符--出现次数
		 字符不可重复
		 出现次数可能一样 是独立的

		 key---字符
		 value---出现次数
		 */
        //获取char数组 遍历这个数组就可以拿到每个字符
        char[] array = str.toCharArray();
        //建立一个map集合用来放置相应字符信息
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        //遍历char数组 进行数据的筛选
        for(int i=0;i<array.length;i++){
            char c = array[i];
            //验证这个c 是否已经被统计
            if(map.containsKey(c)){
                //获取原本的统计次数
                Integer num = map.get(c);
                //将该数值+1
                num++;
                //修改原统计次数
                map.put(c, num);
            }else{//以前没有 第一次统计
                //添加进map并次数写1
                map.put(c,1);
            }
        }
        Set<Map.Entry<Character,Integer>> set = map.entrySet();
        for (Map.Entry<Character, Integer> entry : set) {
            System.out.println(entry.getKey()+"出现了"+entry.getValue()+"次");
        }
    }
    
}
