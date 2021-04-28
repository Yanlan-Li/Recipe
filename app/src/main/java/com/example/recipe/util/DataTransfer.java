package com.example.recipe.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.example.recipe.bean.Data;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Step;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

public class DataTransfer {

    public static  List<Recipe> DataToRecipe(List<Data> dataList){
        List<Recipe> recipeList=new ArrayList<>();
        for(int i=0;i<dataList.size();i++){
            Data data=dataList.get(i);
            Recipe recipe=new Recipe();
            recipe.setRecipeId(data.getRecipe_id());
            recipe.setRecipeTitle(data.getRecipe_title());
            recipe.setRecipeImg(data.getRecipe_img_url());
            recipe.setRecipeMaterials(StringToMaterial(data.getRecipe_material()));
            recipe.setRecipeSteps(StringToStep(data.getRecipe_step(),data.getRecipe_id()));
            recipeList.add(recipe);
        }
        return recipeList;
    }

    public static List<Material> StringToMaterial(String str){
        List<Material> materialList=new ArrayList<>();
        String[] arr = str.split("/");
        List<String> list = Arrays.asList(arr);
        for(int i=0;i<list.size();i++){
            Material material=new Material();
            material.setMaterialName(list.get(i));
            materialList.add(material);
        }
        return materialList;
    }

    public static List<Step> StringToStep(String str,int recipeId){
        List<Step> stepList=new ArrayList<>();
        String[] arr = str.split("/");
        List<String> list = Arrays.asList(arr);
        for(int i=0;i<list.size();i++){
            Step step=new Step();
            step.setStepId(recipeId);
            step.setStepCount(i+1);
            step.setStepDescription(list.get(i));
            stepList.add(step);
        }
        return  stepList;
    }




}
