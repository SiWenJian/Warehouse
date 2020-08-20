package com.pmcc.warehouse.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSONObject;
import com.pmcc.warehouse.DownloadAppService;
import com.pmcc.warehouse.MainActivity;
import com.pmcc.warehouse.R;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.bean.LoginBean;
import com.pmcc.warehouse.bean.UpdateBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.utils.StringUtils;
import com.pmcc.warehouse.utils.SPCache;
import com.pmcc.warehouse.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create on
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    Intent intent;
    private LoginBean loginInfo;
    //文件名
    private String mApkName="wzgk.apk";
    //文件下载地址
    private String mApkUrl="";

    MyReceiver receiver = new MyReceiver();
    private boolean isdownload=false;
    String packageName;//当前包名
    String currentVer;//当前版本
    UpdateBean updateBean;
    UpdateBean.ObjectBean objectBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (!StringUtils.isEmpty(SPCache.getInstance().getString("username"))){
            phone.setText(SPCache.getInstance().getString("username"));
        }
        if (!StringUtils.isEmpty(SPCache.getInstance().getString("password"))){
            password.setText(SPCache.getInstance().getString("password"));
        }
        getVerson();
        regist();
        currentVer=StringUtils.getVersionName(this);
        checkInstallation();
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        if (isdownload){
            ToastUtils.getIntance().showToast("应用正在更新,请稍后再试");
            return;
        }
        if (StringUtils.isEmpty(phone.getText().toString())) {
            ToastUtils.getIntance().showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(password.getText().toString())) {
            ToastUtils.getIntance().showToast("请输入登陆密码");
            return;
        }
        SPCache.getInstance().saveString("username",phone.getText().toString());
        SPCache.getInstance().saveString("password",password.getText().toString());
        login(phone.getText().toString(),password.getText().toString());
    }

    /**
     * 获取当前版本
     */
    private void getVerson() {
        PackageManager packageManager = getPackageManager();
        packageName = getPackageName();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        currentVer = packInfo.versionName;
    }

    /**
     * 检查更新
     */
    public void update(){
        Request<String> request=NoHttp.createStringRequest(NetURL.UPDADE,RequestMethod.GET);
        NoCallServer.getInstance().requestNoHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                Log.d("获取更新信息",result);
                updateBean=JSONObject.parseObject(result,UpdateBean.class);
                objectBean=updateBean.getObject();
                try {
                    if (StringUtils.compareVersion(objectBean.getVersion(),currentVer)>0){
                        mApkUrl=objectBean.getDownloadUrl();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showUpdate();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response response) {

            }

            @Override
            public void onOver(int what) {

            }
        });
    }

    public void showUpdate(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("更新提示");
        builder.setMessage("软件有新的版本发布了,赶快下载体验吧");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startDownloadApk();
            }
        });
        builder.setNegativeButton("暂不更新",null);
        builder.setCancelable(false);
        builder.create().show();
    }


    /**
     * 登陆
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        showProgress("登录中");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        hashMap.put("type","app");

        Request<String> request = NoHttp.createStringRequest(NetURL.LOGIN, RequestMethod.POST);
        request.setDefineRequestBodyForJson(JSONObject.toJSONString(hashMap));

        NoCallServer.getInstance().requestNoHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                cancelProgress();
                SPCache.getInstance().setLoginInfo(result);
                loginInfo=SPCache.getInstance().getLoginInfo();
                if (loginInfo.getResultCode().equals("1")){
                    intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    ToastUtils.getIntance().showToast(loginInfo.getResultData());
                }
            }

            @Override
            public void onFailed(int what, Response response) {
                cancelProgress();
                ToastUtils.getIntance().showToast("登录账号/密码错误");
            }

            @Override
            public void onOver(int what) {
                cancelProgress();
            }
        });
    }

    //检查是否有安装应用的权限
    public void checkInstallation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if (!getPackageManager().canRequestPackageInstalls()){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("权限申请");
                builder.setMessage("该应用无应用内安装应用的权限,可能导致应用更新时无法正常安装,请前去设置");
                builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                        startActivityForResult(intent, 10086);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.setCancelable(false);
                builder.create().show();
            }else {
                update();
            }
        }
    }

    public void startDownloadApk(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        }else {
            downloadApk(mApkUrl);
        }
    }

    private void downloadApk(String url){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "wzgk.apk");
        if (file.exists()) {
            file.delete();
        }
        isdownload=true;
        Intent serviceIntent = new Intent(getApplicationContext(), DownloadAppService.class);
        //写入你的apk下载地址，下面这个地址只是模拟的
        serviceIntent.setData(Uri.parse(url));
        //开启服务，不要写成了startActivity(serviceIntent);
        startService(serviceIntent);
    }

    //注册广播
    private void regist() {
        IntentFilter intentFilter = new IntentFilter(DownloadAppService.BROADCAST_ACTION);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            isdownload=false;
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "wzgk.apk");
            //获取权限
            try {
                Runtime.getRuntime().exec("chmod 777" + file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent = new Intent(Intent.ACTION_VIEW);
            //如果设置，这个活动将成为这个历史堆栈上的新任务的开始
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //判读版本是否在7.0以上
            if (Build.VERSION.SDK_INT >= 24) {
                //7.0以上的版本
                Uri apkUri = FileProvider.getUriForFile(context, "com.pmcc.warehouse.fileProvider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                //7.0以下的版本
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 3) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                update();
            } else {
                Toast.makeText(this,"未获得授权！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10086&&resultCode==RESULT_OK){
            update();
        }else {
            ToastUtils.getIntance().showToast("未获得安装应用的权限,可能会导致更新时出现错误");
        }
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
