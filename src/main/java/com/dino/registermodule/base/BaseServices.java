package com.dino.registermodule.base;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BaseServices<T extends BaseEntity>{

    @Transactional
    default void save(T entity){
        entity.setId(this.getPrimaryKey());
        entity.setCreateTime(new Date());
        this.getBaseDao().insert(entity);
    }

    default T findOne(String primaryKey){
        return this.getBaseDao().selectByPrimaryKey(primaryKey);
    }

    default <R> R findOneResult(String primaryKey){
        return null;
    }


    default void remove(String primaryKey){
        this.getBaseDao().deleteByPrimaryKey(primaryKey);
    }

    default void updateByPrimaryKeySelective(T entity){
        this.getBaseDao().updateByPrimaryKeySelective(entity);
    }

    default String getPrimaryKey(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    default <P extends BaseParam,R extends BaseResult> PageResult<R> findPage(P param, PageParam pageParam, List<OrderFieldParam> orderFieldParamList){
        pageParam = new PageParam(pageParam);
        int totalCount = this.getPageCount(param);
        int pageSize = pageParam.getPageSize();
        List<R> resultList = this.findPageList(param,pageParam,orderFieldParamList);
        PageResult<R> result = new PageResult<>();
        result.setItems(resultList);
        result.setTotalCount(totalCount);
        result.setCurrentPage(pageParam.getCurrentPage());
        result.setPageSize(pageSize);
        int totalPage = totalCount/pageSize;
        if(totalCount%pageSize!=0){
            totalPage += 1;
        }
        result.setTotalPage(totalPage);
        return result;
    }

    default <P extends BaseParam,R extends BaseResult> List<R> findPageList(P param, PageParam pageParam, List<OrderFieldParam> orderFieldParamList){
        return this.getBaseDao().selectPageList(param,pageParam,orderFieldParamList);
    }

    default <P extends BaseParam> int getPageCount(P param){
        return this.getBaseDao().selectPageCount(param);
    }

    BaseDao<T> getBaseDao();

}
