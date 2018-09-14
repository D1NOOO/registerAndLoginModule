package com.dino.registermodule.controller;

import com.dino.registermodule.base.BaseRestResponse;
import com.dino.registermodule.base.RestRequest;
import com.dino.registermodule.domain.param.ForgetPasswordParam;
import com.dino.registermodule.domain.param.ResetPasswordParam;
import com.dino.registermodule.domain.param.ShortMessageForgetPasswordParam;
import com.dino.registermodule.service.ForgetPasswordService;
import com.dino.registermodule.service.LoginUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author shenf
 * @date 2018/9/14 11:02
 */
@RestController
@Api(produces = "application/json",tags = "ForgetResetPasswordRestController",description="忘记密码/重置密码接口")
public class ForgetResetPasswordRestController extends BaseRestController {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserRestController.class);

    private static final String NORMAL_FORGET_PASSWORD_URL = "/normal/forget/password";
    private static final String SHORT_MESSAGE_FORGET_PASSWORD_URL = "/short/message/forget/password";
    private static final String RESET_PASSWORD_URL = "/reset/password";

    @Qualifier("MailForgetPasswordServiceImpl")
    @Autowired
    private ForgetPasswordService mailForgetPasswordService;
    @Qualifier("ShortMessageForgetPasswordServiceImpl")
    @Autowired
    private ForgetPasswordService shortMessageForgetPasswordService;
    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping(value = NORMAL_FORGET_PASSWORD_URL,method = RequestMethod.POST)
    @ApiOperation(value="正常忘记密码" , notes = "根据ForgetPasswordService调用正常忘记密码方法")
    public BaseRestResponse forgetPassword(@RequestBody RestRequest<ForgetPasswordParam> request) {
        logger.debug("正常忘记密码");
        ForgetPasswordParam param = request.getParam();
        String loginMail = param.getLoginMail();
        this.mailForgetPasswordService.mailForgetPassword(loginMail,param.getVerificationCode(),param.getNewPassword());


        return this.createRestResponse();
    }

    @RequestMapping(value = SHORT_MESSAGE_FORGET_PASSWORD_URL,method = RequestMethod.POST)
    @ApiOperation(value="短信忘记密码" , notes = "根据ForgetPasswordService调用短信忘记密码方法")
    public@ResponseBody BaseRestResponse shortMessageForgetPassword(@RequestBody RestRequest<ShortMessageForgetPasswordParam> request) {
        logger.debug("短信忘记密码");
        ShortMessageForgetPasswordParam param = request.getParam();
        shortMessageForgetPasswordService.shortMessageForgetPassword(param.getLoginMobile(),param.getVerificationCode(),param.getNewPassword());

        return this.createRestResponse();
    }

    @RequestMapping(value = RESET_PASSWORD_URL,method = RequestMethod.POST)
    @ApiOperation(value="重置密码" , notes = "根据LoginUserService调用重置密码方法")
    public@ResponseBody BaseRestResponse resetPassword(@RequestBody RestRequest<ResetPasswordParam> request) {
        logger.debug("重置密码");
        ResetPasswordParam param = request.getParam();
        loginUserService.modifyPassword(param.getLoginId(),param.getNewPassword());

        return this.createRestResponse();
    }
}
