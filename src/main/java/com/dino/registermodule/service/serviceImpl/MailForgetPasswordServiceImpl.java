package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.NumberConstant;
import com.dino.registermodule.config.RedisComponent;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.exception.NotFindLoginUserException;
import com.dino.registermodule.service.ForgetPasswordService;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.service.VerificationCodeService;
import com.dino.registermodule.utils.MD5Utils;
import com.dino.registermodule.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.dino.registermodule.base.LoginUserConstant.RedisLoginUserMailPrefix.FORGET_PASSWORD_TYPE_PREFIX;


/**
 * 邮箱找回密码
 */
@Service("MailForgetPasswordServiceImpl")
public class MailForgetPasswordServiceImpl extends ForgetPasswordService {
    private static final Logger logger = LoggerFactory.getLogger(MailForgetPasswordServiceImpl.class);

    //找回密码URL有效时间
    private static long MAIL_FORGET_PASSWORD_EFFECTIVE_TIME = 2 * 24 * 60 * 60;
    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private RedisComponent redisComponent;

    @Override
    //发送邮箱验证码
    public void sendMailVerificationCode(String loginName) {
        logger.debug("发送邮箱验证码");
        if (!this.checkLoginName(loginName)) {
            throw new NotFindLoginUserException();
        }
        LoginUser loginUser = this.loginUserService.getLoginUserByLoginMail(loginName);
        String loginId = loginUser.getId();
        String verificationCode = verificationCodeService.createRandom(NUMBER_FLAG, VERIFICATION_CODE_LENGTH);
        //存储到Redis中
        redisComponent.set(FORGET_PASSWORD_PREFIX + loginUser.getLoginMail(), verificationCode, MAIL_FORGET_PASSWORD_EFFECTIVE_TIME);
        //TODO 邮箱发送l.,x

    }

    @Override
    //邮箱忘记密码
    public void mailForgetPassword(String loginMail, String verificationCode, String password) {
        if (StringUtils.isEmpty(loginMail) || !RegexUtils.checkMail(loginMail)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "邮箱不正确");
        }
        if (StringUtils.isEmpty(password) || !RegexUtils.checkPassword(password)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "密码格式错误");
        }
        if (StringUtils.isEmpty(verificationCode)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "请输入验证码");
        }
        LoginUser loginUser = this.loginUserService.getLoginUserByLoginMail(loginMail);
        if (!this.checkLoginName(loginMail)) {
            //邮箱用户不存在
            //throw new NotFindLoginUserException();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"用户不存在！");
        }
        //从redis获取验证码
        Map dbVerificationCode = redisComponent.getMap(FORGET_PASSWORD_TYPE_PREFIX + loginUser.getLoginMail());
        if (dbVerificationCode==null||StringUtils.isEmpty(dbVerificationCode.get("verificationCode"))) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "验证码已过期");
        }
        if (!verificationCode.equalsIgnoreCase(dbVerificationCode.get("verificationCode").toString())) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "验证码错误");
        }
        this.loginUserService.modifyPassword(loginUser.getId(), MD5Utils.hash(password));
    }

    @Override
    protected boolean checkLoginName(String loginName) {
        return loginUserService.countByLoginMail(loginName) > NumberConstant.Int.INT_ZERO;
    }
}
