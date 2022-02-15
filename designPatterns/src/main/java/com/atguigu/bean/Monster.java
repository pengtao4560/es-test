package com.atguigu.bean;

/**
 * 原型模式使用demo 类
 *
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
public class Monster {

    private Integer id = 10 ;
    private String nickname = "牛魔王";
    private String skill = "芭蕉扇";
    public Monster() {

        System.out.println("monster 创建..");
    }
    public Monster(Integer id, String nickname, String skill) {
        //System.out.println("Integer id, String nickname, String skill被调用");
        this.id = id;
        this.nickname = nickname;
        this.skill = skill;
    }

    public Monster( String nickname, String skill,Integer id) {

        this.id = id;
        this.nickname = nickname;
        this.skill = skill;
    }

}
