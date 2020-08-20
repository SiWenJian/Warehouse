/*
 * Copyright 2015 Yan Zhenjie
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
import com.pmcc.warehouse.R;
import com.pmcc.warehouse.activity.LoginActivity;
import com.pmcc.warehouse.utils.ActivityCollector;
import com.pmcc.warehouse.utils.JsonUtil;
import com.pmcc.warehouse.utils.LogUtils;
import com.pmcc.warehouse.utils.SPCache;
import com.pmcc.warehouse.utils.ToastUtils;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Locale;
import static com.pmcc.warehouse.utils.UIUtils.getString;



/**
 * Created in Nov 4, 2015 12:02:55 PM.
 *
 * @author Yan Zhenjie.
 */
public class NoHttpResponseListener<T> implements OnResponseListener<T> {

    private Activity mActivity;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private NoHttpListener<T> callback;

    /**
     * @param activity     context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public NoHttpResponseListener(Activity activity, Request<?> request, NoHttpListener<T> httpCallback, boolean
            canCancel, boolean isLoading) {
        this.mActivity = activity;
        this.mRequest = request;
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {

    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        callback.onOver(what);

    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        if (callback != null) {
            // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
            // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp

            if (401 == response.getHeaders().getResponseCode() && SPCache.getInstance().getLoginInfo() != null) {
                Overduelanding();
            } else if (200 == response.getHeaders().getResponseCode()) {
                if (JsonUtil.isStringJson((String) response.get())) {
                    //  Toast.show(mActivity,"返回数据结构异常");
                    callback.onSucceed(what, (String) response.get());
                } else if (JsonUtil.isListJson((String) response.get())) {
                    //  Toast.show(mActivity,"返回数据结构异常");
                    callback.onSucceed(what, (String) response.get());
                } else {
                    ToastUtils.getIntance().showToast("数据异常");
                    LogUtils.i("异常结构数据" + response.get());
                }
            } else {
                callback.onFailed(what, response);
                LogUtils.i(response.getHeaders().getResponseCode()+"" + response.request().url() + "异常状态数据" + response.get());
            }
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        String message = getString(R.string.error_unknow);
        String messageContent = "";
        if (401 == response.getHeaders().getResponseCode()) {
            Overduelanding();
            return;
        } else if (exception instanceof NetworkError) {// 网络不好
            messageContent = getString(R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            messageContent = getString(R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            messageContent = getString(R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            messageContent = getString(R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            // 没有缓存一般不提示用户，如果需要随你。
        } else if (exception.getMessage()==null||exception.getMessage().contains("Failed to connect to")) {
            messageContent = "服务器异常";
        } else {
            messageContent = "未知";
        }
        message = String.format(Locale.getDefault(), message, messageContent);
        ToastUtils.getIntance().showToast(message);
        LogUtils.i(response.getHeaders().getResponseCode()+message + "网络问题" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what, response);
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
