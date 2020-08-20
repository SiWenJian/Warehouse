package com.pmcc.warehouse.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.core.content.ContextCompat;

import com.pmcc.warehouse.MyApplication;


public class UIUtils {
    public static String getWindowSize(Activity context) {
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = screenWidth = display.getWidth();
        int screenHeight = screenHeight = display.getHeight();
        return "宽" + screenWidth + "高" + screenHeight;
    }

    public static String getWindowSizeMsg(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        /**
         * getRealMetrics - 屏幕的原始尺寸，即包含状态栏。
         * version >= 4.2.2
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            float density = metrics.density;
            int densityDpi = metrics.densityDpi;
            return "dpi值" + densityDpi + "密度值" + density + "宽" + width + "高" + height;
        } else {
            return "";
        }
    }


    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * 延迟执行任务
     *
     * @param task
     * @param delayMillis
     *//*
	public static void postTaskDelay(Runnable task, int delayMillis) {
		getMainThreadHandler().postDelayed(task, delayMillis);
	}
	*//**
     * 得到主线程Handler
     *
     * @return
     *//*
	public static Handler getMainThreadHandler() {
		return YouApplication.getmHandler();
	}*/

    /**
     * dip-->px
     */
    public static int dip2Px(int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = getResource().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            // versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * sp转化成px
     */
    public static int sp2px(float spValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * px转化成sp
     */
    public static int px2sp(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据string-array id 获取 数组
     */
    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static Context getContext() {
        return MyApplication.getInstance();
    }

    /**
     * 根据Layout id 获取 view 对象
     *
     * @param layout
     * @return
     */
    public static View inflate(int layout) {
        return View.inflate(getContext(), layout, null);
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }

    public static int getColor(int id) {
        //return getResource().getColor(id); //已过时
        return ContextCompat.getColor(MyApplication.getInstance(), id);
    }

    public static int getDimention(int id) {
        return (int) getResource().getDimension(id);
    }

    /**
     * 通过id 获取string
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return getResource().getString(id);
    }

    /**
     * 设置状态栏颜色
     *
     * @param context
     * @param type     1.透明,2.全屏,3.填充颜色
     * @param setColor 只有为type=3，才有用
     */
    public static void setStatusBar(Activity context, int type, int setColor) {
        Window window = context.getWindow();
        switch (type) {
            case 1://透明
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
                break;
            case 2://全屏
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case 3://填充颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(getColor(setColor));
                }
                break;

        }
    }

    //该方法可以封装到工具类里
    public static void initViewAnimation(final View showView, boolean isshow, boolean istop) {

        float[] up2down;

        float[] down2up;

        if (istop) {
            up2down = new float[]{-1f, 0f};
            down2up = new float[]{0f, -1f};

        } else {
            up2down = new float[]{0f, 1f};
            down2up = new float[]{1f, 0f};
        }

        if (isshow) {
            TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    down2up[0], Animation.RELATIVE_TO_SELF, down2up[1]);
            mShowAction.setDuration(500);
            showView.clearAnimation();
            showView.startAnimation(mShowAction);
        } else {
            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, up2down[0], Animation.RELATIVE_TO_SELF, up2down[1]);
            mHiddenAction.setDuration(500);
            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    showView.setVisibility(View.GONE);
                    showView.invalidate();
                }
            });

            showView.clearAnimation();
            showView.startAnimation(mHiddenAction);
        }
    }

}
