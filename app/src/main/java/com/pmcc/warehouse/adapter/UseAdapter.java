package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.UseBean;
import com.pmcc.warehouse.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class UseAdapter extends BaseAdapter {
    Context context;
    List<UseBean.RowsBean> useList = new ArrayList<>();

    public UseAdapter(Context context, List<UseBean.RowsBean> useList) {
        this.context = context;
        this.useList = useList;
    }

    @Override
    public int getCount() {
        return useList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (!StringUtils.isEmpty(useList.get(position).getUesSysName())){
            viewHolder.category.setText(useList.get(position).getUesSysName());
        }else {
            viewHolder.category.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getName())){
            viewHolder.name.setText(useList.get(position).getName());
        }else {
            viewHolder.name.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getSpecification())){
            viewHolder.norms.setText(useList.get(position).getSpecification());
        }else {
            viewHolder.norms.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getUnit())){
            viewHolder.unit.setText(useList.get(position).getUnit());
        }else {
            viewHolder.unit.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getNum())){
            viewHolder.num.setText(useList.get(position).getNum());
        }else {
            viewHolder.num.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getPrice())){
            viewHolder.price.setText(useList.get(position).getPrice());
        }else {
            viewHolder.price.setText("");
        }
        if (!StringUtils.isEmpty(useList.get(position).getMoney())){
            viewHolder.amount.setText(useList.get(position).getMoney());
        }else {
            viewHolder.amount.setText("");
        }
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.norms)
        TextView norms;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.amount)
        TextView amount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

