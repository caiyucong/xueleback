package com.caiyucong.uncenterservice.controller;

import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import com.caiyucong.uncenterservice.domain.Member;
import com.caiyucong.uncenterservice.service.MemberService;
import com.caiyucong.uncenterservice.utils.ConstantWxUtils;
import com.caiyucong.uncenterservice.utils.HttpClientUtils;
import com.google.gson.Gson;
import lombok.val;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Resource
    private MemberService memberService;

    @GetMapping
    public String login() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new MyCustomException(20001, e.getMessage());
        }
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu");
        return "redirect:" + qrcodeUrl;
    }

    @GetMapping("callback")
    public String callback(String code, String state) {
        try {
            String baseAccessTokenUrl =
                    "https://api.weixin.qq.com/sns/oauth2/access_token" +
                            "?appid=%s" +
                            "&secret=%s" +
                            "&code=%s" +
                            "&grant_type=authorization_code";
            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code);
            val accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            val gson = new Gson();
            val hashMap = gson.fromJson(accessTokenInfo, HashMap.class);
            val accessToken = hashMap.get("access_token").toString();
            val openid = hashMap.get("openid").toString();
            var member = memberService.getByOpenId(openid);
            if (member == null) {
                val baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                val userInfoURL = String.format(baseUserInfoUrl, accessToken, openid);
                val userInfo = HttpClientUtils.get(userInfoURL);
                val userInfoMap = gson.fromJson(userInfo, HashMap.class);
                member = new Member();
                member.setOpenid(openid);
                member.setNickname(userInfoMap.get("nickname").toString());
                member.setAvatar(userInfoMap.get("headimgurl").toString());
                memberService.save(member);
            }
            val jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token=" + jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyCustomException(20001, "登陆失败");
        }
    }

}
