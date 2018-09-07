package com.dino.registermodule.config;

import com.dino.registermodule.removal.MailVerificationCodeRemovalListener;
import com.dino.registermodule.removal.ShotMessageVerificationCodeRemovalListener;
import com.dino.registermodule.utils.CacheBuild;
import com.dino.registermodule.utils.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheManagerConfig {

    public static int SHOT_MESSAGE_VERIFICATION_CODE_EFFECTIVE_TIME = 15 * 60;

    public static int MAIL_VERIFICATION_CODE_EFFECTIVE_TIME = 24 * 60 * 60;
    @Autowired
    private RedisComponent redisComponent;

    @Bean
    public ShotMessageVerificationCodeRemovalListener shotMessageVerificationCodeRemovalListener() {
        return new ShotMessageVerificationCodeRemovalListener();
    }

    @Bean
    public MailVerificationCodeRemovalListener mailVerificationCodeRemovalListener() {
        return new MailVerificationCodeRemovalListener();
    }

    @Bean("shotMessageVerificationCodeCache")
    public CacheManager shotMessageVerificationCodeCache(ShotMessageVerificationCodeRemovalListener shotMessageVerificationCodeRemovalListener) {
        return new CacheManager(shotMessageVerificationCodeRemovalListener, SHOT_MESSAGE_VERIFICATION_CODE_EFFECTIVE_TIME);
    }

    @Bean("mailVerificationCodeCache")
    public CacheManager mailVerificationCodeCache(MailVerificationCodeRemovalListener mailVerificationCodeRemovalListener) {
        return new CacheManager(mailVerificationCodeRemovalListener, MAIL_VERIFICATION_CODE_EFFECTIVE_TIME);
    }

    @Bean
    public CacheBuild cacheBuild(@Qualifier("shotMessageVerificationCodeCache") CacheManager shotMessageVerificationCodeCache, @Qualifier("mailVerificationCodeCache") CacheManager mailVerificationCodeCache) {
        CacheBuild cacheBuild = new CacheBuild();
        Map<CacheBuild.CacheType,CacheManager> cacheManagerMap = new HashMap<>();
        cacheManagerMap.put(CacheBuild.CacheType.MAIL_VERIFICATION_CODE_TYPE,mailVerificationCodeCache);
        cacheManagerMap.put(CacheBuild.CacheType.SHOT_MESSAGE_VERIFICATION_CODE_TYPE,shotMessageVerificationCodeCache);
        cacheBuild.setCacheManagerMap(cacheManagerMap);
        cacheBuild.setRedisComponent(redisComponent);
        return cacheBuild;
    }


}
