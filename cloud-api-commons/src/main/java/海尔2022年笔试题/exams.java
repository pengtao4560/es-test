package 海尔2022年笔试题;

/**
 * 考题需要放开16行
 */
public class exams {
    static int result = 0;
    int engScore;

    exams(int score) {
        engScore = score;
    }

    static class results {
        results() {
            // System.out.println("Reuslt = " + result + "," + engScore);
        }
    }
    // 这个题可能是考的 静态内部类不能因引用外部类的非静态变量 Non-static field 'engScore' cannot be referenced from a static context
}
