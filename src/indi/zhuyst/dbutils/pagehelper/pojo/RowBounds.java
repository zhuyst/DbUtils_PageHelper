package indi.zhuyst.dbutils.pagehelper.pojo;

import indi.zhuyst.dbutils.pagehelper.enums.PageEnum;

public class RowBounds {
    private Integer pageNum;

    private Integer pageSize;

    private PageEnum pageEnum;

    public RowBounds() {}

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageEnum getPageEnum() {
        return pageEnum;
    }

    public void setPageEnum(PageEnum pageEnum) {
        this.pageEnum = pageEnum;
    }
}
