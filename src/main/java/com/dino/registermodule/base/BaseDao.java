package com.dino.registermodule.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Base Dao
 * @param <T> 实体对象
 */
public interface BaseDao<T extends BaseEntity> {

    int deleteByPrimaryKey(String id);

    int insert(T entity);

    int insertSelective(T entity);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T entity);

    int updateByPrimaryKey(T entity);

    /**
     * 获得分页数据
     * @param param 分页参数
     * @return 分页数据集合
     */
    List<T> selectPageList(BaseParam param);

    <R extends BaseResult> List<R> selectPageList(@Param("param") BaseParam param, @Param("pageParam") PageParam pageParam,
                                                  @Param("orderFieldParamList") List<OrderFieldParam> orderFieldParamList);

    int selectPageCount(BaseParam param);
}
