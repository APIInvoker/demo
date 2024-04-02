package com.example.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @since 2023-12-27
 */
public class PageInfo implements Serializable
{
    private static final long serialVersionUID = 5675084397100799553L;

    private static final int MAX_PAGE_SIZE_1 = 100;
    private static final int MAX_PAGE_SIZE_2 = 1000000;

    private int pageNum = 1; // 当前页码
    private int pageSize = 25; // 每页条数
    private int totalCount = 0; // 总条数

    public PageInfo()
    {
    }

    public PageInfo(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public PageInfo(int pageNum, int pageSize)
    {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageInfo(int totalCount, int pageNum, int pageSize)
    {
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static PageInfo buildExportPageInfo1()
    {
        return new PageInfo(MAX_PAGE_SIZE_1);
    }

    public static PageInfo buildExportPageInfo2()
    {
        return new PageInfo(MAX_PAGE_SIZE_2);
    }

    public <T> Page<T> buildPage()
    {
        return new Page<>(new ArrayList<>(), new PageInfo());
    }

    public <T> Page<T> buildPage(List<T> list, int totalCount)
    {
        return new Page<>(list, new PageInfo(totalCount, pageNum, pageSize));
    }

    public <T> Page<T> buildPage(List<T> list, PageInfo pageInfo)
    {
        return new Page<>(list, pageInfo);
    }

    public int getOffset()
    {
        return pageSize * (pageNum - 1);
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }
}
