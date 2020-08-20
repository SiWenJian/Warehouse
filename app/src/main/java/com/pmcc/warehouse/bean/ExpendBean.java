package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class ExpendBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 0
     * page : 0
     * rows : [{"orgName":"皮一队","upper":"777.0","in":"0","lower":"0","sum":"777.0"}]
     * pageSize : 0
     * object : 777.0
     * sCount : 0
     * fCount : 0
     */

    private String resultCode;
    private String resultDesc;
    private Object resultData;
    private int total;
    private int page;
    private int pageSize;
    private String object;
    private int sCount;
    private int fCount;
    private List<RowsBean> rows;

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

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * orgName : 皮一队
         * upper : 777.0
         * in : 0
         * lower : 0
         * sum : 777.0
         */

        private String orgName;
        private String upper;
        private String in;
        private String lower;
        private String sum;

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getUpper() {
            return upper;
        }

        public void setUpper(String upper) {
            this.upper = upper;
        }

        public String getIn() {
            return in;
        }

        public void setIn(String in) {
            this.in = in;
        }

        public String getLower() {
            return lower;
        }

        public void setLower(String lower) {
            this.lower = lower;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }
    }
}
