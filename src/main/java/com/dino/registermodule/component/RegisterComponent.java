package com.dino.registermodule.component;

import com.dino.registermodule.domain.param.LoginUserRegisterParam;
import com.dino.registermodule.domain.param.RegisterParam;
import com.dino.registermodule.domain.param.ShortMessageRegisterParam;
import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.NormalRegisterService;
import com.dino.registermodule.service.ShortMessageRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户注册调用实现
 */
@Component
public final class RegisterComponent {
    @Autowired
    private NormalRegisterService normalRegisterService;
    @Autowired
    private ShortMessageRegisterService shortMessageRegisterService;

    public LoginUserRegisterResult register(RegisterParam registerParam) {
        LoginUserRegisterResult result = null;
        if (registerParam instanceof LoginUserRegisterParam) {
            //邮箱注册
            String loginMail = ((LoginUserRegisterParam) registerParam).getLoginMail();
            String password = ((LoginUserRegisterParam) registerParam).getPassword();
            result = this.normalRegisterService.register(loginMail,password);
        } else if (registerParam instanceof ShortMessageRegisterParam) {
            //手机短信注册
            String loginMobile = ((ShortMessageRegisterParam) registerParam).getLoginMobile();
            String verificationCode = ((ShortMessageRegisterParam) registerParam).getVerificationCode();
            result = this.shortMessageRegisterService.register(loginMobile,verificationCode);
        }else{
            throw new BusinessException();
        }
        return result;
    }


}
