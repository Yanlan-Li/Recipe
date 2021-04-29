package com.example.recipe.search.model;

import android.util.Log;

import com.example.recipe.util.RecipeData;
import com.example.recipe.bean.Recipe;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchResultModel {
    private List<Recipe> recipeList;
    public List<Object> getRecipeListByKey(String key){
        List<RecipeData> recipeDataList =HttpUtils.getRecipeByKey(key);
        if(recipeDataList !=null){
            Log.e("TAG", " data is not empty" );
            List<Recipe> recipeList= DataTransfer.DataToRecipe(recipeDataList);
           return DataTransfer.RecipeToObject(recipeList);
        }else{
            List<Object> list=new ArrayList<>();
            list.add("empty");
            Log.e("TAG", "data empty" );
            return list;
        }
    }

}
