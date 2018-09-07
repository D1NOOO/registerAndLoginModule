package com.dino.registermodule.component;

import com.dino.registermodule.domain.param.LoginParam;
import com.dino.registermodule.domain.param.NormalLoginParam;
import com.dino.registermodule.domain.param.ShortMessageLoginParam;
import com.dino.registermodule.domain.param.ThirdPartyLoginParam;
import com.dino.registermodule.domain.result.LoginResult;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.NormalLoginService;
import com.dino.registermodule.service.ShortMessageLoginService;
import com.dino.registermodule.service.ThirdPartyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户登录实现
 */
@Component
public final class LoginComponent {
    @Autowired
    private NormalLoginService normalLoginService;
    @Autowired
    private ShortMessageLoginService shortMessageLoginService;
    @Autowired
    private ThirdPartyLoginService thirdPartyLoginService;

    public LoginResult login(LoginParam loginParam) {
        LoginResult result = null;
        if (loginParam instanceof NormalLoginParam) {
            /*邮箱登录*/
            String loginName = ((NormalLoginParam) loginParam).getLoginName();
            String password = ((NormalLoginParam) loginParam).getPassword();
            result = this.normalLoginService.login(loginName, password);
        } else if (loginParam instanceof ShortMessageLoginParam) {
            /*手机登录*/
            String loginMobile = ((ShortMessageLoginParam) loginParam).getLoginMobile();
            String verificationCode = ((ShortMessageLoginParam) loginParam).getVerificationCode();
            result = this.shortMessageLoginService.login(loginMobile, verificationCode);
        } else if (loginParam instanceof ThirdPartyLoginParam) {
            /*第三方登录*/
            String openId = ((ThirdPartyLoginParam) loginParam).getOpenId();
            String thirdPartType = ((ThirdPartyLoginParam) loginParam).getThirdPartType();
            result = this.thirdPartyLoginService.login(openId,thirdPartType);
        }else{
            throw new BusinessException();
        }
        return result;
    }


}
