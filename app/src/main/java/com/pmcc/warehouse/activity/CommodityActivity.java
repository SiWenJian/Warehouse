package com.pmcc.warehouse.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.pmcc.warehouse.R;
import com.pmcc.warehouse.adapter.PlanAdapter;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.base.SuccessBean;
import com.pmcc.warehouse.bean.DetailBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.review.MoveTextView;
import com.pmcc.warehouse.review.MyListView;
import com.pmcc.warehouse.utils.StringUtils;
import com.pmcc.warehouse.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create on
 */
public class CommodityActivity extends BaseActivity implements PlanAdapter.ClickItem {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.mylist)
    MyListView mylist;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.specification)
    TextView specification;
    @BindView(R.id.parentname)
    TextView parentname;
    @BindView(R.id.childname)
    TextView childname;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.cargoname)
    TextView cargoname;
    @BindView(R.id.shelvesname)
    TextView shelvesname;
    @BindView(R.id.materialsupplier)
    TextView materialsupplier;
    @BindView(R.id.scan)
    MoveTextView scan;

    PlanAdapter planAdapter;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String codenum = "";
    DetailBean detailBean;
    DetailBean.RowsBean rowsBean;
    List<DetailBean.RowsBean.PlanListBean> planList = new ArrayList<>();

    EditText num, batch,notes;
    TextView plan_count,complete_count,surplus_count;
    Button sure,cancel;
    int tag = 0;
    SuccessBean successBean;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        ButterKnife.bind(this);
        initView();
        codenum = getIntent().getStringExtra("code");
        rowsBean = (DetailBean.RowsBean) getIntent().getSerializableExtra("rowsBean");
        refreshData();
    }

    public void initView() {
        topTitle.setText("入库商品详情");
        planAdapter = new PlanAdapter(this, planList, this);
        mylist.setAdapter(planAdapter);
    }

    //刷新页面数据
    public void refreshData() {
        if (rowsBean != null) {
            if (!StringUtils.isEmpty(rowsBean.getName())) {
                name.setText(rowsBean.getName());
            } else {
                name.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getCode())) {
                code.setText(rowsBean.getCode());
            } else {
                code.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getMaterialSupplier())) {
                materialsupplier.setText(rowsBean.getMaterialSupplier());
            } else {
                materialsupplier.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getMaterialParentTypeName())) {
                parentname.setText(rowsBean.getMaterialParentTypeName());
            } else {
                parentname.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getMaterialChildTypeName())) {
                childname.setText(rowsBean.getMaterialChildTypeName());
            } else {
                childname.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getSpecification())) {
                specification.setText(rowsBean.getSpecification());
            } else {
                specification.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getType())) {
                type.setText(rowsBean.getType());
            } else {
                type.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getUnit())) {
                unit.setText(rowsBean.getUnit());
            } else {
                unit.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getPrice())) {
                price.setText(rowsBean.getPrice());
            } else {
                price.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getCargoName())) {
                cargoname.setText(rowsBean.getCargoName());
            } else {
                cargoname.setText("");
            }
            if (!StringUtils.isEmpty(rowsBean.getShelvesName())) {
                shelvesname.setText(rowsBean.getShelvesName());
            } else {
                shelvesname.setText("");
            }
            planList.clear();
            planList.addAll(rowsBean.getPlanList());
            planAdapter.notifyDataSetChanged();
        }
    }

    public void initDta(String codenum) {
        Request<String> request = NoHttp.createStringRequest(NetURL.DETAIL, RequestMethod.GET);
        request.add("materialNum", codenum);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                Log.d("获取结果", result);
                detailBean = JSONObject.parseObject(result, DetailBean.class);
                if (detailBean.getResultCode().equals("1")) {
                    if (detailBean.getRows() != null && detailBean.getRows().size() > 0) {
                        rowsBean = detailBean.getRows().get(0);
                        if (rowsBean != null) {
                            refreshData();
                        }
                    }
                } else {
                    ToastUtils.getIntance().showToast(detailBean.getResultDesc());
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


    //弹出popuwindow
    public void showDialog() {
        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.popupwindow, null, false);//引入弹窗布局
        num = convertView.findViewById(R.id.num);
        batch = convertView.findViewById(R.id.batch);
        sure = convertView.findViewById(R.id.sure);
        plan_count=convertView.findViewById(R.id.plan_count);
        complete_count=convertView.findViewById(R.id.complete_count);
        surplus_count=convertView.findViewById(R.id.surplus_count);
        notes=convertView.findViewById(R.id.notes);
        cancel=convertView.findViewById(R.id.cancel);
        plan_count.setText(planList.get(tag).getPlanNum());
        complete_count.setText(planList.get(tag).getWarehousingNum());
        surplus_count.setText(planList.get(tag).getResidualNum());
        if (rowsBean!=null&&!StringUtils.isEmpty(rowsBean.getPrice())){
            batch.setText(rowsBean.getPrice());
        }
        num.setSelection(num.getText().length());
        batch.setSelection(batch.getText().length());
        num.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(".") && dest.toString().length() == 0){
                    return "0.";
                }
                if(dest.toString().contains(".")){
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if(length == 5){
                        return "";
                    }
                }
                return null;
            }
        }});
        batch.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(".") && dest.toString().length() == 0){
                    return "0.";
                    }
                if(dest.toString().contains(".")){
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if(length == 3){
                        return "";
                    }
                }
                return null;
            }
        }});
        builder.setView(convertView);
        dialog = builder.show();
        dialog.setCanceledOnTouchOutside(false);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(num.getText().toString())) {
                    ToastUtils.getIntance().showToast("请输入入库数量");
                    return;
                }
                if (StringUtils.isEmpty(batch.getText().toString())) {
                    ToastUtils.getIntance().showToast("请输入入库批号");
                    return;
                }
                if (Double.parseDouble(num.getText().toString()) - Double.parseDouble(planList.get(tag).getResidualNum()) > 0) {
                    ToastUtils.getIntance().showToast(" ");
                    return;
                }
                warehousing(num.getText().toString(), batch.getText().toString());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        tag = position;
        showDialog();
    }

    //入库
    public void warehousing(String actualNum, String batch) {
        showProgress("正在入库");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("planNum", planList.get(tag).getPlanNum());
        hashMap.put("actualNum", actualNum);
        hashMap.put("price", batch);
        if (!StringUtils.isEmpty(planList.get(tag).getIntoWarehouseId())) {
            hashMap.put("id", planList.get(tag).getIntoWarehouseId());
            hashMap.put("residualNum", planList.get(tag).getResidualNum());
        }
        hashMap.put("date",planList.get(tag).getDate());
        hashMap.put("code", codenum);
        hashMap.put("reportId", planList.get(tag).getReportId());
        hashMap.put("planId", planList.get(tag).getPlanId());
        if (!StringUtils.isEmpty(notes.getText().toString())){
            hashMap.put("notes",notes.getText().toString());
        }else {
            hashMap.put("notes","");
        }
        Request request = NoHttp.createStringRequest(NetURL.RUKU, RequestMethod.POST);
        request.setDefineRequestBodyForJson(JSONObject.toJSONString(hashMap));
        Log.d("打印数据",JSONObject.toJSONString(hashMap));
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                Log.d("打印保存数据", result);
                cancelProgress();
                successBean = JSONObject.parseObject(result, SuccessBean.class);
                if (successBean != null) {
                    if (successBean.getResultCode().equals("1")) {
                        ToastUtils.getIntance().showToast(successBean.getResultDesc());
                        dialog.dismiss();
                        initDta(codenum);
                    } else {
                        ToastUtils.getIntance().showToast(successBean.getResultDesc());
                    }
                }
            }

            @Override
            public void onFailed(int what, Response response) {
                cancelProgress();
                ToastUtils.getIntance().showToast("入库失败");
            }

            @Override
            public void onOver(int what) {
                cancelProgress();
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.scan:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 3);
                }else {
                    if (!scan.isDrag()){
                        scan();
                    }
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==666&&resultCode==RESULT_OK){
            if (data != null) {
                codenum = data.getStringExtra(Constant.CODED_CONTENT);
                initDta(codenum);
            }else {
                ToastUtils.getIntance().showToast("扫描错误");
            }
        }
    }
}
