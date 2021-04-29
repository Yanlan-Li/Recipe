package com.example.recipe.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recipe.adapter.DetailAdapter;
import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.bean.Recipe;
import com.example.recipe.databinding.ActivityDetailBinding;
import com.example.recipe.databinding.FragmentSearchResultBinding;
import com.example.recipe.search.model.SearchResultModel;

public class DetailViewModel  extends AndroidViewModel {
   DetailModel model=new DetailModel();

    //构造方法
    public DetailViewModel(@NonNull Application application, @NonNull ActivityDetailBinding binding, Recipe recipe){
        super(application);
        DetailAdapter adapter=(DetailAdapter)binding.detailRv.getAdapter();
        assert adapter != null;
        adapter.setList(model.getAdapterList(recipe),recipe.getRecipeMaterials().size(),recipe.getRecipeSteps().size());
    }
}
