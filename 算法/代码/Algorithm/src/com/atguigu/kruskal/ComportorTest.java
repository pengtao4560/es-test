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
        User �ϰ� = new User(1, "�ϰ�", -1);
        User ���� = new User(1, "�ϰ�", 3);
        User Ա�� = new User(1, "�ϰ�", 5);


    }

}
