package com.dino.registermodule.service;

import com.dino.registermodule.base.NumberConstant;
import com.dino.registermodule.exception.SystemException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public abstract class ForgetPasswordService {
    public static boolean NUMBER_FLAG = Boolean.FALSE;
    public static int VERIFICATION_CODE_LENGTH = NumberConstant.Int.INT_TEN;
    public static String FORGET_PASSWORD_PREFIX = "FORGET_PASSWORD_";
    //短信忘记密码
    public void shortMessageForgetPassword(String loginMobile,String verificationCode,String password){
        throw new SystemException();
    }
    //邮箱忘记密码
    public void mailForgetPassword(String loginMail,String verificationCode,String password){
        throw new SystemException();
    }
    //邮箱发送验证码
    public void sendMailVerificationCode(String loginName) {
        throw new SystemException();
    }

    //根据登录名检查用户是否存在
    protected abstract boolean checkLoginName(String loginName);
}
