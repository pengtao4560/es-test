package com.atguigu.kruskal;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 *
 */
class User implements Comparable<User>{
    int id;
    String name;
    int priority;

    public User(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(User o) {
        return o.priority-this.priority;
    }
}
public class ComportorTest {
    public static void main(String[] args) {
        User 老板 = new User(1, "老板", -1);
        User 经理 = new User(1, "老板", 3);
        User 员工 = new User(1, "老板", 5);


    }

}
