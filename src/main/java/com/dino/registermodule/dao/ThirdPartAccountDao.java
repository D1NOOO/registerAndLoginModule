package com.dino.registermodule.dao;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.domain.entity.ThirdPartAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThirdPartAccountDao extends BaseDao<ThirdPartAccount> {
    //用户第三方绑定
    void bindThirdPartAccount(ThirdPartAccount thirdPartAccount);
    //通过id查找第三方用户数量
    int countByOpenId(@Param("openId") String openId);
    //通过loginId查找第三方用户列表
    List<ThirdPartAccount> selectThirdPartAccountByLoginId(@Param("loginId") String loginId);
    //更新第三方表
   void updateLoginId(@Param("delLoginId") String delLoginId, @Param("newLoginId") String newLoginId) ;
   //用户取消绑定第三方
    void cancelBindThirdPartAccount(@Param("openId") String openId);

    void batchInsert(List<ThirdPartAccount> thirdPartAccountList);
}
