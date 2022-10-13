package com.caiyucong.oss.service.impl;

import com.caiyucong.oss.service.OssService;
import com.caiyucong.oss.utils.ConstYmlUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        var key = "images/";
        var cred = new BasicCOSCredentials(ConstYmlUtils.SECRET_ID, ConstYmlUtils.SECRET_KEY);
        var region = new Region(ConstYmlUtils.REGION);
        var clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        var cosClient = new COSClient(cred, clientConfig);
        try {
            var inputStream = file.getInputStream();
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            var filename = file.getOriginalFilename();
            if (filename != null)
                key += UUID.randomUUID().toString().replaceAll("-", "") +
                        filename.substring(filename.lastIndexOf("."));
            var putObjectRequest = new PutObjectRequest(ConstYmlUtils.BUCKET, key, inputStream, objectMetadata);
            cosClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        return ConstYmlUtils.getUrl(key);
    }
}
