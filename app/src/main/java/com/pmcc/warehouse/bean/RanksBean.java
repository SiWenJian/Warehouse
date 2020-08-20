package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class RanksBean {

    /**
     * resultCode : 1
     * resultDesc : 组织列表
     * resultData : null
     * total : 0
     * page : 0
     * rows : [{"id":"402881107226d7f2017226fe74530006","code":"0$11kjdkpidaidui","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"皮一队","isLeaf":false,"key":"402881107226d7f2017226fe74530006","value":"mass"},{"id":"402881107226d7f2017226fed2220007","code":"0$11kjdkyunyidui","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"运一队","isLeaf":false,"key":"402881107226d7f2017226fed2220007","value":"mass"},{"id":"40288110723b5c1401724b2d00970022","code":"0$11kjdkyunerdui","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"运二队","isLeaf":false,"key":"40288110723b5c1401724b2d00970022","value":"mass"},{"id":"40288110723b5c1401724b2dfcaa0023","code":"0$11kjdkpisandui","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"皮三队","isLeaf":false,"key":"40288110723b5c1401724b2dfcaa0023","value":"mass"},{"id":"40288110723b5c1401724b2e77d50024","code":"0$11kjdkxigongqu","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"西工区","isLeaf":false,"key":"40288110723b5c1401724b2e77d50024","value":"mass"},{"id":"ff80808171bfebe40171c39525550003","code":"0$11kjdkjdd","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"机电队","isLeaf":false,"key":"ff80808171bfebe40171c39525550003","value":"mass"},{"id":"ff80808171bfebe40171c39570600004","code":"0$11kjdkgdd","parentId":"2c9231a671bfc2b00171bfc9f69c0002","title":"供电队","isLeaf":false,"key":"ff80808171bfebe40171c39570600004","value":"mass"}]
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
         * id : 402881107226d7f2017226fe74530006
         * code : 0$11kjdkpidaidui
         * parentId : 2c9231a671bfc2b00171bfc9f69c0002
         * title : 皮一队
         * isLeaf : false
         * key : 402881107226d7f2017226fe74530006
         * value : mass
         */

        private String id;
        private String code;
        private String parentId;
        private String title;
        private boolean isLeaf;
        private String key;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(boolean isLeaf) {
            this.isLeaf = isLeaf;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
