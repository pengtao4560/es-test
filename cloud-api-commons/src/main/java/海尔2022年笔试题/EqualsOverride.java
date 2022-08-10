package 海尔2022年笔试题;

import java.util.HashMap;
import java.util.Objects;

/**
 * 以下程序运行结果为：
 * null
 * null
 * 因为重写equals方法，并没有重写hashCode，所以  相等的对象 作为hashMap的key 从 hashMap中 取不到数据。
 *
 * 如果重写了hashCode方法，就能拿到结果：
 * A object
 * B object
 */
public class EqualsOverride {

    int instanceVar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof EqualsOverride && this.instanceVar == ((EqualsOverride) o).instanceVar) {

            return true;
        } else {
            return false;
        }
    }

/*    @Override
    public int hashCode() {
        return Objects.hash(instanceVar);
    }*/

    public static void main(String[] args) {
        EqualsOverride a = new EqualsOverride();
        a.instanceVar = 1;

        EqualsOverride b = new EqualsOverride();
        b.instanceVar = 2;

        EqualsOverride c = new EqualsOverride();
        c.instanceVar = 1;

        EqualsOverride d = new EqualsOverride();
        d.instanceVar = 2;

        HashMap<EqualsOverride, String> mapStore = new HashMap<>();
        mapStore.put(a, "A object");
        mapStore.put(b, "B object");
        System.out.println(mapStore.get(c));
        System.out.println(mapStore.get(d));

    }
}
//
