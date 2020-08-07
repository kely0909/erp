package com.cskaoyan.controller;

import com.cskaoyan.util.check.ValidityCheck;
import com.cskaoyan.util.file.MyFileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Sayen Cool
 */
@Controller
@RequestMapping("/{fileType}")
public class FileController {

    private static Logger logger = LogManager.getLogger(FileController.class);

    private static String fileRootPath;

    static {
        // 上传的文件在WEB-INF之外
        fileRootPath = Objects.requireNonNull(FileController.class.getClassLoader().getResource("../../")).getPath();
        // 防止抛异常
        new File(fileRootPath).mkdirs();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map upload(@PathVariable String fileType, String dir, MultipartFile uploadFile, MultipartFile file) {
        logger.info("enter FileController upload");

        MultipartFile processFile = uploadFile == null ? file : uploadFile;

        Map<Object, Object> map = new HashMap<>();
        String prefix = ("pic".equals(fileType) || "pic".equals(dir)) ? "/pic" : "/file";
        String msg = ValidityCheck.checkUploadFile(processFile, prefix);
        String uploadFileUrl = null;

        if (null == msg) {
            try {
                uploadFileUrl = MyFileUtils.uploadFile(processFile, fileRootPath + prefix);
                map.put("url", prefix + "/" + uploadFileUrl);
                map.put("success", "文件上传成功！");
                map.put("error", 0);
            } catch (IOException e) {
                msg = "文件上传失败！请重试！";
                map.put("error", 1);
                logger.error(msg, e);
            }
        } else {
            map.put("error", 1);
        }
        map.put("msg", msg);

        return map;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(String fileName, String picName) {
        logger.info("enter FileController delete");

        String deleteFileName = null != fileName ? fileName : picName;

        if (deleteFileName.contains("=")){
            String[] split = deleteFileName.split("=");
            deleteFileName = split[1];
        }

        Map<Object, Object> map = new HashMap<>();
        String msg = null;

        boolean fileExist = ValidityCheck.isFileExist(fileRootPath, deleteFileName);
        if (fileExist) {
            boolean deleteFile = MyFileUtils.deleteFile(fileRootPath, deleteFileName);
            if (deleteFile) {
                map.put("data", "success");
                return map;
            } else {
                msg = "文件删除失败！";
            }
        } else {
            msg = "文件不存在！";
        }
        map.put("error", 1);
        map.put("msg", msg);
        map.put("data", msg);
        return map;
    }

    @RequestMapping("/download")
    @ResponseBody
    public Map downLoad(String fileName, HttpServletResponse response) {
        Map<Object, Object> map = new HashMap<>();
        String msg = null;

        boolean fileExist = ValidityCheck.isFileExist(fileRootPath, fileName);
        if (!fileExist) {
            map.put("error", 1);
            msg = "文件不存在！下载失败！";
        } else {
            map.put("data","success");
            map.put("error", 0);
            msg = MyFileUtils.downLoad(fileRootPath, fileName, response);
        }
        map.put("msg",msg);

        return map;
    }

}
