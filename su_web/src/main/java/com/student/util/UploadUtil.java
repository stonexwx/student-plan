package com.student.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UploadUtil {
    //在src/main/webapp下新建文件夹的名字
    private static final String uploadFileName = "upload";
    public static Map<String, List<String>> fileUploadFileNameByDate(MultipartFile[] myfiles, HttpServletRequest request) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,List<String>> filesName = new HashMap<>();
        List<String> photo = new ArrayList<>();
        List<String> video = new ArrayList<>();
        String strDate = sdf.format(date);
        for (MultipartFile file : myfiles) {
            // 此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if (file.isEmpty()) {
                System.out.println("文件未上传!");
            } else {
                // 得到上传的文件名
                String fileName = file.getOriginalFilename();
                assert fileName != null;
                String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
                MimeTypeUtils.imageSort();
                MimeTypeUtils.videoSort();
                // 得到服务器项目发布运行所在地址
                String uploadPath = request.getSession().getServletContext().getRealPath(uploadFileName)
                        + File.separator;
                System.out.println(uploadPath);
                String finalFileName = strDate + fileName;
                String path = uploadPath + "/" + finalFileName;
                // 把文件上传至path的路径
                File localFile = new File(path);
                try {
                    file.transferTo(localFile);
                    if(Arrays.binarySearch(MimeTypeUtils.IMAGE_EXTENSION,fileNameSuffix)>0){
                        photo.add(request.getScheme() + "://" + request.getServerName()
                                + ":" + request.getServerPort() + "/upload/" + finalFileName);
                        filesName.put("photo",photo);
                    }
                    if(Arrays.binarySearch(MimeTypeUtils.VIDEO_EXTENSION,fileNameSuffix)>0){
                        video.add(request.getServerName()
                                + ":" + request.getServerPort() + "/upload/" + finalFileName);
                        filesName.put("video",video);
                    }
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filesName;
    }

}
