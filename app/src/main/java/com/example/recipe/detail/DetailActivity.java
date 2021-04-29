package com.example.recipe.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recipe.R;
import com.example.recipe.adapter.DetailAdapter;
import com.example.recipe.bean.Recipe;
import com.example.recipe.databinding.ActivityDetailBinding;
import com.example.recipe.search.viewmodel.SearchResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityDetailBinding binding;
    private RecyclerView detailRecyclerView;
    private DetailAdapter adapter;
    private Recipe recipe;
    DetailViewModel viewModel;
    List<Object> list;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG","Detail onCreate");
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        view=binding.getRoot();
        initView();
        initClick();
    }

    private void initClick(){
        binding.detailBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_back_button:
                onBackPressed();
                break;

        }
    }

    private void initView() {//初始化recyclerview
        Log.d("TAG","Detail init view");
        recipe=(Recipe)getIntent().getSerializableExtra("recipe");
        Log.d("TAG","recipe title:"+recipe.getRecipeTitle());
        binding.detailTopName.setText(recipe.getRecipeTitle());
        adapter = new DetailAdapter(DetailActivity.this);
        detailRecyclerView =binding.detailRv;
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
        detailRecyclerView.setAdapter(adapter);
        viewModel=new DetailViewModel(getApplication(),binding,recipe);
        binding.setViewModel(viewModel);
    }


}