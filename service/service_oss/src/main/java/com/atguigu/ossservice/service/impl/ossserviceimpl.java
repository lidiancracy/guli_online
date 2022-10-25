package com.atguigu.ossservice.service.impl;

import com.atguigu.ossservice.service.ossservice;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;


/**
 * @ClassName ossserviceimpl
 * @Description TODO
 * @Date 2022/10/24 17:58
 */
@Service
public class ossserviceimpl implements ossservice {

    /**
     * 头像文件上传api
     * @param inputStream
     * @param fileName
     * @return
     */
    @Value("${qiniu.accesskey}")
    String accesskey;
    @Value("${qiniu.secretkey}")
    String secretkey;
    @Value("${qiniu.domain}")
    String prewebside;
    @Value("${qiniu.bucketname}")
    String bucketname;
    @Override
    public String upload(InputStream inputStream, String fileName) {
        //构造一个带指定 Region 对象的配置类
        //Region.region2();代表华南地区
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //AK和SK从七牛云个人中心秘钥管理查看
        String accessKey = accesskey;
        String secretKey = secretkey;
        //bucket就是创建
        String bucket = bucketname;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        String result = null;

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                if(response.statusCode==200){
                    //返回图片上传的路径地址
                    result = prewebside+fileName;
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return result;
    }
}
