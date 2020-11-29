package com.seu.zqy.bean;

import java.util.List;

/**
 * pages是分页的模型对象
 * @param <T>  是数据具体类型
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private Integer pageno;
    //总页码
    private Integer pageTotalno;
    //当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private Integer pageTotalcount;
    //当前页数据
    private List<T> items;

    //分页条的请求地址
    private String url;

    public Page() {
    }

    public Page(Integer pageno, Integer pageTotalno, Integer pageSize, Integer pageTotalcount, List<T> items, String url) {
        this.pageno = pageno;
        this.pageTotalno = pageTotalno;
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
        this.pageTotalcount = pageTotalcount;
        this.items = items;
        this.url = url;
    }

    public Page(Integer pageno, Integer pageTotalno, Integer pageTotalcount, List<T> items, String url) {
        this.pageno = pageno;
        this.pageTotalno = pageTotalno;
        this.pageTotalcount = pageTotalcount;
        this.items = items;
        this.url = url;
    }

    public  Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalcount() {
        return pageTotalcount;
    }

    public void setPageTotalcount(Integer pageTotalcount) {
        this.pageTotalcount = pageTotalcount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageno() {
        return this.pageno;
    }

    public void setPageno(Integer pageno) {
        //方法参数和属性重命，属性要用this来引用，避免冲突
        if (pageno<1){
            this.pageno=1;
        }else if (pageno>pageTotalno){
            this.pageno=pageTotalno;
        }else {
            this.pageno = pageno;
        }
    }

    public Integer getPageTotalno() {
        return pageTotalno;
    }

    public void setPageTotalno(Integer pageTotalno) {
        this.pageTotalno = pageTotalno;
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
                "pageno=" + pageno +
                ", pageTotalno=" + pageTotalno +
                ", pageSize=" + pageSize +
                ", pageTotalcount=" + pageTotalcount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
