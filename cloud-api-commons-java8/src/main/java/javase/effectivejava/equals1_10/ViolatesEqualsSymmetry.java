package javase.effectivejava.equals1_10;

import java.util.Objects;

/**
 * @description: 该类假设：重写equals时违反了对称性原则，则 main方法运行 内部类的equals方法比较忽略大小写差异，
 * 而String类的equals方法并没有忽略大小写差异
 * @author: peng tao
 * @create: 2021-10-12 14:29
 */
public class ViolatesEqualsSymmetry {
    // Broken - violates symmetry! equals时违反了对称性原则
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s));// true
        System.out.println(s.equals(cis)); // false

        /** 正如所料，cis.equals(s) 返回 true。问题是，尽管 CaseInsensitiveString 类中的 equals 方
         法知道正常字符串，但 String 类中的 equals 方法却忽略了不区分大小写的字符串。因此，s.equals(cis) 返 回 false，
         明显违反对称性。假设把一个不区分大小写的字符串放入一个集合中：*/
    }

    /*要消除这个问题，只需删除 equals 方法中与 String 类相互操作的恶意尝试。这样做之后，可以将该方法重
    构为单个返回语句:*/
    public static final class CaseInsensitiveString {
        private final String s;

        public CaseInsensitiveString(String s) {
            this.s = Objects.requireNonNull(s);
        }

        // Broken - violates symmetry! 违反了对称性原则
        @Override
        // 以下是  代码扫描规约插件检查出的问题颜色标识
        public boolean equals(Object o) {
            if (o instanceof CaseInsensitiveString) {
                return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
            }
            if (o instanceof String) {
                // One-way interoperability!
                return s.equalsIgnoreCase((String) o);
            }
            return false;
        }

        // ... Remainder omitted 剩余部分省略
        //修改为： 即可保证不违反对称性原则
/*        @Override
        public boolean equals(Object o) {
            return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
        }*/
    }
}
