package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.CollectBean;
import com.pmcc.warehouse.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class CollectAdapter extends BaseAdapter {
    Context context;
    List<CollectBean.RowsBean> collectList = new ArrayList<>();

    public CollectAdapter(Context context, List<CollectBean.RowsBean> collectList) {
        this.context = context;
        this.collectList = collectList;
    }

    @Override
    public int getCount() {
        return collectList.size();
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
        if (!StringUtils.isEmpty(collectList.get(position).getOrgName())){
            viewHolder.category.setText(collectList.get(position).getOrgName());
        }else {
            viewHolder.category.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getName())){
            viewHolder.name.setText(collectList.get(position).getName());
        }else {
            viewHolder.name.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getSpecification())){
            viewHolder.norms.setText(collectList.get(position).getSpecification());
        }else {
            viewHolder.norms.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getUnit())){
            viewHolder.unit.setText(collectList.get(position).getUnit());
        }else {
            viewHolder.unit.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getNum())){
            viewHolder.num.setText(collectList.get(position).getNum());
        }else {
            viewHolder.num.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getPrice())){
            viewHolder.price.setText(collectList.get(position).getPrice());
        }else {
            viewHolder.price.setText("");
        }
        if (!StringUtils.isEmpty(collectList.get(position).getMoney())){
            viewHolder.amount.setText(collectList.get(position).getMoney());
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
