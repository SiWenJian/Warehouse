package com.pmcc.warehouse.config;

public class NetURL {

//    public static final String BASE = "http://192.168.27.162:8080";//测式服务器
    public static final String BASE = "http://210.76.9.25";//正式服务器

    public static final String LOGIN=BASE+"/auth";//登陆
    public static final String DETAIL=BASE+"/api/v1/appMaterial/getPlanAndMaterial";
    public static final String RUKU=BASE+"/api/v1/appMaterial/save";
    public static final String UPDADE=BASE+"/api/v1/app/get";

    //数据报表

    //获取单位
    public static final String JIDAINRANKS=BASE+"/api/v1/organizations/queryByParentOrg";
    //获取资金支出列表
    public static final String JIDIANEXPEND=BASE+"/api/v1/reportForm/queryQuarter?";
    //配件入库主类
    public static final String CATEGORY=BASE+"/api/v1/mainType/query?parentId=1";
    //入库列表
    public static final String INCOMELIST=BASE+"/api/v1/reportForm/queryStock?";
    //仓库查询接口
    public static final String WAREHOUSE=BASE+"/api/v1/meterialWarehouse/queryByOrgCode";
    //获取物资到货列表
    public static final String ARRIVAL=BASE+"/api/v1/formExport/materialArrival?";
    //获取使用区域
    public static final String REGION=BASE+"/api/v1/useType/query?parentId=1";
    //使用情况列表
    public static final String USELIST=BASE+"/api/v1/reportForm/queryPlace?";

    //获取领用列表
    public static final String COLLECT=BASE+"/api/v1/reportForm/queryO";
}
