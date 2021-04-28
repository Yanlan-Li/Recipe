package com.example.recipe.sort;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipe.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.adapter.SortAdapter;
import com.example.recipe.bean.Sort;
import com.example.recipe.databinding.FragmentSortBinding;
import com.example.recipe.search.SearchActivity;
import com.example.recipe.search.SearchResultFragment;

import java.util.ArrayList;
import java.util.List;


public class SortFragment extends Fragment implements View.OnClickListener{

    private FragmentSortBinding binding;
    private RecyclerView recyclerView;
    private SortAdapter adapter;
    private List<Sort> list;
    private View view;

    private SortModel model;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentSortBinding.inflate(inflater);
        view = binding.getRoot();
       // SortViewModel mainViewModel=new SortViewModel(getActivity().getApplication());//实例化ViewModel
        //mainViewModel=ViewModelProviders.of(this).get(MainViewModel);
        //binding.setViewModel(mainViewModel);
        model=new SortModel();
        initView();
        initData();
        initClick();
        return view;
    }


    private void initClick(){
        binding.sortSearch.setOnClickListener(this);
        adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textView=view.findViewById(R.id.sort);
                String key=textView.getText().toString();
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_search:
                Log.i("sort frag","search click");
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("key","");
                startActivity(intent);
                break;
        }
    }

    private void initView() {//初始化recyclerview
        recyclerView=view.findViewById(R.id.classify_rv);
        adapter=new SortAdapter();
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//网格布局
        //设置adapter
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        list= model.getSortList();
        //设置Item增加
        adapter.setList(list);
    }


}