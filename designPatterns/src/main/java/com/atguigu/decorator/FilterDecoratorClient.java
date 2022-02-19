package com.atguigu.decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * jdk中应用了装饰者模式案例分析- 客户端调用
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
public class FilterDecoratorClient {

    public static void main(String[] args) throws IOException {


        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("G:\\workspace_idea_c\\cloud2020_new\\designPatterns\\src\\main\\resources\\装饰者模式.md"));

        System.out.println(dataInputStream.read());
        dataInputStream.close();
    }
}
