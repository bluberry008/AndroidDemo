package com.example.riderb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.riderb.R;
import com.example.riderb.bean.Orders;

import java.util.ArrayList;
import java.util.List;

public class AdapterUnfinish extends RecyclerView.Adapter<AdapterUnfinish.unfinishViewHolder> {
    List<Orders> goods=new ArrayList<>();

    public AdapterUnfinish(List<Orders> goods) {
        this.goods = goods;
    }

    @NonNull
    @Override
    public unfinishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new AdapterUnfinish.unfinishViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull unfinishViewHolder holder, int position) {
        Orders g=goods.get(position);
        holder.textView1.setText(g.getPoster());
        holder.textView2.setText("接单时间："+g.getCreatedAt());
        holder.textView3.setText("取货码："+g.getCode());
        holder.textView4.setText("联系人："+g.getContact());
        holder.textView5.setText("收货地址："+g.getAddress());
        holder.textView6.setText("联系方式："+g.getContact());
        holder.textView7.setText("详细要求："+g.getDetail());

        //详细要求，联系方式
    }


    @Override
    public int getItemCount() {
        return goods.size();
    }

    static class unfinishViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4 ,textView5,textView6,textView7;

        public unfinishViewHolder(View itemView) {
            super(itemView);
            /*//获取子布局的控件实例
            tv_notification=(TextView) itemView.findViewById(R.id.tv_notification);
            tv_notification_time=(TextView) itemView.findViewById(R.id.tv_notification_time);
*/
            textView1=itemView.findViewById(R.id.editText);
            textView2=itemView.findViewById(R.id.editText2);
            textView3=itemView.findViewById(R.id.editText3);
            textView4=itemView.findViewById(R.id.editText4);
            textView5=itemView.findViewById(R.id.edit5);
            textView6=itemView.findViewById(R.id.textView_contract);
            textView7=itemView.findViewById(R.id.textView_detail);
        }
    }
}
