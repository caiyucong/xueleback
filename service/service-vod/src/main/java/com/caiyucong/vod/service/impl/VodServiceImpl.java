package com.caiyucong.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.caiyucong.commonutils.R;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import com.caiyucong.vod.service.VodService;
import com.caiyucong.vod.utils.MyVODUtils;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public boolean deleteVideo(String id) {
        DefaultAcsClient client = MyVODUtils.initVodClient();
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        deleteVideoRequest.setVideoIds(id);
        try {
            client.getAcsResponse(deleteVideoRequest);
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyCustomException(20001, "删除失败");
        }
    }

    @Override
    public String getPlayAuth(String id) {
        try {
            val client = MyVODUtils.initVodClient();
            val request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            val response = client.getAcsResponse(request);
            return response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyCustomException(20001, "没有找到视频id");
        }
    }

    @Override
    public String uploadVideoFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title;
            if (fileName != null) {
                title = fileName.substring(0, fileName.lastIndexOf("."));
            } else {
                return null;
            }
            UploadStreamRequest request = new UploadStreamRequest(MyVODUtils.ACCESS_KEY_ID, MyVODUtils.ACCESS_KEY_SECRET,
                    title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，
                // 此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
                throw new MyCustomException(20001, "上传视频失败");
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
