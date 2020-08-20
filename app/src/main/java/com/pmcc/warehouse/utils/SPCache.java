package com.pmcc.warehouse.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.pmcc.warehouse.MyApplication;
import com.pmcc.warehouse.bean.LoginBean;

/**
 * Description:      SharedPreference存储
 * Autour：          WD
 * Date：            2017/8/17 16:28
 */
public class SPCache {
    private static LoginBean loginInfo;
    private static final String FILE_NAME = "warehouse_cache";
    private volatile static SPCache mInstance;
    private static SharedPreferences settings;

    private SPCache() {
        settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
    }
    /**
     * 单例
     * @return SharedPreferencesUser对象
     */
    public static SPCache getInstance() {
        if (mInstance == null) {
            synchronized (SPCache.class) {
                if (mInstance == null) {
                    mInstance = new SPCache();
                }
            }
        }
        return mInstance;
    }

    public void putPres(String key, String value) {
        // 阿里云语音  有 无
        if (key.equals("ALIVOICE")) {
            settings.edit().putString("ALIVOICE", value).commit();//0与空是无 1是有
        } else {
            settings.edit().putString(key, value).commit();
        }
    }


    public String getPres(String key) {
        String value = "";
        if (key.equals("ALIVOICE")) {
            value = settings.getString("ALIVOICE", "0");
        } else {
            value = settings.getString(key, "");
        }
        return value;
    }

    /**
     * 保存字符串数据
     * @param key
     * @param value
     */
    public void saveString(String key, String value) {
        settings.edit().putString(key, value).commit();
    }

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public String getString(String key) {
        return settings.getString(key, "");
    }
    /**
     * 保存boolean类型数据
     * @param key
     * @param value
     */
    public void saveBoolean(String key, boolean value) {
        settings.edit().putBoolean(key, value).commit();
    }
    /**
     * 获取字符串
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return settings.getBoolean(key, false);
    }

    /**
     * 保存int类型数据
     * @param key
     * @param value
     */
    public void saveInt(String key, int value) {
        settings.edit().putInt(key, value).commit();
    }
    /**
     * 获取字符串
     * @param key
     * @return
     */
    public int getIntValue(String key) {
        return settings.getInt(key, 0);
    }
    public int getIntValueNOT0(String key, int code) {
        return settings.getInt(key, code);
    }


    /**
     * 保存long类型数据
     * @param key
     * @param value
     */
    public void saveLongTime(String key, long value) {
        settings.edit().putLong(key, value).commit();
    }
    /**
     * 获取字符串
     * @param key
     * @return
     */
    public long getLongTime(String key) {
        return settings.getLong(key, System.currentTimeMillis());
    }


    /**
     * 保存登录信息
     *
     * @param info
     * @return
     */
    public LoginBean setLoginInfo(String info) {
        if (settings == null) {
            settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        settings.edit().putString("login", info).commit();
        if (StringUtils.isEmpty(info)) {
            loginInfo = null;
        } else {
            loginInfo = JSONObject.parseObject(info, LoginBean.class);
        }
        return loginInfo;
    }

    /**
     * 获取登录信息
     * @return
     */
    public LoginBean getLoginInfo() {
        if (settings == null || loginInfo == null) {
            settings = MyApplication.getInstance().getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        }
        if (StringUtils.isEmpty(settings.getString("login", ""))) {
            return null;
        } else {
            loginInfo = JSONObject.parseObject(settings.getString("login", ""), LoginBean.class);
        }
        return loginInfo;
    }

    /**
     * 得到SharedPreferences对象
     * @return

    public static SharedPreferences getSp() {
        if (settings == null) {
            settings = YouApplication.getInstance().getSharedPreferences(FILE_NAME,
                    Activity.MODE_PRIVATE);
        }
        return settings;
    }*/


    /**
     * 是否存在该字段
     *
     * @param result
     * @return
     */
    public boolean existResult(String result) {
        return settings.contains(result);
    }

    /**
     * 移除该字段
     *
     * @param preName
     */
    public void removePre(String preName) {
        settings.edit().remove(preName).commit();
    }

    /**
     * 清空用户信息
     */
    public void clearUserInfo() {
        settings.edit().clear();
        settings.edit().commit();
    }

}
