package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.LoginUserConstant;
import com.dino.registermodule.config.RedisComponent;
import com.dino.registermodule.dao.ThirdPartAccountDao;
import com.dino.registermodule.domain.entity.LoginRef;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.domain.entity.ThirdPartAccount;
import com.dino.registermodule.domain.result.LoginResult;
import com.dino.registermodule.domain.result.ThirdPartAccountResult;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.service.ShortMessageLoginService;
import com.dino.registermodule.service.VerificationCodeService;
import com.dino.registermodule.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dino.registermodule.base.LoginUserConstant.RedisShortMessagePrefix.LOGIN_TYPE_PREFIX;


/**
 * 用户通过手机短信进行登录
 */
@Service
public class ShortMessageLoginServiceImpl implements ShortMessageLoginService {
    private static final Logger logger = LoggerFactory.getLogger(ShortMessageLoginServiceImpl.class);
    private static long TOKEN_EFFECTIVE_TIME = 100000000 * 60 * 60;
    public static String TOKEN = "LG_";
    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private ThirdPartAccountDao thirdPartAccountDao;

    @Override
    public LoginResult login(String loginMobile, String verificationCode) {
        logger.debug("手机短信验证码登录");
        if (StringUtils.isEmpty(loginMobile) || !RegexUtils.checkMobile(loginMobile)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "手机号不正确");
        }
        if (StringUtils.isEmpty(verificationCode) || !verificationCodeService.checkLoginVerificationCode(loginMobile, verificationCode)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "验证码不正确");
        }
        LoginResult loginResult = new LoginResult();
        //通过手机号拿到用户信息
        LoginUser loginUser = this.loginUserService.getLoginUserByLoginMobile(loginMobile);
        LoginRef loginRef;
        String loginId;
        String userId;
        String loginUserType = LoginUserConstant.LoginUserType.SHORT_MESSAGE;
        if (loginUser == null) {
            /*用户不存在新增用户到数据库*/
            loginResult.setRegister(false);
            loginId = this.loginUserService.getPrimaryKey();
            userId = this.loginUserService.getPrimaryKey();
            String loginRefId = this.loginUserService.getPrimaryKey();
            //Login User依赖User
            loginRef = new LoginRef();
            loginRef.setId(loginRefId);
            loginRef.setUserId(userId);
            loginRef.setLoginId(loginId);
            loginUser = new LoginUser();
            loginUser.setLoginMobile(loginMobile);
            loginUser.setLoginUserType(loginUserType);
            loginUser.setId(loginId);
            loginUser.setLoginRef(loginRef);
            this.loginUserService.save(loginUser);
        } else {
            /*用户存在返回longId userId*/
            loginResult.setRegister(true);
            userId = loginUser.getLoginRef().getUserId();
            loginId = loginUser.getId();
        }
        Map<String ,String> map = new HashMap<>();
        map.put("loginId",loginId);
        map.put("userId", userId);
        loginResult.setUserId(userId);
        loginResult.setLoginId(loginId);
        loginResult.setLoginUserType(loginUserType);
        //通过LoginId查询第三方列表
        List<ThirdPartAccount> thirdPartAccountList = this.thirdPartAccountDao.selectThirdPartAccountByLoginId(loginId);
        //填充List<ThirdPartAccountResult>信息
        List<ThirdPartAccountResult> thirdPartAccountResults = new ArrayList<>();
        for (ThirdPartAccount thirdPartAccount : thirdPartAccountList){
            ThirdPartAccountResult thirdPartAccountResult = new ThirdPartAccountResult();
            BeanUtils.copyProperties(thirdPartAccount,thirdPartAccountResult);
            thirdPartAccountResults.add(thirdPartAccountResult);
        }
        //将第三方列表信息塞入loginResult中
        loginResult.setThirdPartAccountResults(thirdPartAccountResults);
        loginResult.setToken(loginUserService.getPrimaryKey());
        /*将loginId userId放入redis*/
        redisComponent.set(TOKEN + loginResult.getToken(), map, TOKEN_EFFECTIVE_TIME );
        redisComponent.remove(LOGIN_TYPE_PREFIX + loginMobile);
        return loginResult;
    }

    @Override
    public BaseDao<LoginUser> getBaseDao() {
        return null;
    }
}
