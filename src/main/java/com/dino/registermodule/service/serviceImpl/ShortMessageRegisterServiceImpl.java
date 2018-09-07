package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.LoginUserConstant;
import com.dino.registermodule.base.NumberConstant;
import com.dino.registermodule.config.RedisComponent;
import com.dino.registermodule.domain.entity.LoginRef;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.service.ShortMessageRegisterService;
import com.dino.registermodule.service.VerificationCodeService;
import com.dino.registermodule.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.dino.registermodule.base.LoginUserConstant.RedisShortMessagePrefix.REGISTER_TYPE_PREFIX;


/**
 * 通过手机注册用户
 */
@Service
public class ShortMessageRegisterServiceImpl implements ShortMessageRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(ShortMessageRegisterServiceImpl.class);

    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private RedisComponent redisComponent;
    @Override
    public LoginUserRegisterResult register(String loginMobile, String verificationCode) {
        logger.debug("手机注册");
        if (StringUtils.isEmpty(loginMobile) || !RegexUtils.checkMobile(loginMobile)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "手机号码格式不正确");
        }
        if (StringUtils.isEmpty(verificationCode)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "验证码不能为空");
        }
        if (this.checkLoginName(loginMobile)) {
            //手机号已被注册
            //throw new LoginUserExistException();
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"用户已存在！");
        }
        if(!verificationCodeService.checkRegisterVerificationCode(loginMobile,verificationCode)){
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"验证码不正确");
        }
        String loginId = this.loginUserService.getPrimaryKey();
        String userId = this.loginUserService.getPrimaryKey();
        String loginRefId = this.loginUserService.getPrimaryKey();
        LoginRef loginRef = new LoginRef();
        loginRef.setId(loginRefId);
        loginRef.setLoginId(loginId);
        loginRef.setUserId(userId);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(loginId);
        loginUser.setLoginMobile(loginMobile);
        loginUser.setLoginStatus(LoginUserConstant.LoginUserStatusType.NORMAL_STATUS);
        loginUser.setLoginUserType(LoginUserConstant.LoginUserType.SHORT_MESSAGE);
        loginUser.setLoginRef(loginRef);
        loginUserService.save(loginUser);
        LoginUserRegisterResult result = new LoginUserRegisterResult();
        result.setLoginId(loginId);
        result.setUserId(userId);
        redisComponent.remove(REGISTER_TYPE_PREFIX+loginMobile);
        return result;
    }

    /**
     * 通过手机号查找用户数量
     * @param loginMobile
     * @return
     */
    @Override
    public boolean checkLoginName(String loginMobile) {
        int count = this.loginUserService.countByLoginMobile(loginMobile);
        return count > NumberConstant.Int.INT_ZERO;
    }

    @Override
    public BaseDao<LoginUser> getBaseDao() {
        return null;
    }
}
