package com.te.micoservice.model;

import java.util.List;

/**
 * Created by cxj4842 on 2018/5/10.
 */
public class SearchModel<T> {
    /// <summary>
    /// 页索引
    /// </summary>
    private int pageIndex = 1;

    /// <summary>
    /// 页面大小
    /// </summary>
    private int pageSize=10;

    /// <summary>
    /// 总记录数
    /// </summary>
    private int recordCount;

    /// <summary>
    /// 总页数
    /// </summary>
    private int pageCount;

    private int listCount = 0;

    /// <summary>
    /// 数据实体
    /// </summary>
    private List<T> resultList;

    /// <summary>
    /// 请求查询实体
    /// </summary>
    private T requestModel;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
       this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public T getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(T requestModel) {
        this.requestModel = requestModel;
    }
}
