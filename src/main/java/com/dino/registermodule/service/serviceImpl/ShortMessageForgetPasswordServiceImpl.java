package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.NumberConstant;
import com.dino.registermodule.config.RedisComponent;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.ForgetPasswordService;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.utils.MD5Utils;
import com.dino.registermodule.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.dino.registermodule.base.LoginUserConstant.RedisShortMessagePrefix.FORGET_PASSWORD_TYPE_PREFIX;


/**
 * 通过短信找回密码
 */
@Service("ShortMessageForgetPasswordServiceImpl")
public class ShortMessageForgetPasswordServiceImpl extends ForgetPasswordService {
    private static final Logger logger = LoggerFactory.getLogger(ShortMessageForgetPasswordServiceImpl.class);

    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private RedisComponent redisComponent;

    @Override
    public void shortMessageForgetPassword(String loginMobile, String verificationCode, String password) {
        logger.debug("通过短信找回密码");
        if(StringUtils.isEmpty(loginMobile) || !RegexUtils.checkMobile(loginMobile)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"手机号码不正确！");
        }
        if(StringUtils.isEmpty(password) || !RegexUtils.checkPassword(password)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"密码格式错误");
        }
        if(StringUtils.isEmpty(verificationCode)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"请输入验证码");
        }
        LoginUser loginUser = this.loginUserService.getLoginUserByLoginMobile(loginMobile);
        if (loginUser == null) {
            //手机用户不存在
            //throw new NotFindLoginUserException();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"用户不存在！");
        }
        //从redis获取验证码
        String dbVerificationCode = redisComponent.get(FORGET_PASSWORD_TYPE_PREFIX + loginMobile);
        if(StringUtils.isEmpty(dbVerificationCode)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"验证码已过期");
        }
        if(!dbVerificationCode.equalsIgnoreCase(verificationCode)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"验证码错误");
        }
        this.loginUserService.modifyPassword(loginUser.getId(), MD5Utils.hash(password));
        redisComponent.remove(FORGET_PASSWORD_TYPE_PREFIX + loginMobile);
    }

    /**
     * 判断用户是否存在
     * @param loginName
     * @return
     */
    @Override
    protected boolean checkLoginName(String loginName) {
        return loginUserService.countByLoginMobile(loginName) > NumberConstant.Int.INT_ZERO;
    }
}
