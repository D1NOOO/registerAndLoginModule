package com.dino.registermodule.controller;

import com.dino.registermodule.base.RestRequest;
import com.dino.registermodule.base.RestResponse;
import com.dino.registermodule.component.RegisterComponent;
import com.dino.registermodule.domain.param.LoginUserRegisterParam;
import com.dino.registermodule.domain.param.ShortMessageRegisterParam;
import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册接口
 * @author shenf
 * @date 2018/8/27 14:14
 */
@RestController
@Api(produces = "application/json",tags = "RegisterRestController",description="注册接口")
public class RegisterRestController extends BaseRestController{
    @Autowired
    private RegisterComponent registerComponent;

    private static final String MAIL_REGISTER_URL = "/mail/register/register";
    private static final String SHORT_MESSAGE_REGISTER_URL = "/short/message/register";

    @RequestMapping(value = MAIL_REGISTER_URL,method = RequestMethod.POST)
    @ApiOperation(value="正常注册(邮箱注册)" , notes = "根据RegisterComponent调用正常注册方法")
    public@ResponseBody
    RestResponse<LoginUserRegisterResult> loginUserRegister(@RequestBody RestRequest<LoginUserRegisterParam> request) {
        LoginUserRegisterParam registerParam = request.getParam();
        LoginUserRegisterResult result = this.registerComponent.register(registerParam);
        return super.createRestResponse(result);
    }

    @RequestMapping(value = SHORT_MESSAGE_REGISTER_URL,method = RequestMethod.POST)
    @ApiOperation(value="短信验证码注册" , notes = "根据RegisterComponent调用短信注册方法")
    public@ResponseBody RestResponse<LoginUserRegisterResult> shortMessageRegister(@RequestBody RestRequest<ShortMessageRegisterParam> request) {
        ShortMessageRegisterParam registerParam = request.getParam();
        LoginUserRegisterResult result = this.registerComponent.register(registerParam);
        return super.createRestResponse(result);
    }
}
