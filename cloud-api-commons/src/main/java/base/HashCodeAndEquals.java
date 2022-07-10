package base;

import lombok.Data;

/**
 *
 */
public class HashCodeAndEquals {

    public static void main(String[] args) {
        Person p1 = new Person("1", "张三");
        Person p2 = new Person("1", "张三");
        System.out.println(p1.equals(p2));

        boolean b = p1.hashCode() == p2.hashCode();
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(b);
    }

    @Data
    public static class Person{
        String id;
        String name;

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
