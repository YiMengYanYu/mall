package com.ly.utils;

import java.util.List;

/**
 * @author YiMeng
 * @DateTime: 2022/8/15 19:54
 * @Description: TODO
 */
public class PageUtil<T> {

    private List<T> list;
    /**
     * 当前下标
     */
    private Integer index=0;
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 是否有上一个
     */
    private Boolean isHasPrev;
    /**
     * 是否有下一个
     */
    private Boolean isHasNext;

    public Boolean isHasNext() {
        return isHasNext;
    }
    public Boolean isHasPrev() {
        return isHasPrev;
    }

    public Boolean getHasPrev() {
        return isHasPrev;
    }

    public void setHasPrev(Boolean hasPrev) {
        isHasPrev = hasPrev;
    }

    public Boolean getHasNext() {
        return isHasNext;
    }

    public void setHasNext(Boolean hasNext) {
        isHasNext = hasNext;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
