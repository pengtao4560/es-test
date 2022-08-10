package 海尔2022年笔试题;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 *
 */
public class Test4 {
    public static void main(String[] args) throws IOException {
       File file = new File("D:\\IDEA\\");
        boolean mkdirs = file.mkdirs();
        boolean newFile = file.createNewFile();
        System.out.println(file.list());
        String[] list = file.list();
        System.out.println(Arrays.toString(list));

        System.out.println();
        System.out.println(Arrays.toString(file.listFiles()));
    }

}
/**
 * 哪个 文件file 对象 的API能够打印包括文件路径的所有文件。
 * 答案 file.listFiles()
 */
