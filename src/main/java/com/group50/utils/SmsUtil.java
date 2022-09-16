package com.group50.utils;

import com.group50.dto.SmsMessage;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
    private final String[] patch = {"000000","000000","0000","000","00","0",""};

    /**
     * 报错的话，去检查用户是否有详细的个人信息，比如手机号
     * @param tel 用户手机号
     * @return 用户验证码
     */
    public String createCode(String tel){
        int hash = tel.hashCode();
        int encryption = 1008611;
        long encryptResult = hash ^ encryption;
        long currentTime = System.currentTimeMillis();
        encryptResult = encryptResult ^ currentTime;
        long result = encryptResult % 1000000;
        result = result < 0 ? -result : result;
        String code = result + "";
        int len = code.length();
        return patch[len] + code;
    }

    /**
     * 需加载进spring容器
     *
     * 从smsService的value = "VerifyCodeSpace"，的空间中取出生成的code值
     * return null if not have relative value
     */
    @Cacheable(value = "VerifyCodeSpace", key = "#tel")
    public String getVerificationCodeFromCache(String tel){

        return null;
    }

    //把数据放入缓存，不取出来（如果空间中有key为#tel的值就把，对应的值存进去或者取出来）
    @CachePut(value = "VerifyCodeSpace", key = "#tel")
    public String sentCode(String tel) {
        return createCode(tel);
    }

    /**
     * 用户输入的code和这个创建出来的code做校验
     */
    public boolean checkCode(SmsMessage smsMessage) {

        //get code from cache
        String codeFromCache = getVerificationCodeFromCache(smsMessage.getTelephone());
        System.out.println("111111");
        System.out.println(codeFromCache);
        System.out.println(smsMessage.getCode());
        //check whether the input code = or not code from cache
        return smsMessage.getCode().equals(codeFromCache);
    }
}
