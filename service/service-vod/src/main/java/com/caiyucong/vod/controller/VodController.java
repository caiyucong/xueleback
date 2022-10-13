package com.caiyucong.vod.controller;

import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.R;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import com.caiyucong.vod.client.OrderClient;
import com.caiyucong.vod.client.VideoClient;
import com.caiyucong.vod.service.VodService;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("eduvod/vod")
public class VodController {

    @Resource
    private VodService vodService;

    @Resource
    private VideoClient videoClient;

    @Resource
    private OrderClient orderClient;

    @PostMapping
    public R uploadVideoFile(MultipartFile file) {
        return R.ok().putData("videoId", vodService.uploadVideoFile(file));
    }

    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        return vodService.deleteVideo(id) ? R.ok() : R.error();
    }

    @GetMapping("{id}")
    public R getVideoPlayAuth(@PathVariable String id, HttpServletRequest request) {
        val courseId = videoClient.getBySourceId(id);
        val token = JwtUtils.getMemberIdByJwtToken(request);
        if (token.equals("") || courseId.equals("")) {
            return R.error();
        }
        if (!orderClient.isBuy(courseId, token))
            return R.ok().setMessage("请购买之后观看").setSuccess(false)
                    .putData("courseId", courseId);
        return R.ok().putData("playAuth", vodService.getPlayAuth(id));
    }

}
