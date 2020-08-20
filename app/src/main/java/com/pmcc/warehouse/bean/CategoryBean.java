package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class CategoryBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 14
     * page : 1
     * rows : [{"id":"4028811072744bb301727458ac4b0328","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:24:28","materialName":"架空乘人","parentId":"1","code":"ckjr","orgCode":"0$11kjdk"},{"id":"4028811072744bb301727458d9560329","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:24:39","materialName":"皮带管理","parentId":"1","code":"pdgl","orgCode":"0$11kjdk"},{"id":"4028811072744bb30172745903d1032a","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:24:50","materialName":"轨道运输","parentId":"1","code":"gdys","orgCode":"0$11kjdk"},{"id":"4028811072744bb3017274592bfa032b","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:01","materialName":"主扇制冷","parentId":"1","code":"zszl","orgCode":"0$11kjdk"},{"id":"4028811072744bb301727459526e032c","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:10","materialName":"立井提升","parentId":"1","code":"ljts","orgCode":"0$11kjdk"},{"id":"4028811072744bb3017274597538032d","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:19","materialName":"矿水处理","parentId":"1","code":"kscl","orgCode":"0$11kjdk"},{"id":"4028811072744bb3017274599d61032e","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:30","materialName":"供电通讯","parentId":"1","code":"gdtx","orgCode":"0$11kjdk"},{"id":"4028811072744bb301727459bee3032f","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:38","materialName":"线材管材","parentId":"1","code":"xcgc","orgCode":"0$11kjdk"},{"id":"4028811072744bb301727459e6610330","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:48","materialName":"油脂化工","parentId":"1","code":"yzhg","orgCode":"0$11kjdk"},{"id":"4028811072744bb30172745a0b6d0331","creatorId":"ff80808171bfebe40171c3966e88000a","creator":"shiliufeng","createTime":"2020-06-02 09:25:58","materialName":"低值易耗","parentId":"1","code":"dzyh","orgCode":"0$11kjdk"}]
     * pageSize : 10
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
         * id : 4028811072744bb301727458ac4b0328
         * creatorId : ff80808171bfebe40171c3966e88000a
         * creator : shiliufeng
         * createTime : 2020-06-02 09:24:28
         * materialName : 架空乘人
         * parentId : 1
         * code : ckjr
         * orgCode : 0$11kjdk
         */

        private String id;
        private String creatorId;
        private String creator;
        private String createTime;
        private String materialName;
        private String parentId;
        private String code;
        private String orgCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }
    }
}
