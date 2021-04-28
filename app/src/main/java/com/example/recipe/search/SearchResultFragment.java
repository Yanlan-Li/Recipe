package com.example.recipe.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.bean.Data;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.databinding.FragmentSearchResultBinding;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {
    private FragmentSearchResultBinding binding;
    private SearchResultAdapter searchResultAdapter;
    private RecyclerView searchRecyclerView;
    private List<Recipe> recipeList;
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
        searchRecyclerView = view.findViewById(R.id.search_result_rv);
        searchResultAdapter = new SearchResultAdapter(getActivity());

        //设置布局管理器
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置adapter
        searchRecyclerView.setAdapter(searchResultAdapter);
        Bundle bundle=getArguments();
        if(!mActivity.getKey().equals("")){
            key=mActivity.getKey();
            Log.e("TAG", "result key "+key);
            setData();
            //查数据
        }
    }

    private void setData() {
        Log.e("TAG", "setData: start" );
        List<Object> list=new ArrayList<>();
        if(!key.equals("")){
            List<Data> dataList=HttpUtils.getRecipeData(key);
            if(dataList!=null){
                Log.e("TAG", "setData: data is not empty" );
               recipeList= DataTransfer.DataToRecipe(dataList);
               for(int i=0;i<recipeList.size();i++){
                   list.add(recipeList.get(i));
               }
               searchResultAdapter.setList(list);
            }else{
                list.add("empty");
                 searchResultAdapter.setList(list);
                 Log.e("TAG", "setData: data empty" );
            }
        }
    }

}
