package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recipe.R;
import com.example.recipe.adapter.DetailAdapter;
import com.example.recipe.adapter.MainAdapter;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Step;
import com.example.recipe.databinding.ActivityDetailBinding;
import com.example.recipe.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityDetailBinding binding;
    private RecyclerView detailRecyclerView;
    private DetailAdapter adapter;
    private Recipe recipe;
    //private List<Step> stepList;
   // private List<Material> materialList;
    List<Object> list;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG","Detail onCreate");
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        view=binding.getRoot();
        initView();
        initData();
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
        GridLayoutManager layoutManger = new GridLayoutManager(DetailActivity.this, 1);
        detailRecyclerView =view.findViewById(R.id.detail_rv);
        detailRecyclerView.setLayoutManager(layoutManger);
        detailRecyclerView.setAdapter(adapter);
    }

    private void initData() {

        list=new ArrayList<>();
       // stepList = new ArrayList<>();
       // materialList=new ArrayList<>();

            Log.i("TAG","Detail init data");
        list.add(recipe.getRecipeTitle());
        list.add(recipe.getRecipeImg());
        for(int i=0;i<recipe.getRecipeMaterials().size();i++)
            list.add(recipe.getRecipeMaterials().get(i));
        list.add(null);
            for(int i=0;i<recipe.getRecipeSteps().size();i++)
                list.add(recipe.getRecipeSteps().get(i));
        adapter.setList(list,recipe.getRecipeMaterials().size(),recipe.getRecipeSteps().size());


    }

}