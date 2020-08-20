package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.ArrivalBean;
import com.pmcc.warehouse.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class ArrivalAdapter extends BaseAdapter {
    Context context;
    List<ArrivalBean.RowsBean> arrivalList = new ArrayList<>();

    public ArrivalAdapter(Context context, List<ArrivalBean.RowsBean> arrivalList) {
        this.context = context;
        this.arrivalList=arrivalList;
    }

    @Override
    public int getCount() {
        return arrivalList.size();
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
        if (!StringUtils.isEmpty(arrivalList.get(position).getCode())){
            viewHolder.category.setText(arrivalList.get(position).getCode());
        }else {
            viewHolder.category.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getName())){
            viewHolder.name.setText(arrivalList.get(position).getName());
        }else {
            viewHolder.name.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getSpecification())){
            viewHolder.norms.setText(arrivalList.get(position).getSpecification());
        }else {
            viewHolder.norms.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getUnit())){
            viewHolder.unit.setText(arrivalList.get(position).getUnit());
        }else {
            viewHolder.unit.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getPlanNum())){
            viewHolder.num.setText(arrivalList.get(position).getPlanNum());
        }else {
            viewHolder.num.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getNum())){
            viewHolder.price.setText(arrivalList.get(position).getNum());
        }else {
            viewHolder.price.setText("");
        }
        if (!StringUtils.isEmpty(arrivalList.get(position).getProportion())){
            viewHolder.amount.setText(arrivalList.get(position).getProportion());
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
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.norms)
        TextView norms;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.amount)
        TextView amount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
