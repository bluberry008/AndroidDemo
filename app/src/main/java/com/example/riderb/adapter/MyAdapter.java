package com.example.riderb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riderb.R;
import com.example.riderb.activity.DetailPage;
import com.example.riderb.activity.Login;
import com.example.riderb.bean.Goods;
import com.example.riderb.bean.User;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private  Context context;
    List<Goods> data;
    private final int N_TYPE=0;
    private final int F_TYPE=1;
    private int Max_num=15;
    private boolean isfootview=true;
    User user;
    public MyAdapter(Context context, List<Goods> data) {
        this.data = data;
       this.context=context;
    }

     class RecyclerViewHolder extends RecyclerView.ViewHolder {


        public TextView textView1,textView2,textView3,textView4,loading ;
        public RecyclerViewHolder(View itemView,int view_type) {
            super(itemView);
            if (view_type==N_TYPE) {
                textView1 = itemView.findViewById(R.id.editText);
                textView2 = itemView.findViewById(R.id.editText2);
                textView3 = itemView.findViewById(R.id.editText3);
                textView4 = itemView.findViewById(R.id.editText4);
            }else if (view_type==F_TYPE) {
                loading=itemView.findViewById(R.id.footText);
            }
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_c,parent,false);
        View footview=LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item,parent,false);
        if (viewType==F_TYPE){
            return new RecyclerViewHolder(footview,F_TYPE);
        }else{
            return new RecyclerViewHolder(view,N_TYPE);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isfootview&&getItemViewType(position)==F_TYPE){
            final  RecyclerViewHolder recyclerViewHolder= (RecyclerViewHolder) holder;
            recyclerViewHolder.loading.setText("加载中。。。");
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Max_num+=8;
                    notifyDataSetChanged();
                }
            },2000);
        }else{
            final  RecyclerViewHolder recyclerViewHolder= (RecyclerViewHolder) holder;
             Goods g=data.get(position);
            recyclerViewHolder.textView1.setText(g.getPoster());
            recyclerViewHolder.textView2.setText("收货地址："+g.getAddress());
            recyclerViewHolder.textView3.setText(String.valueOf(g.getCount())+"件");
            if (g.getSize().equals("2")){
                recyclerViewHolder.textView4.setText("大件");
            }else if (g.getSize().equals("0")){
                recyclerViewHolder.textView4.setText("小件");
            }else{
                recyclerViewHolder.textView4.setText("有大有小");
           }
            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user=BmobUser.getCurrentUser(User.class);
                    int position=recyclerViewHolder.getAdapterPosition();
                    if (user!=null){
                        Intent in=new Intent(context, DetailPage.class);
                        in.putExtra("id",data.get(position).getObjectId());
                        context.startActivity(in);

                    }else{
                        Toast.makeText(context,"请登录",Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Login.class));
                    }
                }
            });
        }


      /*  Goods g=goods.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getAdapterPosition();
                Intent intent = new Intent(context, DetailPage.class);
                intent.putExtra("id", goods.get(i).getObjectId());
                context.startActivity(intent);
            }
        });

*/
    }

    @Override
    public int getItemViewType(int position) {
        if (position==Max_num-1){
            return F_TYPE;
        }else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (data.size()<Max_num) {
            return data.size();
        }else {
            return Max_num;
        }
    }

}
