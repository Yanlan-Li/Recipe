package com.example.recipe.sort;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipe.MainActivity;
import com.example.recipe.R;
import com.example.recipe.adapter.SortAdapter;
import com.example.recipe.bean.Sort;
import com.example.recipe.databinding.FragmentSortBinding;
import com.example.recipe.search.view.SearchActivity;
import com.example.recipe.search.viewmodel.SearchResultViewModel;

import java.util.List;


public class SortFragment extends Fragment implements View.OnClickListener{

    private FragmentSortBinding binding;
    private RecyclerView recyclerView;
    private SortAdapter adapter;
    private View view;
    SortViewModel viewModel;
    MainActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.e("TAG", "onAttach: start" );
        super.onAttach(context);
        mActivity=(MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentSortBinding.inflate(inflater);
        view = binding.getRoot();
        initView();
        return view;
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
        recyclerView=binding.classifyRv;
        adapter=new SortAdapter(getActivity());
        //设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//网格布局
        //设置adapter
        recyclerView.setAdapter(adapter);
        viewModel=new SortViewModel(mActivity.getApplication(),binding);
        binding.setViewModel(viewModel);
    }


}