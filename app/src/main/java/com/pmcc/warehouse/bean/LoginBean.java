package com.pmcc.warehouse.bean;

public class LoginBean {

    /**
     * resultCode : 1
     * resultDesc : 登录成功
     * resultData : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrZ3kiLCJjcmVhdGVkIjoxNTg3MzY5NjAyMzMxLCJpZCI6IjQwMjg5YjIyNzE5NjJlMzEwMTcxOTY0NDc3N2UwMDExIiwiZXhwIjoxNTg3OTc0NDAyLCJ1c2VySWQiOm51bGx9.Le-daAzD2XcmIGzJ2-vKhDxGNmbgX_gcSl34IJFrEhlchmLDi2SeDf8gqHXX7ibU6r_IWywoWj8QEIkG7OFMXQ
     * total : 0
     * page : 0
     * rows : null
     * pageSize : 0
     * object : null
     * sCount : 0
     * fCount : 0
     */

    private String resultCode;
    private String resultDesc;
    private String resultData;
    private int total;
    private int page;
    private Object rows;
    private int pageSize;
    private Object object;
    private int sCount;
    private int fCount;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getSCount() {
        return sCount;
    }

    public void setSCount(int sCount) {
        this.sCount = sCount;
    }

    public int getFCount() {
        return fCount;
    }

    public void setFCount(int fCount) {
        this.fCount = fCount;
    }
}
