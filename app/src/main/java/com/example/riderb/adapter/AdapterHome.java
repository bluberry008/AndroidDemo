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
import com.example.riderb.activity.DetailPage;
import com.example.riderb.bean.Goods;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<Goods> goods;

    public AdapterHome(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.cell_c,parent,false);
        return new AdapterHome.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        Goods g=goods.get(position);
        myViewHolder.textView1.setText(g.getPoster());
        myViewHolder.textView2.setText("收货地址："+g.getAddress());
        myViewHolder.textView3.setText(String.valueOf(g.getCount())+"件");
        if (g.getSize().equals("2")){
            myViewHolder.textView4.setText("大件");
        }else if (g.getSize().equals("0")){
            myViewHolder.textView4.setText("小件");
        }else{
            myViewHolder.textView4.setText("有大有小");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = myViewHolder.getAdapterPosition();
                Intent intent = new Intent(context, DetailPage.class);
                intent.putExtra("id", goods.get(i).getObjectId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView textView1, textView2, textView3, textView4;

        public MyViewHolder(View itemView) {
            super(itemView);
            /*//获取子布局的控件实例
            tv_notification=(TextView) itemView.findViewById(R.id.tv_notification);
            tv_notification_time=(TextView) itemView.findViewById(R.id.tv_notification_time);
*/
            textView1 = itemView.findViewById(R.id.editText);
            textView2 = itemView.findViewById(R.id.editText2);
            textView3 = itemView.findViewById(R.id.editText3);
            textView4 = itemView.findViewById(R.id.editText4);

        }
    }
}
