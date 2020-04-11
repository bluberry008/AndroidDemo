package com.example.riderb.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.riderb.bean.Goods;


public class HomeViewModel extends AndroidViewModel {

    private LiveData<Goods> allLiveGoods;


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Goods> getAllLiveData() {
        return allLiveGoods;
    }



    /* public LiveData<List<Goods>> getAllLiveGoods(){
        BmobQuery<Goods> query = new BmobQuery<Goods>();
//查询playerName叫“比目”的数据
        query.addWhereEqualTo("state", 0);
//返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
//执行查询方法
        query.findObjects(this, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> object) {
                // TODO Auto-generated method stub
                Toast.makeText(HomeViewModel.this, "登录成功", Toast.LENGTH_SHORT).show();
                toast("查询成功：共"+object.size()+"条数据。");
                for (Goods good : object) {
                    //获得playerName的信息
                    good.getAddress();
                    //获得数据的objectId信息
                    //Goods.getObjectId();
                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                    //Goods.getCreatedAt();
                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                toast("查询失败："+msg);
            }
        });
        return null;
    }*/
}