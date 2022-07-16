package Thread;

/**
 * 多线程有序性问题demo
 */
public class SortDemo {

    private  static boolean ready = false;
    private volatile static int number;

    private static class ReaderThread extends Thread {
        public ReaderThread() {
            while (!ready) {
                Thread.yield();
                System.out.println("---");
            }

            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReaderThread readerThread = new ReaderThread();
        readerThread.start();
        number = 42;
        ready = true;
        readerThread.join();
    }

}
