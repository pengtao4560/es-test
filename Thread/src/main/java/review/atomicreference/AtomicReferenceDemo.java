package review.atomicreference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;


@Getter
@AllArgsConstructor
@Data
class User {
    String userName;
    int age;
}

/**
 * AtomicReference原子引用 Demo
 * @see AtomicReference
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        boolean b = atomicReference.compareAndSet(z3, li4);
        boolean b2 = atomicReference.compareAndSet(z3, z3);
        System.out.println(b + "\t" +atomicReference.get().toString());
        System.out.println(b2 + "\t" +atomicReference.get().toString());
    }
}
