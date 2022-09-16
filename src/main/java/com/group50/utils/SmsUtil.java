package com.group50.utils;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
    private final String[] match = {"000000","000000","0000","000","00","0",""};

    /**
     * 报错的话，去检查用户是否有详细的个人信息，比如手机号
     * @param tel 用户手机号
     * @return 用户验证码
     */
    public String generateCode(String tel){
        int hash = tel.hashCode();
        int encryption = 17458521;
        long encryptResult = hash ^ encryption;
        long currentTime = System.currentTimeMillis();
        encryptResult = encryptResult ^ currentTime;
        long result = encryptResult % 1000000;
        result = result < 0 ? -result : result;
        String code = result + "";
        int len = code.length();
        return match[len] + code;
    }

    /**
     * 需加载进spring容器
     * return null if not have relative value
     */
    @Cacheable(value = "SmsCodeSpace", key = "#telephone")
    public String getSmsCodeFromCache(String telephone){

        return null;
    }

    //把数据放入缓存，不取出来（如果空间中有key为#tel的值就把，对应的值存进去或者取出来）
    @CachePut(value = "SmsCodeSpace", key = "#telephone")
    public String makeCode(String telephone) {
        return generateCode(telephone);
    }

}
