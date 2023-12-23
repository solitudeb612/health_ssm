
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yyh.utils.QiNiuUtils;
import org.junit.Test;

/**
 *只适用于本地文件上传到七牛云服务器
 * 实际业务逻辑为：
 *              客户端---->业务服务器（请求token）
 *              业务服务器---->七牛云服务器（获取token）
 *              业务服务器---->客户端（给客户端token）
 *              客户端---->七牛云服务器（文件上传）
 *
 */
public class UploadTest {

    @Test
    public void testUpload() {
        // 编写测试用例来验证上传功能


        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "SFQk5LQQJrZniLWcBkVXgm12ui6y8dJMPCknvoFW";
        String secretKey = "2NG2avS858PN-ecom85bg4jht7lR2STPHOaFN3B9";
        String bucket = "imageregion";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "E:/a_xbnv_2 (2).png";

//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {

                }
            }
        }
    }

    @Test
    public void testDelete(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释

        String accessKey = "SFQk5LQQJrZniLWcBkVXgm12ui6y8dJMPCknvoFW";
        String secretKey = "2NG2avS858PN-ecom85bg4jht7lR2STPHOaFN3B9";

        String bucket = "imageregion";
        String key = "屏幕截图 2023-03-14 114316.png";

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

    @Test
    public void testQiNiuUtils(){
        QiNiuUtils.upload2QiNiu("E:/360MoveData/Users/14650/Desktop/新建 文本文档.txt", "hi.txt");
    }
}
