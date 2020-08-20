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
import com.pmcc.warehouse.adapter.JidianExpendAdapter;
import com.pmcc.warehouse.base.BaseActivity;
import com.pmcc.warehouse.bean.ExpendBean;
import com.pmcc.warehouse.bean.RanksBean;
import com.pmcc.warehouse.config.NetURL;
import com.pmcc.warehouse.netUtil.NoCallServer;
import com.pmcc.warehouse.netUtil.NoHttpListener;
import com.pmcc.warehouse.review.MyListView;
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
 */
public class JidianExpendActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.ranks)
    TextView ranks;
    @BindView(R.id.date)
    LinearLayout date;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.list)
    MyListView list;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.lin_total)
    LinearLayout linTotal;
    @BindView(R.id.empty)
    TextView empty;

    JidianExpendAdapter jidianExpendAdapter;
    SimpleDateFormat formatter_year, formatter_mouth;
    String year_str = "";
    String mouth_str = "";
    Calendar startDate, endDate, selectedDate;

    RanksBean ranksBean;
    List<RanksBean.RowsBean> ranksList = new ArrayList<>();
    List<String> ranksname = new ArrayList<>();

    private String ranksid = "";
    ExpendBean expendBean;
    List<ExpendBean.RowsBean> expendList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jidian_expend);
        ButterKnife.bind(this);
        setHorizontalScreen(this);
        initView();
        initData();
    }

    public void initView() {
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
        title.setText("平煤股份十一矿机电专业配件" + year_str + "-" + mouth_str + "资金支出情况统计月报");
        jidianExpendAdapter = new JidianExpendAdapter(this, expendList);
        list.setAdapter(jidianExpendAdapter);
    }

    /**
     * 获取区队信息
     */
    public void initData() {
        Request<String> request = NoHttp.createStringRequest(NetURL.JIDAINRANKS, RequestMethod.GET);
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                ranksBean = JSONObject.parseObject(result, RanksBean.class);
                if (ranksBean != null) {
                    ranksList.clear();
                    ranksList.addAll(ranksBean.getRows());
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

    @OnClick({R.id.rl_back, R.id.ranks, R.id.date, R.id.reset, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ranks:
                if (ranksList == null || ranksList.size() == 0) {
                    ToastUtils.getIntance().showToast("未获取到区队信息,请稍后再试");
                    initData();
                    return;
                }
                ranksname.clear();
                for (int i = 0; i < ranksList.size(); i++) {
                    ranksname.add(ranksList.get(i).getTitle());
                }
                OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        ranks.setText(ranksname.get(options1));
                        ranksid = ranksList.get(options1).getCode();
                    }
                })
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .setTitleText("区队")
                        .build();
                optionsPickerView.setPicker(ranksname);
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
                ranks.setText("请选择");
                ranksid = "";
                time.setText(year_str + "-" + mouth_str);
                linTotal.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
                title.setText("平煤股份十一矿机电专业配件" + year_str + "-" + mouth_str + "资金支出情况统计月报");
                break;
            case R.id.query:
                expendList();
                title.setText("平煤股份十一矿机电专业配件" + time.getText().toString() + "资金支出情况统计月报");
                break;
        }
    }

    public void expendList() {
        Request<String> request = NoHttp.createStringRequest(NetURL.JIDIANEXPEND, RequestMethod.GET);
        request.add("orgCode", ranksid);
        request.add("date", time.getText().toString());
        NoCallServer.getInstance().requestWithHeader(this, 0, request, new NoHttpListener() {
            @Override
            public void onSucceed(int what, String result) {
                expendBean = JSONObject.parseObject(result, ExpendBean.class);
                if (expendBean != null) {
                    expendList.clear();
                    expendList.addAll(expendBean.getRows());
                    if (expendList != null) {
                        list.setVisibility(View.VISIBLE);
                        jidianExpendAdapter.notifyDataSetChanged();
                    }
                    total.setText(expendBean.getObject());
                    linTotal.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
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
