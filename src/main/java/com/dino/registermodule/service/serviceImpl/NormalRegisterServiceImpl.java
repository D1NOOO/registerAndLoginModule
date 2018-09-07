package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.base.HttpStatus;
import com.dino.registermodule.base.LoginUserConstant;
import com.dino.registermodule.base.NumberConstant;
import com.dino.registermodule.domain.entity.LoginRef;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import com.dino.registermodule.exception.BusinessException;
import com.dino.registermodule.service.LoginUserService;
import com.dino.registermodule.service.NormalRegisterService;
import com.dino.registermodule.utils.MD5Utils;
import com.dino.registermodule.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户正常注册
 */
@Service
public class NormalRegisterServiceImpl implements NormalRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(NormalRegisterServiceImpl.class);

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public LoginUserRegisterResult register(String loginMail, String password) {
        logger.debug("用户正常注册");
        if (StringUtils.isEmpty(loginMail) || !RegexUtils.checkMail(loginMail)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "邮箱格式不正确");
        }
        if (StringUtils.isEmpty(password) || !RegexUtils.checkPassword(password)) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "密码格式不正确");
        }
        //查看邮箱是否存在
        if (this.checkLoginName(loginMail)) {
            //邮箱已被注册
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"用户已存在！");
        }
        String loginId = this.loginUserService.getPrimaryKey();
        String loginRefId = this.loginUserService.getPrimaryKey();
        String userId = this.loginUserService.getPrimaryKey();
        LoginRef loginRef = new LoginRef();
        loginRef.setId(loginRefId);
        loginRef.setLoginId(loginId);
        loginRef.setUserId(userId);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(loginId);
        loginUser.setLoginMail(loginMail);
        loginUser.setPassword(MD5Utils.hash(password));//MD5加密
        loginUser.setLoginStatus(LoginUserConstant.LoginUserStatusType.NORMAL_STATUS);
        loginUser.setLoginUserType(LoginUserConstant.LoginUserType.NORMAL);
        loginUser.setLoginRef(loginRef);
        loginUserService.save(loginUser);
        LoginUserRegisterResult result = new LoginUserRegisterResult();
        result.setLoginId(loginId);
        result.setUserId(userId);
        return result;
    }

    /**
     * 判断邮箱是否存在
     * @param loginMail
     * @return
     */
    @Override
    public boolean checkLoginName(String loginMail) {
        int count = this.loginUserService.countByLoginMail(loginMail);
        return count > NumberConstant.Int.INT_ZERO;
    }

    @Override
    public BaseDao<LoginUser> getBaseDao() {
        return null;
    }
}
