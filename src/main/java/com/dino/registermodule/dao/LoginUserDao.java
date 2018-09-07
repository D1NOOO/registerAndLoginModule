package com.dino.registermodule.dao;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.domain.entity.LoginUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface LoginUserDao extends BaseDao<LoginUser> {
    //查询用户信息
    LoginUser selectLoginUser(Map<String, String> loginUserParam);
    //查找用户数量
    int countByLoginName(Map<String, String> loginUserParam);
    //手机绑定用户
    void bindLoginUserMobile(@Param("loginId") String loginId, @Param("loginMobile") String loginMobile);
    //取消手机绑定用户
    void cancelBindLoginUserMobile(@Param("loginId") String loginId, @Param("loginMobile") String loginMobile);
    //更新密码
    void updatePassword(@Param("loginId") String loginId, @Param("password") String password);
    //邮箱绑定用户
    void bindLoginUserMail(@Param("loginId") String loginId, @Param("loginMail") String loginMail);
    //取消邮箱绑定用户
    void cancelBindLoginUserMail(@Param("loginId") String loginId, @Param("loginMail") String loginMail);
    //更新用户登录状态
    void updateLoginUserStatus(@Param("loginId") String loginId, @Param("loginStatus") String loginStatus);

}
