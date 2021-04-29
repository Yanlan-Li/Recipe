package com.example.recipe.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recipe.adapter.MainAdapter;
import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.databinding.FragmentMainBinding;
import com.example.recipe.databinding.FragmentSearchResultBinding;

public class MainViewModel extends AndroidViewModel {
    MainModel model=new MainModel();

    public MainViewModel(@NonNull Application application, @NonNull FragmentMainBinding binding){
        super(application);
        MainAdapter adapter=(MainAdapter)binding.mainRv.getAdapter();
        assert adapter != null;
        adapter.setList(model.getRandomRecypeByN(18));
    }




}
