package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class RegionBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 7
     * page : 1
     * rows : [{"id":"ff80808171c4c9af0171c4ec801c0020","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 07:52:46","materialName":"变频器配件","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4ecf3b80022","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 07:53:15","materialName":"井下排水系统","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4ed108d0023","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 07:53:23","materialName":"通讯系统","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4f290a80024","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 07:59:23","materialName":"皮带机保护","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4f36eb90026","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 08:00:20","materialName":"猴车配件","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4fb45be0029","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 08:08:54","materialName":"地面供电系统","parentId":"1","orgCode":"0$11kjdk"},{"id":"ff80808171c4c9af0171c4fcc9ec002d","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-29 08:10:33","materialName":"斜巷提升","parentId":"1","orgCode":"0$11kjdk"}]
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
         * id : ff80808171c4c9af0171c4ec801c0020
         * creatorId : 2c9231a671bfc2b00171bfca575b0005
         * creator : 11kadmin
         * createTime : 2020-04-29 07:52:46
         * materialName : 变频器配件
         * parentId : 1
         * orgCode : 0$11kjdk
         */

        private String id;
        private String creatorId;
        private String creator;
        private String createTime;
        private String materialName;
        private String parentId;
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

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }
    }
}
