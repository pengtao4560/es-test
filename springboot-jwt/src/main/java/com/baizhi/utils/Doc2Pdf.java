package com.baizhi.utils;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class Doc2Pdf {
    public static void main(String[] args) throws IOException {

        final String path = "D:\\poi-test\\wordToHtml\\";
        final String file = "git入门教学.docx";
        String inPath = path + file;
        String outPath = path + "test.pdf";
        try (FileInputStream fileInputStream = new FileInputStream(inPath);
             FileOutputStream fileOutputStream = new FileOutputStream(outPath);//调用转换
        ) {//读取docx文件

            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);

            PdfOptions pdfOptions = PdfOptions.create();//输出路径

            PdfConverter.getInstance().convert(xwpfDocument, fileOutputStream, pdfOptions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
