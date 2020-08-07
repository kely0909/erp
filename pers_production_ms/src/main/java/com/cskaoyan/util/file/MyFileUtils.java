package com.cskaoyan.util.file;

import com.cskaoyan.util.check.ValidityCheck;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author Sayen Cool
 */
public class MyFileUtils {

    private static Logger logger = LogManager.getLogger(MyFileUtils.class);

    public static String uploadFile(MultipartFile file, String fileRootPath) throws IOException {
        byte[] uploadFileBytes = file.getBytes();
        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        // hash保存文件
        int hash = fileName.hashCode();
        String hexString = Integer.toHexString(hash);
        String dir = getDir(hexString);
        File directory = new File(fileRootPath, dir);
        directory.mkdirs();

        File uploadFilePath = new File(directory, fileName);
        FileUtils.writeByteArrayToFile(uploadFilePath, uploadFileBytes);
        return dir + fileName;
    }

    private static String getDir(String hexString) {
        char[] chars = hexString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c).append('/');
        }
        return sb.toString();
    }

    public static String updateFile(MultipartFile file, String fileRootPath, String fileSaveRelativeUrl) throws IOException {
        logger.info("enter updateDocument");
        deleteFile(fileRootPath, fileSaveRelativeUrl);
        return uploadFile(file, fileRootPath);
    }

    public static boolean deleteFile(String fileRootPath, String fileSaveRelativeUrl) {
        File deleteFile = new File(fileRootPath, fileSaveRelativeUrl);
        boolean deleteFlag = deleteFile.delete();
        while (deleteFlag){
            deleteFile = deleteFile.getParentFile();
            deleteFlag = deleteFile.delete();
        }
        return true;
    }

    public static String downLoad(String fileRootPath, String fileSaveRelativeUrl, HttpServletResponse response) {
        String msg = null;

        File file = new File(fileRootPath, fileSaveRelativeUrl);
        if (file.exists()) {
            try {
                downloadResponseProcess(fileSaveRelativeUrl, response, file);
            } catch (IOException e) {
                logger.error(e.getMessage());
                msg = "文件下载失败！请重试！";
            }
        } else {
            msg = "错误！文件不存在！";
        }
        return msg;
    }

    private static void downloadResponseProcess(String fileSaveRelativeUrl, HttpServletResponse response, File file) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        int index = fileSaveRelativeUrl.lastIndexOf("/");
        String originalFileName = fileSaveRelativeUrl.substring(index + 37);
        // 中文文件名需要转换编码格式（根据操作系统，中文通常是gb2312）
        originalFileName = new String(originalFileName.getBytes("GB2312"), "ISO_8859_1");
        response.setHeader("Content-Disposition", "attachment;fileName=" + originalFileName);
        // 在java中调用reponse.getOutputStream()方法会自动激活下载操作
        OutputStream os = response.getOutputStream();
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
