package cn.ocoop.framwork.miniprogram.user;

import cn.ocoop.framwork.miniprogram.MiniprogramProperties;
import cn.ocoop.framwork.miniprogram.util.RestTemplateHelper;
import cn.ocoop.framwork.miniprogram.util.WxCryptUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MiniprogramUserService {

    @Autowired
    MiniprogramProperties config;

    public Code2SessionResponse code2Session(String js_code) {
        String retStr = RestTemplateHelper.get().getForObject(
                "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code",
                String.class,
                config.getAppid(),
                config.getSecret(),
                js_code
        );

        log.info("微信接口返回:{}", retStr);

        return JSON.parseObject(retStr, Code2SessionResponse.class);
    }

    public PhoneNumberDecryptResponse getPhoneNumber(String encryptedData, String iv, String sessionKey) throws Exception {
        String phoneNumberDecryptResponse = WxCryptUtils.decrypt(encryptedData, iv, sessionKey);
        log.info("微信返回:{}", phoneNumberDecryptResponse);
        return JSON.parseObject(phoneNumberDecryptResponse, PhoneNumberDecryptResponse.class);
    }
}
