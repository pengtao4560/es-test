package javase.effectivejava.equals1_10;

/**
 * @description: 该类假设：重写equals时违反了传递性原则
 * @author: peng tao
 * @create: 2021-10-12 15:31
 */
public class ViolatesEqualsT {
    public static void main(String[] args) {

    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
    //... Remainder omitted
}
