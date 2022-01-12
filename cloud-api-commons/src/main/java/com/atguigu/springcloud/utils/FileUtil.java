package com.atguigu.springcloud.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取项目中resource资源下的json文件。ide和 linux 都可以读取
 * @author: peng tao
 * @create: 2022-01-12 15:08
 */
@Slf4j
public class FileUtil {
    public static String readJsonFile(String fileName){
        String result = null;
        try {
            File file = ResourceUtils.getFile("chasspath:" + fileName);
            result = FileUtils.readFileToString(file, "UTF-8");

        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException {}", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IOException {}", e);
        }
        return result;
    }
}
