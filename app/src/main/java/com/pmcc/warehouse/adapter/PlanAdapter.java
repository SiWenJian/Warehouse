package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.DetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class PlanAdapter extends BaseAdapter {
    Context context;
    List<DetailBean.RowsBean.PlanListBean> planList=new ArrayList<>();
    ClickItem clickItem;

    public PlanAdapter(Context context, List<DetailBean.RowsBean.PlanListBean> planList,ClickItem clickItem) {
        this.context = context;
        this.planList = planList;
        this.clickItem=clickItem;
    }

    @Override
    public int getCount() {
        return planList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_plan, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.unloading.setTag(position);
        viewHolder.unloading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.onItemClick(v,position);
            }
        });
        viewHolder.name.setText(planList.get(position).getPlanName());
        viewHolder.planCount.setText(planList.get(position).getPlanNum());
        viewHolder.completeCount.setText(planList.get(position).getWarehousingNum());
        viewHolder.surplusCount.setText(planList.get(position).getResidualNum());
        return convertView;
    }


    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.plan_count)
        TextView planCount;
        @BindView(R.id.complete_count)
        TextView completeCount;
        @BindView(R.id.surplus_count)
        TextView surplusCount;
        @BindView(R.id.unloading)
        Button unloading;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface ClickItem{
        void onItemClick(View v, int position);
    }
}
