package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.ExpendBean;
import com.pmcc.warehouse.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class JidianExpendAdapter extends BaseAdapter {
    Context context;
    List<ExpendBean.RowsBean> expendList=new ArrayList<>();

    public JidianExpendAdapter(Context context,List<ExpendBean.RowsBean> expendList) {
        this.context = context;
        this.expendList=expendList;
    }

    @Override
    public int getCount() {
        return expendList.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_jidianexpend, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (!StringUtils.isEmpty(expendList.get(position).getOrgName())){
            viewHolder.name.setText(expendList.get(position).getOrgName());
        }else {
            viewHolder.name.setText("");
        }
        if (!StringUtils.isEmpty(expendList.get(position).getUpper())){
            viewHolder.first.setText(expendList.get(position).getUpper());
        }else {
            viewHolder.first.setText("");
        }
        if (!StringUtils.isEmpty(expendList.get(position).getIn())){
            viewHolder.middle.setText(expendList.get(position).getIn());
        }else {
            viewHolder.middle.setText("");
        }
        if (!StringUtils.isEmpty(expendList.get(position).getLower())){
            viewHolder.last.setText(expendList.get(position).getLower());
        }else {
            viewHolder.last.setText("");
        }
        if (!StringUtils.isEmpty(expendList.get(position).getSum())){
            viewHolder.total.setText(expendList.get(position).getSum());
        }else {
            viewHolder.total.setText("");
        }
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.first)
        TextView first;
        @BindView(R.id.middle)
        TextView middle;
        @BindView(R.id.last)
        TextView last;
        @BindView(R.id.total)
        TextView total;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
