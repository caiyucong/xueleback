package com.caiyucong.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {

    String uploadVideoFile(MultipartFile file);

    boolean deleteVideo(String id);

    String getPlayAuth(String id);

}
