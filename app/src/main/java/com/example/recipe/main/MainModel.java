package com.example.recipe.main;

import com.example.recipe.util.RecipeData;
import com.example.recipe.bean.Recipe;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MainModel {

    public List<Recipe> getRandomRecypeByN(int n){
        List<Object> list=new ArrayList<>();
        List<RecipeData> recipeDataList = HttpUtils.getRandomRecipe(n);
       return  DataTransfer.DataToRecipe(recipeDataList);

    }





}
