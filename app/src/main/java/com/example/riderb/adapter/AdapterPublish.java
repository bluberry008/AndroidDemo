package com.example.riderb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.riderb.R;
import com.example.riderb.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class AdapterPublish extends RecyclerView.Adapter<AdapterPublish.MyViewHolder> {
    List<Goods> goods=new ArrayList<>();


    public AdapterPublish(List<Goods> goods) {
        this.goods = goods;
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
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_c,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Goods g=goods.get(position);
        holder.textView1.setText(g.getPoster());
        holder.textView2.setText("收货地址："+g.getAddress());
        holder.textView3.setText(String.valueOf(g.getCount())+"件");
        if (g.getSize().equals("2")){
            holder.textView4.setText("大件");
        }else if (g.getSize().equals("0")){
            holder.textView4.setText("小件");
        }else{
            holder.textView4.setText("有大有小");
        }
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}
