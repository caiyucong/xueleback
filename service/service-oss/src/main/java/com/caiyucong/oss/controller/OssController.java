package com.caiyucong.oss.controller;

import com.caiyucong.commonutils.R;
import com.caiyucong.oss.service.OssService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eduoss/upload")
public class OssController {
    @Resource
    private OssService ossService;

    @PostMapping
    public R uploadImages(MultipartFile file) {
        String url = ossService.uploadAvatar(file);
        return R.ok().putData("url", url);
    }
}
