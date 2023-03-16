package com.zsh.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by zsh on 2023/3/16
 *
 * @author zsh
 */
public class AliyunOssUtils {

    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "LTAI5t8ZNHpgMD1DnT3SXNMJ";
    private static final String accessKeySecret = "Y5aH5Y6PdOxXEzzOuzjKuIszr0x0pl";
    private static final String bucketName = "menu-api";
    // 域名地址
    private static final String ossBaseUrl = "https://menu-api.oss-cn-beijing.aliyuncs.com/";;

    public static String uploadFile(MultipartFile file) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            //添加随机数防止文件在oss上被覆盖（文件名相同),并加入日期作为文件夹 /a/b/1.jpg-----/2021/10/10/1.jpg
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + filename;
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + filename;
            ossClient.putObject(bucketName, filename, inputStream);

            inputStream.close();
            ossClient.shutdown();

            return "https://" + bucketName + "." + endpoint + "/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void deleteFile(String fileUrl) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String objectName = fileUrl.replace(ossBaseUrl, "");
        try {
            // 删除文件。
            ossClient.deleteObject(bucketName, objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
