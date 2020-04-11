package com.example.riderb.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.riderb.R;
import com.example.riderb.bean.Goods;

import java.util.Optional;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView poster,recipient,address,detail,count,code;
    RadioButton small,medium,big;
    Button publish;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        initView();

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Goods goods=new Goods();
                goods.setPoster(poster.getText().toString().trim());
                goods.setAddress(address.getText().toString().trim());
                goods.setRecipient(recipient.getText().toString().trim());
                goods.setCode(code.getText().toString().trim());
                goods.setDetail(detail.getText().toString().trim());
                goods.setCount(Integer.parseInt(count.getText().toString().trim()));

                if (small.isChecked()){
                    goods.setSize(0);
                }else if (big.isChecked()){
                    goods.setSize(2);
                }else{
                    goods.setSize(1);
                }
             /*goods.save(new SaveListener<Goods>() {
                 @Override
                 public void done(Goods goods, BmobException e) {

                 }
             });*/
            }
        });
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
       /* final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    public void initView(){
        poster =getActivity().findViewById(R.id.poster);
        recipient=getActivity().findViewById(R.id.recipient);
        address=getActivity().findViewById(R.id.address);
        detail=getActivity().findViewById(R.id.detail);
        count=getActivity().findViewById(R.id.count);
        code=getActivity().findViewById(R.id.code);
        small=getActivity().findViewById(R.id.small);
        medium=getActivity().findViewById(R.id.medium);
        big=getActivity().findViewById(R.id.big);
        publish=getActivity().findViewById(R.id.publish);
    }
}
