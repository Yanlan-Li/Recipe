package com.example.recipe.detail;

import android.util.Log;

import com.example.recipe.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

public class DetailModel {

    public List<Object> getAdapterList(Recipe recipe){
        List<Object> list=new ArrayList<>();
        Log.i("TAG","Detail init data");
        list.add(recipe);
        list.add(null);
        for(int i=0;i<recipe.getRecipeMaterials().size();i++)
            list.add(recipe.getRecipeMaterials().get(i));
        list.add(null);
        for(int i=0;i<recipe.getRecipeSteps().size();i++)
            list.add(recipe.getRecipeSteps().get(i));
        return list;
    }

}
