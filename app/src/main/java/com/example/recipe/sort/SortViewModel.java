package com.example.recipe.sort;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recipe.adapter.SearchResultAdapter;
import com.example.recipe.adapter.SortAdapter;
import com.example.recipe.databinding.FragmentSearchResultBinding;
import com.example.recipe.databinding.FragmentSortBinding;

public class SortViewModel  extends AndroidViewModel {
    SortModel sortModel=new SortModel();

    public SortViewModel(@NonNull Application application, @NonNull FragmentSortBinding binding){
        super(application);
        SortAdapter adapter=(SortAdapter)binding.classifyRv.getAdapter();
        assert adapter != null;
        adapter.setList(sortModel.getSortList());
    }

}
