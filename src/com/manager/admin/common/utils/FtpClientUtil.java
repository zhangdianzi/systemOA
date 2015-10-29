package com.manager.admin.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtil {

    private static ResourceBundle res = ResourceBundle.getBundle("ftp_config");

    public static String ftpServer = res.getString("ftp.server");

    private static Integer ftpPort = Integer.parseInt(res.getString("ftp.port"));

    private static String ftpUser = res.getString("ftp.user");

    private static String ftpPassword = res.getString("ftp.password");

    private static String ftpBaseDir = res.getString("ftp.basedir");
    
    public static Integer needFtp = Integer.parseInt(res.getString("ftp.needftp"));

//     public static String ftpServer = "183.61.83.188";
//    
//     private static Integer ftpPort = 21;
//    
//     private static String ftpUser = "ppftp188";
//    
//     private static String ftpPassword = "pp!@#188";
//    
//     private static String ftpBaseDir = "pp/";

    private static FTPClient ftpClient = null;

    public static void main(String[] args) {
        uploadFile("D:\\ftptest\\test.ppt", "cp/apk", "test1.ppt");
    }

    /**
     * 上传单个文件
     * 
     */
    public static boolean uploadFile(String localFile, String remoteDir,
            String newFileName) {
        File file = new File(localFile);
        boolean flag = false;
        try {
            InputStream ins = new FileInputStream(file);
            flag = uploadFile(ins, remoteDir, newFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传单个文件
     * 
     */
    public static boolean uploadFile(InputStream ins, String remoteDir,
            String newFileName) {
        remoteDir = ftpBaseDir + remoteDir; // 加上用户的根目录
        boolean flag = false;
        try {
            connectServer();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // ftp.setFileType(FTP.ASCII_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);

            removeFile(remoteDir + "/" + newFileName); // 删除文件

            if (!ftpClient.changeWorkingDirectory(remoteDir)) {// 如果不能进入dir下，说明此目录不存在！
                ftpCreateDirectoryTree(remoteDir);
                ftpClient.changeWorkingDirectory(remoteDir);
            }
            flag = ftpClient.storeFile(newFileName, ins); // 上传文件
            ins.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return flag;
    }

    /**
     * 删除文件
     * 
     * @param srcFname
     * @return
     */
    private static boolean removeFile(String srcFname) {
        boolean flag = false;
        try {
            flag = ftpClient.deleteFile(srcFname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建目录
     * 
     * @param dirTree
     * @throws IOException
     */
    private static void ftpCreateDirectoryTree(String dirTree)
            throws IOException {

        boolean dirExists = true;
        String[] directories = dirTree.split("/");
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                if (dirExists) {
                    dirExists = ftpClient.changeWorkingDirectory(dir);
                }
                if (!dirExists) {
                    if (!ftpClient.makeDirectory(dir)) {
                        throw new IOException(
                                "Unable to create remote directory '" + dir
                                        + "'.  error='"
                                        + ftpClient.getReplyString() + "'");
                    }
                    if (!ftpClient.changeWorkingDirectory(dir)) {
                        throw new IOException(
                                "Unable to change into newly created remote directory '"
                                        + dir + "'.  error='"
                                        + ftpClient.getReplyString() + "'");
                    }
                }
            }
        }
    }

    /**
     * 设置FTP客服端的配置--一般可以不设置
     * 
     * @return
     */
    private static FTPClientConfig getFtpConfig() {
        FTPClientConfig ftpConfig = new FTPClientConfig(
                FTPClientConfig.SYST_UNIX);
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }

    /**
     * 登录服务器
     * 
     * @return
     */
    public static boolean connectServer() {
        boolean flag = true;
        if (ftpClient == null) {
            int reply;
            try {
                ftpClient = new FTPClient();
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setDefaultPort(ftpPort);
                ftpClient.configure(getFtpConfig());
                ftpClient.connect(ftpServer);
                ftpClient.login(ftpUser, ftpPassword);
                reply = ftpClient.getReplyCode();
                // ftpClient.setDataTimeout(120000);
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftpClient.disconnect();
                    System.err.println("FTP server refused connection.");
                    flag = false;
                }
            } catch (SocketException e) {
                flag = false;
                e.printStackTrace();
                System.err.println("登录ftp服务器【" + ftpServer + "】失败,连接超时！");
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
                System.err.println("登录ftp服务器【" + ftpServer + "】失败，FTP服务器无法打开！");
            }
        }
        return flag;
    }

    // 断开连接
    public static void disconnect() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
                ftpClient = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}