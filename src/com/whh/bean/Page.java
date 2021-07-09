package com.whh.bean;

import java.util.List;

/*
分页bean对象
<T>可针对不同的模块分页,如图书Book 用户User
 */
public class Page<T> {
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    public static final Integer PAGE_SIZE = 4;
    private Integer pageSize = PAGE_SIZE;//当前页显示条数
    private Integer pageTotalCount;//总记录数
    private List<T> items;//当前页数据 books

    private String url;//分页条的请求地址


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //数据边界有效检查
//        if(pageNo > 1){
//            pageNo = 1;
//        }if(pageNo > pageTotal){
//            pageNo = pageTotal;
//        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
}
