package com.pmcc.warehouse.bean;

import com.pmcc.warehouse.base.BaseBean;

import java.util.List;

/**
 * Create on
 */
public class DetailBean extends BaseBean {

    /**
     * resultCode : 1
     * resultDesc : 获取成功
     * resultData :
     * total : 0
     * page : 0
     * rows : [{"name":"鼠标","code":"89524252241","materialParentTypeName":"","materialChildTypeName":"","specification":"20*30","type":"ge","unit":"个","price":30,"cargoName":"","shelvesName":"","materialSupplier":"","planList":[{"intoWarehouseId":"","planId":"40289b2271962e310171963ce94b000e","date":"2020-04","planName":"2020-04年度计划","planNum":"100","residualNum":"100","warehousingNum":"0"}]}]
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

    public static class RowsBean extends BaseBean{
        /**
         * name : 鼠标
         * code : 89524252241
         * materialParentTypeName :
         * materialChildTypeName :
         * specification : 20*30
         * type : ge
         * unit : 个
         * price : 30.0
         * cargoName :
         * shelvesName :
         * materialSupplier :
         * planList : [{"intoWarehouseId":"","planId":"40289b2271962e310171963ce94b000e","date":"2020-04","planName":"2020-04年度计划","planNum":"100","residualNum":"100","warehousingNum":"0"}]
         */

        private String name;
        private String code;
        private String materialParentTypeName;
        private String materialChildTypeName;
        private String specification;
        private String type;
        private String unit;
        private String price;
        private String cargoName;
        private String shelvesName;
        private String materialSupplier;
        private List<PlanListBean> planList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMaterialParentTypeName() {
            return materialParentTypeName;
        }

        public void setMaterialParentTypeName(String materialParentTypeName) {
            this.materialParentTypeName = materialParentTypeName;
        }

        public String getMaterialChildTypeName() {
            return materialChildTypeName;
        }

        public void setMaterialChildTypeName(String materialChildTypeName) {
            this.materialChildTypeName = materialChildTypeName;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getCargoName() {
            return cargoName;
        }

        public void setCargoName(String cargoName) {
            this.cargoName = cargoName;
        }

        public String getShelvesName() {
            return shelvesName;
        }

        public void setShelvesName(String shelvesName) {
            this.shelvesName = shelvesName;
        }

        public String getMaterialSupplier() {
            return materialSupplier;
        }

        public void setMaterialSupplier(String materialSupplier) {
            this.materialSupplier = materialSupplier;
        }

        public List<PlanListBean> getPlanList() {
            return planList;
        }

        public void setPlanList(List<PlanListBean> planList) {
            this.planList = planList;
        }

        public static class PlanListBean extends BaseBean{
            /**
             * intoWarehouseId :
             * planId : 40289b2271962e310171963ce94b000e
             * date : 2020-04
             * planName : 2020-04年度计划
             * planNum : 100
             * residualNum : 100
             * warehousingNum : 0
             */

            private String intoWarehouseId;
            private String planId;
            private String date;
            private String planName;
            private String planNum;
            private String residualNum;
            private String warehousingNum;
            private String reportId;

            public String getIntoWarehouseId() {
                return intoWarehouseId;
            }

            public void setIntoWarehouseId(String intoWarehouseId) {
                this.intoWarehouseId = intoWarehouseId;
            }

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
            }

            public String getPlanNum() {
                return planNum;
            }

            public void setPlanNum(String planNum) {
                this.planNum = planNum;
            }

            public String getResidualNum() {
                return residualNum;
            }

            public void setResidualNum(String residualNum) {
                this.residualNum = residualNum;
            }

            public String getWarehousingNum() {
                return warehousingNum;
            }

            public void setWarehousingNum(String warehousingNum) {
                this.warehousingNum = warehousingNum;
            }

            public String getReportId() {
                return reportId;
            }

            public void setReportId(String reportId) {
                this.reportId = reportId;
            }
        }
    }
}
