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

public class AdapterFinish extends RecyclerView.Adapter<AdapterFinish.MyViewHolder> {
    List<Orders> goods=new ArrayList<>();

    public AdapterFinish(List<Orders> goods) {
        this.goods = goods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_c,parent,false);
        return new AdapterFinish.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Orders g=goods.get(position);
        holder.textView1.setText(g.getPoster());
        holder.textView2.setText("完成时间："+g.getUpdatedAt());
        holder.textView3.setText("收货人:"+g.getRecipient());
        holder.textView4.setText(g.getContact());
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4 ;

        public MyViewHolder(View itemView) {
            super(itemView);
            /*//获取子布局的控件实例
            tv_notification=(TextView) itemView.findViewById(R.id.tv_notification);
            tv_notification_time=(TextView) itemView.findViewById(R.id.tv_notification_time);
*/
            textView1=itemView.findViewById(R.id.editText);
            textView2=itemView.findViewById(R.id.editText2);
            textView3=itemView.findViewById(R.id.editText3);
            textView4=itemView.findViewById(R.id.editText4);
        }
    }
}
