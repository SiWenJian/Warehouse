package com.pmcc.warehouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create on
 * 物资管控列表
 */
public class DataReportActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.jidian_expend)
    CardView jidianExpend;
    @BindView(R.id.jidian_income)
    CardView jidianIncome;
    @BindView(R.id.material)
    CardView material;
    @BindView(R.id.jidian_use)
    CardView jidianUse;
    @BindView(R.id.jidian_receive)
    CardView jidianReceive;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datareport);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.jidian_expend, R.id.jidian_income, R.id.material, R.id.jidian_use, R.id.jidian_receive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.jidian_expend:
                intent=new Intent(this,JidianExpendActivity.class);
                startActivity(intent);
                break;
            case R.id.jidian_income:
                intent=new Intent(this,JidianIncomeActivity.class);
                startActivity(intent);
                break;
            case R.id.material:
                intent=new Intent(this,MaterialActivity.class);
                startActivity(intent);
                break;
            case R.id.jidian_use:
                intent=new Intent(this, JidianUseActivity.class);
                startActivity(intent);
                break;
            case R.id.jidian_receive:
                intent=new Intent(this,JidianCollectActivity.class);
                startActivity(intent);
                break;
        }
    }
}
