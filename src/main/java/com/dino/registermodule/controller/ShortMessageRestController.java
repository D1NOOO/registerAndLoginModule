package com.dino.registermodule.controller;

import com.dino.registermodule.base.BaseRestResponse;
import com.dino.registermodule.base.RestRequest;
import com.dino.registermodule.component.ShortMessageComponent;
import com.dino.registermodule.domain.param.ShortMessageVerificationCodeParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录模块相关短信发送接口实现
 */
@RestController
@Api(produces = "application/json",tags = "ShortMessageRestController",description="短信发送接口")
public class ShortMessageRestController extends BaseRestController{
    private static final Logger logger = LoggerFactory.getLogger(ShortMessageRestController.class);

    private static final String SEND_SHORT_MESSAGE_URL = "/send/short/message";

    @Autowired
    private ShortMessageComponent shortMessageComponent;

    @RequestMapping(value = SEND_SHORT_MESSAGE_URL,method = RequestMethod.POST)
    @ApiOperation(value="发送短信", notes="根据ShortMessageTypeS(1:登录短信，2：注册短信，3：忘记密码短信，4：登录绑定短信，5：取消绑定短信)发送不同类型的短信")
    public BaseRestResponse sendShortMessage(@RequestBody RestRequest<ShortMessageVerificationCodeParam> request) {
        logger.debug("发送短信");
        ShortMessageVerificationCodeParam shortMessageVerificationCodeParam = request.getParam();
        String loginMobile = shortMessageVerificationCodeParam.getLoginMobile();
        String shortMessageType = shortMessageVerificationCodeParam.getShortMessageType();
        this.shortMessageComponent.sendShortMessage(loginMobile,shortMessageType);
        return this.createRestResponse();
    }
}
