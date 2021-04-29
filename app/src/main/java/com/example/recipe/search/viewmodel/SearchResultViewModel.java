package com.example.recipe.search.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.databinding.FragmentSearchResultBinding;
import com.example.recipe.search.model.SearchResultModel;

public class SearchResultViewModel extends AndroidViewModel {
   SearchResultModel model=new SearchResultModel();

    //构造方法
 public SearchResultViewModel(@NonNull Application application,@NonNull FragmentSearchResultBinding binding,String key){
     super(application);
     SearchResultAdapter adapter=(SearchResultAdapter)binding.searchResultRv.getAdapter();
     assert adapter != null;
     if(!key.equals(""))
        adapter.setList(model.getRecipeListByKey(key));
 }


}
