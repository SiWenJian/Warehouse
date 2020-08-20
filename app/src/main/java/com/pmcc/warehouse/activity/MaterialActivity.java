package com.pmcc.warehouse.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.pmcc.warehouse.R;
import com.pmcc.warehouse.adapter.ArrivalAdapter;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.bean.ArrivalBean;
import com.pmcc.warehouse.bean.WarehouseBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.utils.NiceUtil;
import com.pmcc.warehouse.utils.StringUtils;
import com.pmcc.warehouse.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create on
 * 物资到货统计表
 */
public class MaterialActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.warehouse)
    TextView warehouse;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.date)
    LinearLayout date;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.empty)
    TextView empty;

    SimpleDateFormat formatter_year, formatter_mouth;
    String year_str = "";
    String mouth_str = "";
    Calendar startDate, endDate, selectedDate;
    WarehouseBean warehouseBean;
    List<WarehouseBean.RowsBean> warehouseList=new ArrayList<>();
    List<String> warename=new ArrayList<>();
    private String warehouseid="";

    ArrivalBean arrivalBean;
    List<ArrivalBean.RowsBean> arrivalList=new ArrayList<>();
    ArrivalAdapter arrivalAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        setHorizontalScreen(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initView(){
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        formatter_year = new SimpleDateFormat("yyyy");
        year_str = formatter_year.format(curDate);
        formatter_mouth = new SimpleDateFormat("MM");
        mouth_str = formatter_mouth.format(curDate);
        selectedDate = Calendar.getInstance();//系统当前时间
        startDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        endDate = Calendar.getInstance();
        endDate.set(2025, 11, 31);

        time.setText(year_str + "-" + mouth_str);
        arrivalAdapter=new ArrivalAdapter(this,arrivalList);
        list.setAdapter(arrivalAdapter);
    }

    //查询仓库列表
    public void initData(){
        Request<String> request = NoHttp.createStringRequest(NetURL.WAREHOUSE, RequestMethod.GET);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                warehouseBean= JSONObject.parseObject(result,WarehouseBean.class);
                if (warehouseBean!=null){
                    warehouseList.clear();
                    warehouseList.addAll(warehouseBean.getRows());
                    if (warehouseList!=null&&warehouseList.size()>0){
                        warehouseid=warehouseList.get(0).getCode();
                        warehouse.setText(warehouseList.get(0).getName());
                    }
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


    @OnClick({R.id.rl_back, R.id.warehouse, R.id.date, R.id.reset, R.id.query, R.id.empty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.warehouse:
                if (warehouseList==null||warehouseList.size()==0){
                    ToastUtils.getIntance().showToast("未获取到仓库数据,请稍后再试");
                    initData();
                    return;
                }
                warename.clear();
                for (WarehouseBean.RowsBean rowsBean:warehouseList){
                    warename.add(rowsBean.getName());
                }
                OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        warehouse.setText(warename.get(options1));
                        warehouseid = warehouseList.get(options1).getCode();
                    }
                })
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .setTitleText("仓库")
                        .build();
                optionsPickerView.setPicker(warename);
                optionsPickerView.show();
                break;
            case R.id.date:
                TimePickerView timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        time.setText(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .setLabel("年", "月", "", "", "", "")
                        .setDate(selectedDate)
                        .setRangDate(startDate, endDate)
                        .isCenterLabel(false)
                        .setLineSpacingMultiplier(1.5f)
                        .setContentSize(20)
                        .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                        .build();
                timePickerView.show();
                break;
            case R.id.reset:
                warehouse.setText("请选择");
                warehouseid="";
                time.setText(year_str + "-" + mouth_str);
                list.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                break;
            case R.id.query:
                if (StringUtils.isEmpty(warehouseid)){
                    ToastUtils.getIntance().showToast("请先选择仓库");
                    return;
                }
                getArrivalList();
                break;
        }
    }

    //获取物资到货列表
    public void getArrivalList(){
        Request<String> request = NoHttp.createStringRequest(NetURL.ARRIVAL, RequestMethod.GET);
        request.add("date",time.getText().toString());
        request.add("warehouseCode",warehouseid);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                Log.d("获取数据",result);
                arrivalBean=JSONObject.parseObject(result,ArrivalBean.class);
                if (arrivalBean!=null){
                    arrivalList.clear();
                    arrivalList.addAll(arrivalBean.getRows());
                    if (arrivalList!=null&&arrivalList.size()>0){
                        list.setVisibility(View.VISIBLE);
                        empty.setVisibility(View.GONE);
                        arrivalAdapter.notifyDataSetChanged();
                    }else {
                        empty.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                    }
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
}
