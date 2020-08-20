package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class IncomeBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 0
     * page : 0
     * rows : [{"typeName":"架空乘人","name":"232通讯模块","specification":"FX3U-232-BD(湘潭)","num":"4.0","price":"99.00","money":"396.0","unit":"个"},{"typeName":"架空乘人","name":"50G型248托绳轮组件","specification":"轮衬大小Ø248*Ø188*50mm","num":"1.0","price":"100.00","money":"100.0","unit":"件"},{"typeName":"架空乘人","name":"AC/DC电源模块","specification":"JWAK-25S5W，IN：AC85-265V,OUT:DC 5V/5A","num":"2.0","price":"101.00","money":"202.0","unit":"个"},{"typeName":"架空乘人","name":"CAN/RS232转换模块","specification":"CAN/RS232MB","num":"2.0","price":"102.00","money":"204.0","unit":"个"},{"typeName":"架空乘人","name":"G型托轮轮衬","specification":"Ø188*Ø248*50mm（橡胶材质）","num":"8.0","price":"111.00","money":"888.0","unit":"件"}]
     * pageSize : 0
     * object : 1790.0
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
         * typeName : 架空乘人
         * name : 232通讯模块
         * specification : FX3U-232-BD(湘潭)
         * num : 4.0
         * price : 99.00
         * money : 396.0
         * unit : 个
         */

        private String typeName;
        private String name;
        private String specification;
        private String num;
        private String price;
        private String money;
        private String unit;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
