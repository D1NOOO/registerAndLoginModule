package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据库分页参数.
 *
 * @author jiang_nan
 */
public class PageParam extends BaseParam {
    @Getter
    @Setter
    private int currentPage;
    @Getter
    @Setter
    private int pageSize;
    @Getter
    @Setter
    private boolean paging;
    @Getter
    private int rowNumber;
    public PageParam() {

    }

    public PageParam(PageParam pageParam) {
        this.currentPage = pageParam.getCurrentPage();
        this.pageSize = pageParam.getPageSize();
        this.paging = pageParam.isPaging();
        this.rowNumber = (currentPage - NumberConstant.Int.INT_ONE) * pageSize;
    }


}
