package com.yyh.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 *
 * 七牛云工具类（操作的是本地文件）
 * @author yyh
 */
public class QiNiuUtils {
    //TODO:抽取到配置文件中
    public static String accessKey = "SFQk5LQQJrZniLWcBkVXgm12ui6y8dJMPCknvoFW";
    public static String secretKey = "2NG2avS858PN-ecom85bg4jht7lR2STPHOaFN3B9";
    public static String bucket = "imageregion";

    /**
     * 根据本地的文件路径上传文件
     * @param filePath
     * @param fileName
     */
    public static void upload2QiNiu(String filePath, String fileName){
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据字节流上传文件（也是本地（业务服务器)操作，而不是客服端直传）
     * @param bytes
     * @param fileName
     */
    public static void upload2QiNiu(byte[] bytes, String fileName){
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);

        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        //允许上传文件的临时token
        String upToken = auth.uploadToken(bucket);

        try {
            uploadManager.put(bytes, key, upToken);
        } catch (QiniuException e) {
            Response error_response = e.response;
            System.err.println(error_response.toString());
            try {
                System.err.println(error_response.bodyString());
            } catch (QiniuException ex) {
                //ignore
            }
        }
    }

    /**
     * 删除文件
     * @param fileName
     */
    public static void deleteFileFromQiniu(String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
