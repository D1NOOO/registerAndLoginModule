package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.LoginUserConstant;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.service.VerificationCodeService;
import com.dino.registermodule.utils.Cache;
import com.dino.registermodule.utils.CacheBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.dino.registermodule.base.LoginUserConstant.RedisShortMessagePrefix.*;
import static com.dino.registermodule.config.CacheManagerConfig.MAIL_VERIFICATION_CODE_EFFECTIVE_TIME;
import static com.dino.registermodule.config.CacheManagerConfig.SHOT_MESSAGE_VERIFICATION_CODE_EFFECTIVE_TIME;


@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private LoginUserService loginUserService;

    @Override
    public boolean checkLoginVerificationCode(String loginMobile, String verificationCode) {
        String loginVerificationCode = this.getMobileVerificationCode(LOGIN_TYPE_PREFIX,loginMobile);
        return loginVerificationCode !=null &&  loginVerificationCode.equalsIgnoreCase(verificationCode);
    }

    /**
     * 创建登录验证码
     * @param loginMobile 登录手机号码
     * @return
     */
    @Override
    public String createLoginVerificationCode(String loginMobile) {
        return this.createMobileVerificationCode(LOGIN_TYPE_PREFIX, loginMobile);
    }

    /**
     * 检查绑定手机验证码
     * @param loginMobile
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkBindMobileVerificationCode(String loginMobile, String verificationCode) {

        String loginVerificationCode = this.getMobileVerificationCode(BIND_LOGIN_USER_TYPE_PREFIX,loginMobile);

        return loginVerificationCode !=null  && loginVerificationCode.equalsIgnoreCase(verificationCode);
    }

    /**
     * 检查取消绑定手机验证码
     * @param loginMobile
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkCancelBindMobileVerificationCode(String loginMobile, String verificationCode) {
        String loginVerificationCode = this.getMobileVerificationCode(CANCEL_BIND_LOGIN_USER_TYPE_PREFIX,loginMobile);
        return loginVerificationCode !=null  && loginVerificationCode.equalsIgnoreCase(verificationCode);
    }


    /**
     * 创建绑定手机验证码
     * @param loginMobile 手机号码
     * @return
     */
    @Override
    public String createBindMobileVerificationCode(String loginMobile) {
        return this.createMobileVerificationCode(BIND_LOGIN_USER_TYPE_PREFIX, loginMobile);
    }

    @Override
    public String createCancelBindMobileVerificationCode(String loginMobile) {
        return this.createMobileVerificationCode(CANCEL_BIND_LOGIN_USER_TYPE_PREFIX,loginMobile);
    }

    /**
     * 检查手机忘记密码验证码
     * @param loginMobile
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkForgetPasswordVerificationCode(String loginMobile, String verificationCode) {
        String loginVerificationCode = this.getMobileVerificationCode(FORGET_PASSWORD_TYPE_PREFIX,loginMobile);
        return loginVerificationCode.equalsIgnoreCase(verificationCode);
    }

    /**
     * 创建手机忘记密码验证码
     * @param loginMobile 手机号码
     * @return
     */
    @Override
    public String createForgetPasswordVerificationCode(String loginMobile) {
        return this.createMobileVerificationCode(FORGET_PASSWORD_TYPE_PREFIX, loginMobile);
    }

    /**
     * 检查手机注册验证码
     * @param loginMobile
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkRegisterVerificationCode(String loginMobile, String verificationCode) {
        String loginVerificationCode = this.getMobileVerificationCode(LoginUserConstant.RedisShortMessagePrefix.REGISTER_TYPE_PREFIX,loginMobile);
        return loginVerificationCode !=null && loginVerificationCode.equalsIgnoreCase(verificationCode);
    }

    /**
     * 创建手机注册验证码
     * @param loginMobile 手机号码
     * @return
     */
    @Override
    public String createRegisterVerificationCode(String loginMobile) {
        return this.createMobileVerificationCode(REGISTER_TYPE_PREFIX, loginMobile);
    }

    /**
     * 检查是否绑定邮箱验证码
     * @param loginId
     * @param loginMail
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkBindMailVerificationCode( String loginId,String loginMail, String verificationCode) {
        Map<String,Object> value = this.getMailVerificationCode(LoginUserConstant.RedisLoginUserMailPrefix.BIND_MAIL_TYPE_PREFIX,loginMail);
        if(org.springframework.util.CollectionUtils.isEmpty(value)){
            return false;
        }
        String cacheLoginId = (String) value.get("loginId");
        String cacheVerificationCode = (String) value.get("verificationCode");
        if(StringUtils.isEmpty(cacheLoginId)|| StringUtils.isEmpty(cacheVerificationCode)){
            return false;
        }
        return cacheLoginId.equalsIgnoreCase(loginId)&&cacheVerificationCode.equalsIgnoreCase(verificationCode);
    }

    /**
     * 检查是否取消绑定邮箱验证码
     * @param loginMail
     * @param loginId
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkCancelBindMailVerificationCode(String loginMail, String loginId, String verificationCode) {
        Map<String,Object> value = this.getMailVerificationCode(LoginUserConstant.RedisLoginUserMailPrefix.CANCEL_BIND_MAIL_TYPE_PREFIX,loginMail);
        if (org.springframework.util.CollectionUtils.isEmpty(value)){
            return false;
        }
        String cacheLoginId = (String)value.get("loginId");
        String cacheVerificationCode = (String)value.get("cacheVerificationCode");
        if (StringUtils.isEmpty(cacheLoginId)|| StringUtils.isEmpty(cacheVerificationCode)){
            return false;
        }
        return cacheLoginId.equalsIgnoreCase(loginId)&&cacheVerificationCode.equalsIgnoreCase(verificationCode);
    }


    /**
     * 创建绑定邮箱验证码
     * @param loginMail 登录邮箱
     * @return
     */
    @Override
    public String createBindMailVerificationCode(String loginMail) {
        return this.createMailVerificationCode(LoginUserConstant.RedisLoginUserMailPrefix.BIND_MAIL_TYPE_PREFIX,loginMail);
    }

    /**
     * 创建取消绑定邮箱验证码
     * @param loginMail
     * @return
     */
    @Override
    public String createCancelBindMailVerificationCode(String loginMail) {
        return this.createMailVerificationCode(LoginUserConstant.RedisLoginUserMailPrefix.CANCEL_BIND_MAIL_TYPE_PREFIX,loginMail);
    }

    /**
     * 检查邮箱忘记密码验证码是否正确
     * @param loginMobile
     * @param loginId
     * @param verificationCode
     * @return
     */
    @Override
    public boolean checkMailForgetPasswordVerificationCode(String loginMobile, String loginId, String verificationCode) {
        return false;
    }

    /**
     * 创建邮箱忘记密码验证码
     * @param loginMail 登录邮箱
     * @return
     */
    @Override
    public String createMailForgetPasswordVerificationCode(String loginMail) {
        return this.createMailVerificationCode(LoginUserConstant.RedisLoginUserMailPrefix.FORGET_PASSWORD_TYPE_PREFIX,loginMail);
    }

    /**
     * 创建邮箱验证码
     * @param mailPrefix
     * @param loginMail
     * @return
     */
    private String createMailVerificationCode(String mailPrefix, String loginMail) {
        LoginUser loginUser = loginUserService.getLoginUserByLoginMail(loginMail);
        if(null==loginUser){
            //throw new NotFindLoginUserException();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"用户不存在！");
        }
        String key = mailPrefix + loginMail;
        String verificationCode = this.createRandom(NUMBER_FLAG_FALSE, MAIL_LENGTH);
        Cache cache = CacheBuild.build(CacheBuild.CacheType.MAIL_VERIFICATION_CODE_TYPE);
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("loginId", loginUser.getLoginMail());
        valueMap.put("verificationCode", verificationCode);
        cache.save(key, valueMap, MAIL_VERIFICATION_CODE_EFFECTIVE_TIME);
        return verificationCode;
    }

    /**
     * 获取邮箱验证码
     * @param mailPrefix
     * @param loginMail
     * @return
     */
    private Map<String,Object> getMailVerificationCode(String mailPrefix, String loginMail){
        Cache cache = CacheBuild.build(CacheBuild.CacheType.MAIL_VERIFICATION_CODE_TYPE);
        Map<String,Object> value = cache.getMapValue(mailPrefix+loginMail);
        return value;
    }

    /**
     * 创建手机验证码
     * @param shortMessagePrefix
     * @param loginMobile
     * @return
     */
    private String createMobileVerificationCode(String shortMessagePrefix, String loginMobile) {
        String key = shortMessagePrefix + loginMobile;
        String value = this.createRandom(NUMBER_FLAG_TRUE, SHORT_MESSAGE_LENGTH);
        Cache cache = CacheBuild.build(CacheBuild.CacheType.SHOT_MESSAGE_VERIFICATION_CODE_TYPE);
        cache.save(key, value, SHOT_MESSAGE_VERIFICATION_CODE_EFFECTIVE_TIME);
        return value;
    }

    /**
     * 获取手机验证码
     * @param shortMessagePrefix
     * @param loginMobile
     * @return
     */
    private String getMobileVerificationCode(String shortMessagePrefix,String loginMobile){
        Cache cache = CacheBuild.build(CacheBuild.CacheType.SHOT_MESSAGE_VERIFICATION_CODE_TYPE);
        String key = shortMessagePrefix + loginMobile;
        String value = cache.getValue(key);
        return value;
    }

    @Override
    public BaseDao<LoginUser> getBaseDao() {
        return null;
    }
}
