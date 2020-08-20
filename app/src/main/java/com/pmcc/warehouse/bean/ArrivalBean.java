package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class ArrivalBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 7
     * page : 0
     * rows : [{"code":"1131012810001","name":"天轮轴承","specification":"JKMD-4×3.5绞车配套天轮使用(新副井绞车)\n轴承型号： 3003280","unit":"套","price":"2.00","planNum":"2.00","num":"0","proportion":"0.00%"},{"code":"1171024010001","name":"大红调和漆","specification":"小桶（2-3.5kg）","unit":"公斤","price":"16.96","planNum":"1.00","num":"0","proportion":"0.00%"},{"code":"1231000110001","name":"232通讯模块","specification":"FX3U-232-BD(湘潭)","unit":"个","price":"99.00","planNum":"3.00","num":"0","proportion":"0.00%"},{"code":"1231000210001","name":"50G型248托绳轮组件","specification":"轮衬大小Ø248*Ø188*50mm","unit":"件","price":"100.00","planNum":"1.00","num":"0","proportion":"0.00%"},{"code":"1231000310001","name":"AC/DC电源模块","specification":"JWAK-25S5W，IN：AC85-265V,OUT:DC 5V/5A","unit":"个","price":"101.00","planNum":"2.00","num":"0","proportion":"0.00%"},{"code":"1231000410001","name":"CAN/RS232转换模块","specification":"CAN/RS232MB","unit":"个","price":"102.00","planNum":"2.00","num":"0","proportion":"0.00%"},{"code":"1231001310001","name":"G型托轮轮衬","specification":"Ø188*Ø248*50mm（橡胶材质）","unit":"件","price":"111.00","planNum":"7.00","num":"0","proportion":"0.00%"}]
     * pageSize : 0
     * object : null
     * sCount : 0
     * fCount : 0
     */

    private String resultCode;
    private String resultDesc;
    private Object resultData;
    private int total;
    private int page;
    private int pageSize;
    private Object object;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * code : 1131012810001
         * name : 天轮轴承
         * specification : JKMD-4×3.5绞车配套天轮使用(新副井绞车)
         轴承型号： 3003280
         * unit : 套
         * price : 2.00
         * planNum : 2.00
         * num : 0
         * proportion : 0.00%
         */

        private String code;
        private String name;
        private String specification;
        private String unit;
        private String price;
        private String planNum;
        private String num;
        private String proportion;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPlanNum() {
            return planNum;
        }

        public void setPlanNum(String planNum) {
            this.planNum = planNum;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
        }
    }
}
