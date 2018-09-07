package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.dao.LoginRefDao;
import com.dino.registermodule.dao.LoginUserDao;
import com.dino.registermodule.dao.ThirdPartAccountDao;
import com.dino.registermodule.domain.entity.LoginRef;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.domain.entity.ThirdPartAccount;
import com.dino.registermodule.service.LoginUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LoginUserServiceImpl implements LoginUserService {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);

    @Autowired
    private LoginUserDao loginUserDao;
    @Autowired
    private LoginRefDao loginRefDao;
    @Autowired
    private ThirdPartAccountDao thirdPartAccountDao;

    /**
     * 通过主键查找用户信息
     * @param primaryKey
     * @return
     */
    @Override
    public LoginUser findOne(String primaryKey) {
        logger.debug("通过主键查找用户信息");
        LoginUser loginUser = this.getBaseDao().selectByPrimaryKey(primaryKey);
        this.initLoginUserRef(loginUser);
        return loginUser;
    }

    /**
     * 通过邮箱拿到用户信息
     * @param loginMail
     * @return
     */
    @Override
    public LoginUser getLoginUserByLoginMail(String loginMail) {
        Map<String, String> loginUserParam = new HashMap<>();
        loginUserParam.put("loginMail", loginMail);
        LoginUser loginUser = loginUserDao.selectLoginUser(loginUserParam);
        this.initLoginUserRef(loginUser);

        return loginUser;
    }

    /**
     * 通过手机号码拿到用户信息
     * @param loginMobile
     * @return
     */
    @Override
    public LoginUser getLoginUserByLoginMobile(String loginMobile) {
        Map<String, String> loginUserParam = new HashMap<>();
        loginUserParam.put("loginMobile", loginMobile);
        LoginUser loginUser = loginUserDao.selectLoginUser(loginUserParam);
        this.initLoginUserRef(loginUser);
        return loginUser;
    }

    /**
     * 通过第三方id拿到用户信息
     * @param openId
     * @param thirdPartAccountType
     * @return
     */
    @Override
    public LoginUser getLoginUserByOpenId(String openId, String thirdPartAccountType) {
        Map<String, String> loginUserParam = new HashMap<>();
        loginUserParam.put("openId", openId);
        loginUserParam.put("thirdPartAccountType", thirdPartAccountType);
        LoginUser loginUser = loginUserDao.selectLoginUser(loginUserParam);
        this.initLoginUserRef(loginUser);
        return loginUser;
    }

    /**
     * 通过邮箱查找用户数量
     * @param loginMail
     * @return
     */
    @Override
    public int countByLoginMail(String loginMail) {
        Map<String, String> loginUserParam = new HashMap<>();
        loginUserParam.put("loginMail", loginMail);
        return this.loginUserDao.countByLoginName(loginUserParam);
    }

    /**
     * 通过手机号查找用户数量
     * @param loginMobile
     * @return
     */
    @Override
    public int countByLoginMobile(String loginMobile) {
        Map<String, String> loginUserParam = new HashMap<>();
        loginUserParam.put("loginMobile", loginMobile);
        return this.loginUserDao.countByLoginName(loginUserParam);
    }

    /**
     * 修改密码操作
     * @param loginId
     * @param password
     */
    @Override
    public void modifyPassword(String loginId, String password) {
        this.loginUserDao.updatePassword(loginId, password);
    }

    /**
     * 保存用户信息
     * @param entity
     */
    @Transactional
    @Override
    public void save(LoginUser entity) {
        this.loginUserDao.insert(entity);
        this.loginRefDao.insert(entity.getLoginRef());
        List<ThirdPartAccount> thirdPartAccountList = entity.getThirdPartAccountList();
        if (!CollectionUtils.isEmpty(thirdPartAccountList)) {
            this.thirdPartAccountDao.batchInsert(thirdPartAccountList);
        }
    }

    /**
     * 初始化用户影射信息
     * @param loginUser
     */
    private void initLoginUserRef(LoginUser loginUser) {
        if (loginUser == null) {
            return;
        }
        String loginId = loginUser.getId();
        LoginRef loginRef = this.loginRefDao.selectLoginRefByLoginId(loginId);
        loginUser.setLoginRef(loginRef);
    }

    @Override
    public BaseDao<LoginUser> getBaseDao() {
        return loginUserDao;
    }
}
