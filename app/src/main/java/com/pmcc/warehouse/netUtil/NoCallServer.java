/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pmcc.warehouse.netUtil;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.pmcc.warehouse.MyApplication;
import com.pmcc.warehouse.activity.LoginActivity;
import com.pmcc.warehouse.utils.ActivityCollector;
import com.pmcc.warehouse.utils.LogUtils;
import com.pmcc.warehouse.utils.StringUtils;
import com.pmcc.warehouse.utils.SPCache;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.Date;

/**
 * <p>针对队列的一个全局单例封装。</p>
 * Created by YanZhenjie on 2017/6/18.
 */
public class NoCallServer {
    private static int LOGING_EXPIRY_TIME=7*24*60*60*1000;//登录过期时间
    private static NoCallServer instance;

    public static NoCallServer getInstance() {
        if (instance == null)
            synchronized (NoCallServer.class) {
                if (instance == null)
                    instance = new NoCallServer();
            }
        return instance;
    }

     private RequestQueue mRequestQueue;
     private DownloadQueue mDownloadQueue;


    private NoCallServer() {
        mRequestQueue = NoHttp.newRequestQueue(5);
        mDownloadQueue = NoHttp.newDownloadQueue(3);
    }

    /*nohttp原始用法1*/
   /* public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        mRequestQueue.add(what, request, listener);
    }*/
    /*nohttp原始用法2*/
    public <T> void request(Activity activity, int what, Request<T> request, NoHttpListener<T> callback,
                            boolean canCancel, boolean isLoading) {
        request.setCancelSign(activity.getLocalClassName());
        LogUtils.i("网络请求标记" + activity.getLocalClassName());
        mRequestQueue.add(what, request, new NoHttpResponseListener<>(activity, request, callback, true, false));
    }

    /*nohttp原始下载用法1*/
    public void download(int what, DownloadRequest request, DownloadListener listener) {
        mDownloadQueue.add(what, request, listener);
    }


    /*
     * 带请求头的  带缓存 不显示nohttp自带菊花框
     * */
    public <T> void requestWithHeader(Activity activity, int what, Request<String> request, NoHttpListener callback) {
//        long interValTime = new Date().getTime() - SPCache.getInstance().getLongTime("LoginTime");
        if (SPCache.getInstance().getLoginInfo() == null
                || StringUtils.isEmpty(SPCache.getInstance().getLoginInfo().getResultData())) {
            Overduelanding();
        } else {
            request.addHeader("Authorization", SPCache.getInstance().getLoginInfo().getResultData());
            request.setCancelSign(activity.getLocalClassName());
            LogUtils.i("网络请求标记" + activity.getLocalClassName());
            mRequestQueue.add(what, request, new NoHttpResponseListener(activity, request, callback, true, false));
        }
    }

    /*
     * 带请求头的  带缓存 不显示nohttp自带菊花框  为解析数组返回数据而生
     * */
    public <T> void requestWithHeaderArray(Activity activity, int what, Request<String> request, NoHttpListener callback) {
        long interValTime = new Date().getTime() - SPCache.getInstance().getLongTime("LoginTime");
        if (SPCache.getInstance().getLoginInfo() == null
                || StringUtils.isEmpty(SPCache.getInstance().getLoginInfo().getResultData())
                ||interValTime>=LOGING_EXPIRY_TIME) {
            Overduelanding();
        } else {
            request.addHeader("Authorization", SPCache.getInstance().getLoginInfo().getResultData());
            request.setCancelSign(activity.getLocalClassName());
            LogUtils.i("网络请求标记" + activity.getLocalClassName());
            mRequestQueue.add(what, request, new NoHttpResponseListener(activity, request, callback, true, false));
        }
    }

    /*
     * 不带请求头的  带缓存 不显示nohttp自带菊花框
     * */
    public <T> void requestNoHeader(Activity activity, int what, Request<String> request, NoHttpListener callback) {
        request.setCancelSign(activity.getLocalClassName());
//        request.addHeader("Connection", "close");
        LogUtils.i("网络请求标记" + activity.getLocalClassName());
        mRequestQueue.add(what, request, new NoHttpResponseListener(activity, request, callback, true, false));
    }


    public <T> void cancleRequestQueue(String activityName) {
        if (!StringUtils.isEmpty(activityName)&&mRequestQueue!=null) {
            mRequestQueue.cancelBySign(activityName);
        }
        //mRequestQueue.stop();
    }

    /**
     * 过期登录后
     */
    private void Overduelanding() {
        ActivityCollector.removeAllActivity();
        SPCache.getInstance().setLoginInfo("");//清除登录信息
        Toast.makeText(MyApplication.getInstance(), "登录超时，请重新登录", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MyApplication.getInstance(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getInstance().startActivity(intent);
    }
}
