package javase.effectivejava.trywithsource1_9;

import java.io.*;

/**
 * @description: effective java 第一章第八节 使用try-with-resource代替 try-finally
 * @author: peng tao
 * @create: 2021-10-12 13:54
 */
public class tryWithSoruce {
    private static final int BUFFER_SIZE = 16;

    // try-with-resources on multiple resources - short and sweet 在多种资源上用资源进行尝试——简洁明了
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    /**
     * 使用try-catch 如果是两层结构  对比copy()方法
     */
    static void copy1(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    // try-with-resources with a catch clause
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
}
