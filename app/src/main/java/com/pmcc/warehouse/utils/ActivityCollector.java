package com.pmcc.warehouse.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:   描述
 * Autour：       WD
 * Date:          2017/10/16 10:24
 * Email：        pmccwd@mrteam.cn
 */

public class ActivityCollector {
    /**
     * 存放activity的列表
     */
    public static HashMap<Class<?>, Activity> activities = new LinkedHashMap<>();

    /**
     * 添加Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity, Class<?> clz) {
        activities.put(clz, activity);
    }

    /**
     * 判断一个Activity 是否存在
     *
     * @param clz
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static <T extends Activity> boolean isActivityExist(Class<T> clz) {
        boolean res;
        Activity activity = getActivity(clz);
        if (activity == null) {
            res = false;
        } else {
            if (activity.isFinishing() || activity.isDestroyed()) {
                res = false;
            } else {
                res = true;
            }
        }

        return res;
    }

    /**
     * 获得指定activity实例
     *
     * @param clazz Activity 的类对象
     * @return
     */
    public static <T extends Activity> T getActivity(Class<T> clazz) {
        return (T) activities.get(clazz);
    }

    /**
     * 移除activity,代替finish
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activities.containsValue(activity)) {
            LogUtils.i("集合包涵" + activity.getClass());
            activities.remove(activity.getClass());
        } else {
            LogUtils.i("集合不包涵" + activity.getClass());
        }
    }

    /**
     * 移除activity,代替finish
     */
    public static void finishActivity(Class<?> clz) {
        if (clz!=null&&activities.containsKey(clz)) {
            LogUtils.i("包涵" + clz.getName());
            activities.get(clz).finish();
            activities.remove(clz);
        } else {
            LogUtils.i("不包涵" + clz.getName());
        }
    }

    /**
     * 移除所有的Activity
     */
    public static void removeAllActivity() {
        if (activities != null && activities.size() > 0) {
            Set<Map.Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Map.Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing()) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
    }

    /**
     * 移除所有的Activity,除了 某一个Activity
     */
    public static void removeAllExceptActivity(Class<?> mActivity) {
        //登出
        if (activities != null && activities.size() > 0) {
            Set<Map.Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Map.Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing() && !s.getValue().equals(mActivity)) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
    }


}
