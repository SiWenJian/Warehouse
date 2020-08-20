package com.pmcc.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pmcc.warehouse.R;
import com.pmcc.warehouse.bean.CategoryBean;
import com.pmcc.warehouse.bean.IncomeBean;
import com.pmcc.warehouse.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create on
 */
public class JidianIncomeAdapter extends BaseAdapter {
    public Context context;
    public List<IncomeBean.RowsBean> incomeList = new ArrayList<>();

    public JidianIncomeAdapter(Context context, List<IncomeBean.RowsBean> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    @Override
    public int getCount() {
        return incomeList.size();
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

        if (!StringUtils.isEmpty(incomeList.get(position).getTypeName())){
            viewHolder.category.setText(incomeList.get(position).getTypeName());
        }else {
            viewHolder.category.setText("");
        }

        if (!StringUtils.isEmpty(incomeList.get(position).getName())){
            viewHolder.name.setText(incomeList.get(position).getName());
        }else {
            viewHolder.name.setText("");
        }
        if (!StringUtils.isEmpty(incomeList.get(position).getSpecification())){
            viewHolder.norms.setText(incomeList.get(position).getSpecification());
        }else {
            viewHolder.norms.setText("");
        }
        if (!StringUtils.isEmpty(incomeList.get(position).getUnit())){
            viewHolder.unit.setText(incomeList.get(position).getUnit());
        }else {
            viewHolder.unit.setText("");
        }
        if (!StringUtils.isEmpty(incomeList.get(position).getNum())){
            viewHolder.num.setText(incomeList.get(position).getNum());
        }else {
            viewHolder.num.setText("");
        }
        if (!StringUtils.isEmpty(incomeList.get(position).getPrice())){
            viewHolder.price.setText(incomeList.get(position).getPrice());
        }else {
            viewHolder.price.setText("");
        }
        if (!StringUtils.isEmpty(incomeList.get(position).getMoney())){
            viewHolder.amount.setText(incomeList.get(position).getMoney());
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
