package com.pmcc.warehouse;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.pmcc.warehouse.activity.CommodityActivity;
import com.pmcc.warehouse.activity.DataReportActivity;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.bean.DetailBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.utils.ActivityCollector;
import com.pmcc.warehouse.utils.LogUtils;
import com.pmcc.warehouse.utils.StatusBarUtil;
import com.pmcc.warehouse.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.lin_scan)
    LinearLayout linScan;
    @BindView(R.id.lin_form)
    LinearLayout linForm;

    Intent intent;
    DetailBean detailBean;
    DetailBean.RowsBean rowsBean;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 3) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scan();
            } else {
               Toast.makeText(this,"未获得授权！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.lin_scan, R.id.lin_form})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_scan:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 3);
                }else {
                    scan();
                }
                break;
            case R.id.lin_form:
                intent=new Intent(this, DataReportActivity.class);
                startActivity(intent);
                break;
        }
    }
    //调用相机,扫描二维码
    public void scan(){
        ZxingConfig config=new ZxingConfig();
        config.setShowbottomLayout(true);
        config.setPlayBeep(true);
        config.setShake(true);
        config.setShowAlbum(false);
        config.setShowFlashLight(true);
        intent=new Intent(this, CaptureActivity.class);
        intent.putExtra(Constant.INTENT_ZXING_CONFIG,config);
        startActivityForResult(intent,666);
    }

    //获取商品详情
    public void getData(String code){
        showProgress("查询中");
        Request<String> request= NoHttp.createStringRequest(NetURL.DETAIL, RequestMethod.GET);
        request.add("materialNum",code);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                Log.d("获取结果",result);
                cancelProgress();
                detailBean= JSONObject.parseObject(result, DetailBean.class);
                if (detailBean.getResultCode().equals("1")){
                    if (detailBean.getRows()!=null&&detailBean.getRows().size()>0){
                        rowsBean=detailBean.getRows().get(0);
                        intent=new Intent(getApplication(), CommodityActivity.class);
                        intent.putExtra("code",code);
                        intent.putExtra("rowsBean",rowsBean);
                        startActivity(intent);
                    }
                }else {
                    ToastUtils.getIntance().showToast(detailBean.getResultDesc());
                }
            }

            @Override
            public void onFailed(int what, Response response) {
                cancelProgress();
            }

            @Override
            public void onOver(int what) {
                cancelProgress();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==666&&resultCode==RESULT_OK){
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                getData(content);
            }else {
                ToastUtils.getIntance().showToast("扫描错误");
            }
        }
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    private long exitTime = 0;
    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            ToastUtils.getIntance().showToast("再按一次退出程序");
        } else {
            ActivityCollector.removeAllActivity();
            finish();
        }
    }
}
