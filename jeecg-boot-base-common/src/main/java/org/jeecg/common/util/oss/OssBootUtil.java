package org.jeecg.common.util.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.http.fileupload.FileItemStream;
//import org.jeecg.modules.scan.controller.OSSFileController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 阿里云 oss 上传工具类(高依赖版)
 * @Date: 2019/5/10
 */

@Slf4j
public class OssBootUtil {

    private static String endPoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String staticDomain;

    public static void setEndPoint(String endPoint) {
        OssBootUtil.endPoint = endPoint;
    }

    public static void setAccessKeyId(String accessKeyId) {
        OssBootUtil.accessKeyId = accessKeyId;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssBootUtil.accessKeySecret = accessKeySecret;
    }

    public static void setBucketName(String bucketName) {
        OssBootUtil.bucketName = bucketName;
    }

    public static void setStaticDomain(String staticDomain) {
        OssBootUtil.staticDomain = staticDomain;
    }

    /**
     * oss 工具客户端
     */
    private static OSSClient ossClient = null;
    private static String FILE_URL;

    
    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param file    待上传文件
     * @param fileDir 文件保存目录
     * @return oss 中的相对文件路径
     */
    public static String upload(MultipartFile file, String fileDir) {
//    	log.info("上传工具文件1，运行initOSS之前...");
        initOSS(endPoint, accessKeyId, accessKeySecret);
//        log.info("上传工具文件1，运行initOSS之后...");
        StringBuilder fileUrl = new StringBuilder();
        StringBuilder FILE_URL = new StringBuilder();	//注意：如果使用String，在上传多个文件的情况下，返回之【文件地址】总是最后一个文件的地址，WHY？
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            fileUrl = fileUrl.append(fileDir + fileName);
            
            FILE_URL = FILE_URL.append("https://" + bucketName + "." + endPoint + "/" + fileUrl);
//            FILE_URL = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
            //FILE_URL = staticDomain + "/" + fileUrl;
            PutObjectResult result = ossClient.putObject(bucketName, fileUrl.toString(), file.getInputStream());
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
            	System.out.println("------OSS文件上传成功fileUrl------" + fileUrl);
                System.out.println("------OSS文件上传成功FILE_URL------" + FILE_URL.toString());
            }
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        return FILE_URL.toString();
    }


    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param file    待上传文件
     * @param fileDir 文件保存目录
     * @return oss 中的相对文件路径
     */
    public static String upload(FileItemStream file, String fileDir) {
    	log.info("上传工具文件2，运行initOSS之前...");
        initOSS(endPoint, accessKeyId, accessKeySecret);
        log.info("上传工具文件2，运行initOSS之后...");
        StringBuilder fileUrl = new StringBuilder();
        try {
            String suffix = file.getName().substring(file.getName().lastIndexOf('.'));
            String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            fileUrl = fileUrl.append(fileDir + fileName);

            FILE_URL = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
            //FILE_URL = staticDomain + "/" + fileUrl;
            PutObjectResult result = ossClient.putObject(bucketName, fileUrl.toString(), file.openStream());
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                System.out.println("------OSS文件上传成功------" + fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return FILE_URL;
    }

    /**
     * 删除文件
     * @param url
     */
    public static void deleteUrl(String url) {
        String bucketUrl = "https://" + bucketName + "." + endPoint + "/";
        //String bucketUrl = staticDomain + "/";
        url = url.replace(bucketUrl,"");
        ossClient.deleteObject(bucketName, url);
    }

    /**
     * 删除文件
     * @param fileName
     */
    public static void delete(String fileName) {
        ossClient.deleteObject(bucketName, fileName);
    }

    /**
     * 初始化 oss 客户端
     *
     * @return
     */
    private static OSSClient initOSS(String endpoint, String accessKeyId, String accessKeySecret) {
        if (ossClient == null) {
            ossClient = new OSSClient(endpoint,
                    new DefaultCredentialProvider(accessKeyId, accessKeySecret),
                    new ClientConfiguration());
        }
        return ossClient;
    }

}