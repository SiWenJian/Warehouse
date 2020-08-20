package com.pmcc.warehouse.netUtil;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * Description:   描述
 * Autour：       WD
 * Date:          2017/10/20 10:07
 * Email：        pmccwd@mrteam.cn
 */

public interface NoHttpListener<T> {
    void onSucceed(int what, String result);
    void onFailed(int what, Response<T> response);
    void onOver(int what);
}
