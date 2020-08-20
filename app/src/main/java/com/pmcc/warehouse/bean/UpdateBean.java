package com.pmcc.warehouse.bean;

/**
 * Create on
 */
public class UpdateBean {

    /**
     * resultCode : 1
     * resultDesc : null
     * resultData : null
     * total : 0
     * page : 0
     * rows : null
     * pageSize : 0
     * object : {"id":"40289bfd71b4375d0171b43b487f0000","name":"app-release.apk","version":"1.0.2","appType":"0","downloadUrl":"http://wzgk.pmcc.cn:8080/api/v1/app/download","apkUrl":"/updateFile/app-release.apk","state":"RELEASE","remark":"dc9e157d1a8043edadc1924294a3c58e.png","update":true}
     * sCount : 0
     * fCount : 0
     */

    private String resultCode;
    private Object resultDesc;
    private Object resultData;
    private int total;
    private int page;
    private Object rows;
    private int pageSize;
    private ObjectBean object;
    private int sCount;
    private int fCount;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(Object resultDesc) {
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

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
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

    public static class ObjectBean {
        /**
         * id : 40289bfd71b4375d0171b43b487f0000
         * name : app-release.apk
         * version : 1.0.2
         * appType : 0
         * downloadUrl : http://wzgk.pmcc.cn:8080/api/v1/app/download
         * apkUrl : /updateFile/app-release.apk
         * state : RELEASE
         * remark : dc9e157d1a8043edadc1924294a3c58e.png
         * update : true
         */

        private String id;
        private String name;
        private String version;
        private String appType;
        private String downloadUrl;
        private String apkUrl;
        private String state;
        private String remark;
        private boolean update;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }
    }
}
