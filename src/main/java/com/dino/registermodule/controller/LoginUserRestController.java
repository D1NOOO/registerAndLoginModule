package com.dino.registermodule.controller;

import com.dino.registermodule.base.RestRequest;
import com.dino.registermodule.base.RestResponse;
import com.dino.registermodule.component.LoginComponent;
import com.dino.registermodule.domain.param.NormalLoginParam;
import com.dino.registermodule.domain.param.ShortMessageLoginParam;
import com.dino.registermodule.domain.param.ThirdPartyLoginParam;
import com.dino.registermodule.domain.result.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2018/8/24 14:33
 */
@RestController
@Api(produces = "application/json",tags = "LoginUserRestController",description="登录接口")
public class LoginUserRestController extends BaseRestController{
    private static final String EMAIL_LOGIN_URL = "/email/login";
    private static final String SHORT_MESSAGE_LOGIN_URL = "/short/message/login";
    private static final String THIRD_PART_LOGIN_URL = "/third/part/login";

    @Autowired
    private LoginComponent loginComponent;

    @ApiOperation(value="邮箱正常登录" , notes = "根据LoginComponent调用正常登录方法")
    @RequestMapping(value = EMAIL_LOGIN_URL, method = RequestMethod.POST)
    public RestResponse<LoginResult> emailLogin(@RequestBody RestRequest<NormalLoginParam> request) {
        NormalLoginParam loginParam = request.getParam();
        LoginResult result = loginComponent.login(loginParam);
        return super.createRestResponse(result);
    }

    @ApiOperation(value="短信验证码登录" , notes = "根据LoginComponent调用短信登录方法")
    @RequestMapping(value = SHORT_MESSAGE_LOGIN_URL , method = RequestMethod.POST)
    public@ResponseBody
    RestResponse<LoginResult> shortMessageLogin(@RequestBody RestRequest<ShortMessageLoginParam> request) {
        ShortMessageLoginParam loginParam = request.getParam();
        LoginResult result = loginComponent.login(loginParam);
        return super.createRestResponse(result);
    }

    @ApiOperation(value="第三方登录" , notes = "根据LoginComponent调用第三方登录方法")
    @RequestMapping(value = THIRD_PART_LOGIN_URL , method = RequestMethod.POST)
    public@ResponseBody RestResponse<LoginResult> thirdPartyLogin(@RequestBody RestRequest<ThirdPartyLoginParam> request) {
        ThirdPartyLoginParam loginParam = request.getParam();
        LoginResult result = loginComponent.login(loginParam);
        return super.createRestResponse(result);
    }

}
