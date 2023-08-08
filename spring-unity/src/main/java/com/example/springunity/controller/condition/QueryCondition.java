package com.example.springunity.controller.condition;

import org.apache.commons.lang3.StringUtils;

public class QueryCondition {
    private final Integer DEFAULT_PAGE_NUM = 1;

    private final Integer DEFAULT_PAGE_SIZE = 25;

    protected Integer pageNum = DEFAULT_PAGE_NUM;

    protected Integer pageSize = DEFAULT_PAGE_SIZE;

    private String sortName;

    private String sortType;

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

    public void reset() {
        pageNum = DEFAULT_PAGE_NUM;
        pageSize = DEFAULT_PAGE_SIZE;
        sortName = null;
        sortType = null;
    }

    public void reset(Integer pageSize) {
        this.pageNum = 1;
        this.pageSize = pageSize;
        this.sortName = null;
        this.sortType = null;
    }

    public void setDefault() {
        if (getPageNum() == null) {
            setPageNum(DEFAULT_PAGE_NUM);
        }
        if (getPageSize() == null) {
            setPageSize(DEFAULT_PAGE_SIZE);
        }
    }

    public void setDefault(Integer pageSize) {
        if (getPageNum() == null) {
            setPageNum(DEFAULT_PAGE_NUM);
        }
        if (getPageSize() == null) {
            setPageSize(pageSize);
        }
    }

    public String getSortName() {
        return sortName;
    }

    public String getSortSql() {
        String sortTypeDefault = StringUtils.isNotBlank(sortType) ? sortType : "asc";
        return StringUtils.isNotBlank(sortName) ? String.format("%s %s", sortName, sortTypeDefault) : null;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}