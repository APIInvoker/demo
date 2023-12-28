package com.example.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @since 2023-12-27
 */
public class Page<E> implements Serializable {
    private final List<E> listData;
    private final PageInfo pageInfo;

    Page(List<E> listData, PageInfo pageInfo) {
        this.listData = listData != null ? listData : new ArrayList<>();
        this.pageInfo = pageInfo != null ? pageInfo : new PageInfo();
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<E> getListData() {
        return listData;
    }
}