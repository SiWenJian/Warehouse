package com.pmcc.warehouse.activity;

import android.os.Bundle;
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
import com.pmcc.warehouse.adapter.UseAdapter;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.bean.RegionBean;
import com.pmcc.warehouse.bean.UseBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.review.MyListView;
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
 */
public class JidianUseActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    MyListView list;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.lin_total)
    LinearLayout linTotal;

    RegionBean regionBean;
    List<RegionBean.RowsBean> regionList=new ArrayList<>();
    List<String> regionName=new ArrayList<>();
    private String regionid="";

    SimpleDateFormat formatter_year, formatter_mouth;
    String year_str = "";
    String mouth_str = "";
    Calendar startDate, endDate, selectedDate;

    UseBean useBean;
    List<UseBean.RowsBean> useList=new ArrayList<>();

    UseAdapter useAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jidian_use);
        ButterKnife.bind(this);
        setHorizontalScreen(this);
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

        startTime.setText(year_str + "-" + mouth_str);
        endTime.setText(year_str + "-" + mouth_str);
        title.setText("平煤股份十一矿机电专业配件" + year_str + "-" + mouth_str + "使用情况统计月报");

        useAdapter=new UseAdapter(this,useList);
        list.setAdapter(useAdapter);
    }

    public void initData(){
        Request<String> request = NoHttp.createStringRequest(NetURL.REGION, RequestMethod.GET);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                regionBean= JSONObject.parseObject(result,RegionBean.class);
                if (regionBean!=null){
                    regionList.clear();
                    regionList.addAll(regionBean.getRows());
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

    @OnClick({R.id.rl_back, R.id.category, R.id.start_time, R.id.end_time, R.id.reset, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.category:
                if (regionList==null||regionList.size()==0){
                    ToastUtils.getIntance().showToast("未获得使用区域,请稍后再试");
                    initData();
                    return;
                }
                regionName.clear();
                for (RegionBean.RowsBean rowsBean:regionList){
                    regionName.add(rowsBean.getMaterialName());
                }
                OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        category.setText(regionName.get(options1));
                        regionid = regionList.get(options1).getId();
                    }
                })
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .setTitleText("使用区域")
                        .build();
                optionsPickerView.setPicker(regionName);
                optionsPickerView.show();
                break;
            case R.id.start_time:
                TimePickerView timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        startTime.setText(getTime(date));
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
            case R.id.end_time:
                TimePickerView timePickerView1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        endTime.setText(getTime(date));
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
                timePickerView1.show();
                break;
            case R.id.reset:
                category.setText("请选择");
                regionid = "";
                startTime.setText(year_str + "-" + mouth_str);
                endTime.setText(year_str + "-" + mouth_str);
                linTotal.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
                title.setText("平煤股份十一矿机电专业配件" + year_str + "-" + mouth_str + "入库统计月报");
                break;
            case R.id.query:
                getUseList();
                if (startTime.getText().toString().equals(endTime.getText().toString())){
                    title.setText("平煤股份十一矿机电专业配件" + startTime.getText().toString() + "使用情况统计月报");
                }else {
                    title.setText("平煤股份十一矿机电专业配件" + startTime.getText().toString() + "-" + endTime.getText().toString() + "使用情况统计月报");
                }
                break;
        }
    }

    //获取使用列表
    public void getUseList(){
        Request<String> request = NoHttp.createStringRequest(NetURL.USELIST, RequestMethod.GET);
        request.add("uesSys",regionid);
        request.add("startDate",startTime.getText().toString());
        request.add("endDate",endTime.getText().toString());
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                useBean=JSONObject.parseObject(result,UseBean.class);
                if (useBean!=null){
                    useList.clear();
                    useList.addAll(useBean.getRows());
                    if (useList!=null&&useList.size()>0){
                        useAdapter.notifyDataSetChanged();
                        list.setVisibility(View.VISIBLE);
                        empty.setVisibility(View.GONE);
                        linTotal.setVisibility(View.VISIBLE);
                        total.setText(useBean.getObject());
                    }else {
                        list.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                        linTotal.setVisibility(View.GONE);
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
