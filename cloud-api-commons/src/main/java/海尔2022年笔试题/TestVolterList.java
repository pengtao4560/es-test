package 海尔2022年笔试题;


/**
 * 本题的问题记得是 是否线程安全。  有  synchronized 应该线程安全。 但是 他还是一个 static变量
 */
 class VolterList {

    static private int volters = 0;
    int totalVoters;

    private static synchronized int Counter() {
        return ++volters;
    }

    public void getTotalVoterCount() {
        totalVoters = Counter();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            new Thread(){
                @Override
                public void run() {
                    VolterList volterList = new VolterList();
                    volterList.getTotalVoterCount();

                }
            }.start();

        }
    }
}
public class TestVolterList {
    //
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            new Thread(){
                @Override
                public void run() {
                    VolterList volterList = new VolterList();

                    volterList.getTotalVoterCount();

                }
            }.start();

        }
    }
}
