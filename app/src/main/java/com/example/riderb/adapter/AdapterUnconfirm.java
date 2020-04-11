package com.example.riderb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riderb.R;
import com.example.riderb.activity.ConfirmOrder;
import com.example.riderb.activity.DetailPage;
import com.example.riderb.activity.Unconfirm;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.Orders;

import java.util.ArrayList;
import java.util.List;

public class AdapterUnconfirm extends RecyclerView.Adapter<AdapterUnconfirm.MyViewHolder> {
    List<Orders> goods=new ArrayList<>();
    Context context;

    public AdapterUnconfirm(Context context,List<Orders> goods) {
        this.context=context;
        this.goods = goods;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5 ;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.editText);
            textView2=itemView.findViewById(R.id.editText2);
            textView3=itemView.findViewById(R.id.editText3);
            textView4=itemView.findViewById(R.id.editText4);
            textView5=itemView.findViewById(R.id.textView15);
        }
    }
    @NonNull
    @Override
    public AdapterUnconfirm.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_con,parent,false);
        return new AdapterUnconfirm.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterUnconfirm.MyViewHolder holder, int position) {
        Orders g=goods.get(position);
        holder.textView1.setText(g.getPoster());
        holder.textView2.setText("送货人："+g.getEmailuser());
        holder.textView3.setText(String.valueOf(g.getCount())+"件");
        holder.textView4.setText("收货地址："+g.getAddress());
        holder.textView5.setText("创建时间："+g.getCreatedAt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getAdapterPosition();
                Intent intent = new Intent(context, ConfirmOrder.class);
                intent.putExtra("id", goods.get(i).getObjectId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}
