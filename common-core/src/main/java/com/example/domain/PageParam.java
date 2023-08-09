package com.example.domain;

public class PageParam {
    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    /**
     * 进行排序的字段
     */
    private String orderField;

    /**
     * 排序类型：desc降序，升序,默认降序
     */
    private String seType = "desc";

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

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getSeType() {
        return seType;
    }

    public void setSeType(String seType) {
        this.seType = seType;
    }
}