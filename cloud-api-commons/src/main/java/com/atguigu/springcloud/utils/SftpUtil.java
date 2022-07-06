package com.atguigu.springcloud.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sftp工具类
 */
@Getter
@Setter
@Slf4j
@Component
public class SftpUtil {
    @Value("${sftp.pengtao.ip}")
    private String sshHost;
    @Value("${sftp.pengtao.port}")
    private int sshPort;
    @Value("${sftp.pengtao.userName}")
    private String sshUser;
    @Value("${sftp.pengtao.password}")
    private String sshPass;
    /**
     * 文件保存的根路径
     */
    @Value("${sftp.pengtao.path}")
    private String rootPath;
    @Value("${spring.application.name}")
    private String appName;

    /** sftp session 被线程使用的数量 */
    private AtomicInteger sessionUseCount = new AtomicInteger(0);


    public String uploadFile(String fileId, MultipartFile multipartFile) throws SftpException, IOException {
        InputStream inputStream = null;
        Session session = null;
        String filePath;
        try {
            session = getSession();
            ChannelSftp channel = JschUtil.openSftp(session);
            channel.setFilenameEncoding(CharsetUtil.UTF_8);
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = fileId + fileName.substring(fileName.lastIndexOf("."));
            filePath = getFilePath();
            if(checkDirExist(channel,filePath)){
                mkdirs(channel);
            }
            filePath = filePath + "/" +fileName;
            inputStream = multipartFile.getInputStream();
            channel.put(inputStream, filePath);
            return filePath;
        }  finally {
            this.closeSession(session);
            if(inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    /**
     * 下载文件，将sftp文件写入到输出流
     * @param filePath 文件的sftp存储路径
     * @param os 输出流
     * @return
     */
    public void downloadFile(String filePath, OutputStream os) throws SftpException, IOException {
        Session session = null;
        InputStream inputStream = null;
        try {
            session = getSession();
            ChannelSftp channel = JschUtil.openSftp(session);
            inputStream = channel.get(filePath);
            byte[] buffer = new byte[5120];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                os.write(buffer, 0, len);
            }
        } finally {
            this.closeSession(session);
            if(inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public ByteArrayOutputStream getFileStream(String filePath) throws SftpException, IOException {
        Session session = null;
        try {
            session = getSession();
            ChannelSftp channel = JschUtil.openSftp(session);
            InputStream inputStream = channel.get(filePath);
            return cloneInputStream(inputStream);
        } finally {
            this.closeSession(session);
        }
    }

    public InputStream getFileInputStream(String filePath) throws SftpException, IOException {
        Session session = null;
        try {
            session = getSession();
            ChannelSftp channel = JschUtil.openSftp(session);
            InputStream inputStream = channel.get(filePath);
            ByteArrayOutputStream baos = cloneInputStream(inputStream);
            return new ByteArrayInputStream(baos.toByteArray());
        } finally {
            this.closeSession(session);
        }
    }

    private Session getSession() {
        sessionUseCount.incrementAndGet();
        return JschUtil.getSession(sshHost, sshPort, sshUser, sshPass);
    }

    private void closeSession(Session session) {
        int useCount = sessionUseCount.decrementAndGet();
        if(useCount<=0) {
            log.info("Sftp Session Closed！！！sessionUseCount={}", useCount);
            if(session != null) {
                try {
                    if (session.isConnected()) {
                        session.disconnect();
                    }
                } catch (Exception ex) {
                    log.error("关闭Sftp Session出错", ex);
                }
            }
        } else {
            log.info("Sftp Session Not Closed！！！sessionUseCount={}", useCount);
        }
    }

    private String getFilePath() {
        String datePattern = "yyyy/MM/dd";
        String date = DateUtil.format(DateUtil.date(), datePattern);
        return rootPath + date ;
    }

    private static ByteArrayOutputStream cloneInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            return baos;
        } finally {
            baos.flush();
            baos.close();
            input.close();
        }
    }

    private boolean checkDirExist(ChannelSftp channel,String dirPath){
        try {
            Vector dirs = channel.ls(dirPath);
            return dirs == null;
        } catch (SftpException e) {
            log.error("文件路径{}不存在",dirPath);
            return true;
        }

    }

    private void mkdirs(ChannelSftp channel) throws SftpException {
        Calendar calendar = Calendar.getInstance();
        String dirPath = rootPath + calendar.get(Calendar.YEAR);
        if(checkDirExist(channel,dirPath)){
            channel.mkdir(dirPath);
        }
        if(calendar.get(Calendar.MONTH) < 9){
            dirPath = dirPath + "/0" + (calendar.get(Calendar.MONTH) + 1);
        }else {
            dirPath = dirPath + "/" + (calendar.get(Calendar.MONTH) + 1);
        }
        if(checkDirExist(channel,dirPath)){
            channel.mkdir(dirPath);
        }
        if(calendar.get(Calendar.DATE) < 10){
            dirPath = dirPath + "/0" + calendar.get(Calendar.DATE);
        }else {
            dirPath = dirPath + "/" + calendar.get(Calendar.DATE);
        }
        if(checkDirExist(channel,dirPath)){
            channel.mkdir(dirPath);
        }
        log.info("最终path====>"+ dirPath);
    }

    public static void main(String[] args) throws SftpException, IOException {
        SftpUtil sftpUtil = new SftpUtil();
        /*InputStream inputStream = sftpUtil.getFileInputStream("/home/wasadmin/tmsnfsdta/data02/tmsfs/test1.txt");
        ByteArrayOutputStream result = new ByteArrayOutputStream();*/
        ByteArrayOutputStream baos = sftpUtil.getFileStream("/home/wasadmin/tmsnfsdta/data02/tmsfs/test1.txt");
        InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        System.out.println(result.toString("UTF-8"));
        result.close();
        inputStream.close();
    }
}
