package com.example.recipe.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.recipe.databinding.FragmentSearchActionBinding;

public class SearchActionFragment extends Fragment implements View.OnClickListener{
    private FragmentSearchActionBinding binding;
    private View view;
    private FragmentInteraction listerner;

    //  定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void searchActionProcess(String str);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentInteraction) {
            listerner = (FragmentInteraction)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }

    //把传递进来的activity对象释放掉
    @Override
    public void onDetach() {
        super.onDetach();
        listerner = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentSearchActionBinding.inflate(inflater);
        view = binding.getRoot();
        initView();
        return view;
    }

   public void initView(){
        binding.doufu.setOnClickListener(this);
        binding.hongshaoqiezi.setOnClickListener(this);
        binding.hongshaorou.setOnClickListener(this);
        binding.jichi.setOnClickListener(this);
        binding.jirou.setOnClickListener(this);
        binding.kele.setOnClickListener(this);
        binding.niurou.setOnClickListener(this);
        binding.paigu.setOnClickListener(this);
        binding.tangcu.setOnClickListener(this);
        binding.yuecai.setOnClickListener(this);
        binding.tudou.setOnClickListener(this);
        binding.qiezi.setOnClickListener(this);
   }

    @Override
    public void onClick(View view) {
        TextView textView=view.findViewById(view.getId());
        String key=textView.getText().toString();
        listerner.searchActionProcess(key);
    }
}
