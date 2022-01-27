import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @description: Optional类测试
 * @author: peng tao
 * @create: 2022-01-27 16:37
 */
public class OptionalTest {
    @Test
    public void testOptinal() {
        Dept dept = null;
//        Optional<Dept> deptOptional = Optional.of(dept);
//        Dept dept1 = deptOptional.get();
        Optional<Dept> deptOptional2 = Optional.ofNullable(dept);
        Dept dept2 = deptOptional2.get();

//        System.out.println(dept1);
        System.out.println(dept2);

    }
}

class Dept {
    int id;
    int count;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
