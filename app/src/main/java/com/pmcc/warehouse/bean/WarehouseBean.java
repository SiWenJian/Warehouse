package com.pmcc.warehouse.bean;

import java.util.List;

/**
 * Create on
 */
public class WarehouseBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData : null
     * total : 0
     * page : 0
     * rows : [{"id":"ff80808171c8a3000171c8d005c60023","creatorId":"2c9231a671bfc2b00171bfca575b0005","creator":"11kadmin","createTime":"2020-04-30 02:00:08","code":"11kjdpjk","name":"机电配件库","orgId":"0$11kjdk","orgCode":"0$11kjdk","orgName":"十一矿机电科","type":"","remark":"机电配件库","managementName":"时六峰","managementId":"ff80808171bfebe40171c3966e88000a"}]
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
         * id : ff80808171c8a3000171c8d005c60023
         * creatorId : 2c9231a671bfc2b00171bfca575b0005
         * creator : 11kadmin
         * createTime : 2020-04-30 02:00:08
         * code : 11kjdpjk
         * name : 机电配件库
         * orgId : 0$11kjdk
         * orgCode : 0$11kjdk
         * orgName : 十一矿机电科
         * type :
         * remark : 机电配件库
         * managementName : 时六峰
         * managementId : ff80808171bfebe40171c3966e88000a
         */

        private String id;
        private String creatorId;
        private String creator;
        private String createTime;
        private String code;
        private String name;
        private String orgId;
        private String orgCode;
        private String orgName;
        private String type;
        private String remark;
        private String managementName;
        private String managementId;

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

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getManagementName() {
            return managementName;
        }

        public void setManagementName(String managementName) {
            this.managementName = managementName;
        }

        public String getManagementId() {
            return managementId;
        }

        public void setManagementId(String managementId) {
            this.managementId = managementId;
        }
    }
}
