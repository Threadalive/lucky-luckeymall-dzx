package com.lucky.entity;


import java.util.List;

/**
 * @Description 页面类
 *
 * @param <E> 列表类型
 * @Author zhenxing.dong
 * @Date 2019/8/21 10:28
 */
public class PageBean<E> {
    /**
     * 结果集
     */
    private List<E> list;

    /**
     * 查询记录总数
     */
    private int totalRecords;

    /**
     * 每页多少条记录
     */
    private int pageSize;

    /**
     * 第几页
     */
    private int pageNo;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 计算总页数
     *
     * @return 总页数
     * */
    public int getTotalPages(){

        if(totalRecords % pageSize == 0){
            return  totalRecords / pageSize;
        }else {
            return (totalRecords / pageSize)+1;
        }
    }

    /**
     * 计算当前页开始记录
     *
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public int countOffset(int currentPage,int pageSize){
        int offset = pageSize*(currentPage-1);
        return offset;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
