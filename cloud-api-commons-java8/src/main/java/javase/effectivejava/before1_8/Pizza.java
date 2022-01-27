package javase.effectivejava.before1_8;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @description:
 * @author: peng tao
 * @create: 2021-09-22 15:20
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // Subclasses must override this method to return "this" 子类必须重写此方法以返回"this"
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
        // See Item 50
    }
}
