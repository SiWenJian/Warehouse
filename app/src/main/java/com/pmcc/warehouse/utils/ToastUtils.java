package com.pmcc.warehouse.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.pmcc.warehouse.MyApplication;
import com.pmcc.warehouse.R;


/**
 * 动态toast
 */
public class ToastUtils {
    private Toast toast;
    public static final int INFO = 1;//一般提示
    public static final int ERROR = 2;  //错误
    public static final int WARNING = 3; //警告

    /**
     * 饿汉式 优劣详见 com.pmcc.apptv.Common.Base.MethodAnalysis.SingletonMode
     */
    private static final ToastUtils toastUtils=new ToastUtils();
    private ToastUtils() {}
    public static ToastUtils getIntance(){
        return toastUtils;
    }

    /**
     * 一般toast Toast多次点击只显示一次
     *
     * @param text
     */
    public void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.BOTTOM, 0, UIUtils.getDimention(R.dimen.mar_64));
        toast.show();
    }

    public void closeToast(){
        if (toast!=null){
            toast.cancel();
        }
        toast=null;
    }

}
