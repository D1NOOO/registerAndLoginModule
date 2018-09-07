package com.dino.registermodule.base;

public interface LoginUserConstant {
    String APPLICATION_NAME="LOGIN-USER-SERVICE";


    /**
     * 登录类型
     */
    interface LoginUserType {
        /**NORMAL：正常登录(邮箱)*/
        String NORMAL = "1";
        /**SHORT_MESSAGE：短信登录*/
        String SHORT_MESSAGE = "2";
        /**THIRD_PART：第三方登录*/
        String THIRD_PART = "3";

    }

    /**
     * 短信类型
     */
    interface ShortMessageType{
        String LOGIN_TYPE = "1";
        String REGISTER_TYPE = "2";
        String FORGET_PASSWORD_TYPE="3";
        String BIND_LOGIN_USER_TYPE = "4";
        String CANCEL_BIND_LOGIN_USER_TYPE = "5";
    }

    interface RedisShortMessagePrefix{
        String LOGIN_TYPE_PREFIX = "LOGIN_";
        String REGISTER_TYPE_PREFIX = "REGISTER_";
        String FORGET_PASSWORD_TYPE_PREFIX = "FORGET_PASSWORD_";
        String BIND_LOGIN_USER_TYPE_PREFIX = "BIND_LOGIN_USER_";
        String CANCEL_BIND_LOGIN_USER_TYPE_PREFIX = "CANCEL_BIND_LOGIN_USER_" ;
    }

    interface LoginShortMessageStatus{
        String SEND = "1";
        String PROVING = "2";
        String INVALIDATE = "3";
    }

    /**
     * 发送邮件类型
     */
    interface LoginUserMailType{
        /**FORGET_PASSWORD_TYPE:找回密码*/
        String FORGET_PASSWORD_TYPE = "1";
        /**BIND_MAIL_TYPE:绑定邮箱*/
        String BIND_MAIL_TYPE = "2";
        /**CANCEL_BIND_MAIL_TYPE:取消绑定邮箱*/
        String CANCEL_BIND_MAIL_TYPE = "3";
    }

    /**
     * Redis Login User Mail Prefix
     */
    interface RedisLoginUserMailPrefix{
        /**FORGET_PASSWORD_TYPE_PREFIX:找回密码前缀*/
        String FORGET_PASSWORD_TYPE_PREFIX="MAIL_FORGET_PASSWORD_";
        /**BIND_MAIL_TYPE_PREFIX:绑定邮箱前缀*/
        String BIND_MAIL_TYPE_PREFIX="MAIL_BIND_MAIL_";
        /**CANCEL_BIND_MAIL_TYPE_PREFIX:取消绑定邮箱前缀*/
        String CANCEL_BIND_MAIL_TYPE_PREFIX="MAIL_CANCEL_BIND_MAIL_";

    }
    /**
     * 用户存在状态类型
     */
     interface LoginUserStatusType{
         /**正常状态*/
         String NORMAL_STATUS="1";
         /**冻结*/
         String FREEZE_STATUS="2";
         /**密码次数输入过多*/
         String MORE_TIMES_STSTUS="3";
         /**废弃*/
         String  ABANDON_STATUS="4";
    }

}
