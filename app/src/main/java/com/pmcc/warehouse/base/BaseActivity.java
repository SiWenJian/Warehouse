package com.pmcc.warehouse.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.utils.ActivityCollector;
import com.pmcc.warehouse.utils.NiceUtil;
import com.pmcc.warehouse.utils.StringUtils;
import com.pmcc.warehouse.utils.StatusBarUtil;
import com.pmcc.warehouse.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BaseActivity extends AppCompatActivity {

    private Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.StatusBarLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        }
        ActivityCollector.addActivity(this,getClass());
    }

    /**
     * 点击空白部位隐藏键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()){
            ToastUtils.getIntance().closeToast();
        }
    }

    public void showProgress(String strMsg) {
        showProgress(strMsg,true);
    }

    public void showProgress(String strMsg, boolean canCelable) {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new Dialog(this, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(canCelable);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        if (StringUtils.isEmpty(strMsg)){
            msg.setVisibility(View.GONE);
        } else{
            msg.setVisibility(View.VISIBLE);
            msg.setText(strMsg);
        }
        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
    /**
     * 设置当前屏幕方向为横屏
     */
    public void setHorizontalScreen(Activity activity) {
        NiceUtil.hideActionBar(this);
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * 设置当前屏幕方向为竖屏
     */
    public void setVerticalScreen(Activity activity) {
        NiceUtil.showActionBar(this);
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
