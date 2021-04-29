package com.example.recipe.search.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.databinding.FragmentSearchResultBinding;
import com.example.recipe.search.viewmodel.SearchResultViewModel;

public class SearchResultFragment extends Fragment {
    private FragmentSearchResultBinding binding;
    private SearchResultAdapter searchResultAdapter;
    private RecyclerView searchRecyclerView;
    SearchResultViewModel viewModel;
    private View view;
    String key="";
    SearchActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.e("TAG", "onAttach: start" );
        super.onAttach(context);
        mActivity=(SearchActivity) context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "search result onCreate: " );
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("TAG", "search result onCreateView: " );
        binding= FragmentSearchResultBinding.inflate(inflater);
        view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {//初始化recyclerview
        Log.e("TAG", "initView:start ");
        searchRecyclerView =binding.searchResultRv; //view.findViewById(R.id.search_result_rv);
        searchResultAdapter = new SearchResultAdapter(getActivity());
        //设置布局管理器
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        searchRecyclerView.setAdapter(searchResultAdapter);
        key=mActivity.getKey();
        Log.e("TAG", "result key "+key);
        viewModel=new SearchResultViewModel(mActivity.getApplication(),binding,key);
        binding.setViewModel(viewModel);
    }



}
