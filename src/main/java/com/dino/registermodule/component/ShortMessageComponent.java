package com.dino.registermodule.component;

import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.LoginUserConstant;
import com.dino.registermodule.config.RedisComponent;
import com.dino.registermodule.dao.LoginShortMessageDao;
import com.dino.registermodule.domain.entity.LoginShortMessage;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.handler.MessageSendHandler;
import com.dino.registermodule.service.LoginShortMessageService;
import com.dino.registermodule.service.VerificationCodeService;
import com.dino.registermodule.utils.DateTimeUtils;
import com.dino.registermodule.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.dino.registermodule.base.LoginUserConstant.LoginShortMessageStatus.SEND;
import static com.dino.registermodule.base.LoginUserConstant.RedisShortMessagePrefix.*;


/**
 * 短信发送调用实现
 */
@Component
public final class ShortMessageComponent {
    /**
     * 是否数字格式
     */
    private static boolean NUMBER_FLAG = true;
    /**
     * 验证码长度
     */
    private static int VERIFICATION_CODE_LENGTH = 6;
    /**
     * 短信验证码存储的时效
     */
    private static long SHORT_MESSAGE_TIMEOUT = 15 * 60;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private LoginShortMessageService loginShortMessageService;
    @Autowired
    private MessageSendHandler messageSendHandler;

    @Autowired
    private LoginShortMessageDao loginShortMessageDao;

    public void sendShortMessage(String loginMobile, String shortMessageType) {
        if(StringUtils.isEmpty(loginMobile) || !RegexUtils.checkMobile(loginMobile)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"手机号码格式不正确");
        }
        String redisShortMessagePrefix = null;
        //判断短信发送类型
        if (LoginUserConstant.ShortMessageType.LOGIN_TYPE.equalsIgnoreCase(shortMessageType)) {
            redisShortMessagePrefix = LOGIN_TYPE_PREFIX;
        } else if (LoginUserConstant.ShortMessageType.REGISTER_TYPE.equalsIgnoreCase(shortMessageType)) {
            redisShortMessagePrefix = REGISTER_TYPE_PREFIX;
        } else if (LoginUserConstant.ShortMessageType.FORGET_PASSWORD_TYPE.equalsIgnoreCase(shortMessageType)) {
            redisShortMessagePrefix = FORGET_PASSWORD_TYPE_PREFIX;
        } else if (LoginUserConstant.ShortMessageType.BIND_LOGIN_USER_TYPE.equalsIgnoreCase(shortMessageType)) {
            redisShortMessagePrefix = BIND_LOGIN_USER_TYPE_PREFIX;
        } else if(LoginUserConstant.ShortMessageType.CANCEL_BIND_LOGIN_USER_TYPE.equalsIgnoreCase(shortMessageType)){
            redisShortMessagePrefix = CANCEL_BIND_LOGIN_USER_TYPE_PREFIX;
        }else {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"短信验证码类型不匹配");
        }

        String verificationCode = this.redisComponent.get(redisShortMessagePrefix + loginMobile);
        if (StringUtils.isEmpty(verificationCode)) {
            verificationCode = verificationCodeService.createRandom(NUMBER_FLAG, VERIFICATION_CODE_LENGTH);
            this.redisComponent.set(redisShortMessagePrefix + loginMobile, verificationCode, SHORT_MESSAGE_TIMEOUT);
            LoginShortMessage loginShortMessage = new LoginShortMessage();
            long currentlyDate = DateTimeUtils.getCurrentTimeMillis();
            long endDateTime = currentlyDate + SHORT_MESSAGE_TIMEOUT;
            loginShortMessage.setLoginMobile(loginMobile);
            loginShortMessage.setShortMessageType(shortMessageType);
            loginShortMessage.setVerificationCode(verificationCode);
            loginShortMessage.setSendDateTime(currentlyDate);
            loginShortMessage.setEndDateTime(endDateTime);
            loginShortMessage.setStatus(SEND);
            this.loginShortMessageService.save(loginShortMessage);
        }

        //调用短信发送接口
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("phone",loginMobile);
        paramMap.put("code",verificationCode);
        try{
            messageSendHandler.msgSend(paramMap);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
