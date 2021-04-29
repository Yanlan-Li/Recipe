package com.example.recipe.util;

import android.util.Log;

import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Step;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataTransfer {
    //将输入流转换为String
    public static String streamToString(InputStream stream){
        byte[] bytes=null;
        try{
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            while((len=stream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            outputStream.close();
            stream.close();
            bytes=outputStream.toByteArray();
        }catch (Exception e){
            Log.e("MYTAG", "streaToString失败，MESSAGE="+e.getMessage());

        }
        return new String(bytes);
    }

    public static  List<Recipe> DataToRecipe(List<RecipeData> recipeDataList){
        List<Recipe> recipeList=new ArrayList<>();
        for(int i = 0; i< recipeDataList.size(); i++){
            RecipeData recipeData = recipeDataList.get(i);
            Recipe recipe=new Recipe();
            recipe.setRecipeId(recipeData.getRecipe_id());
            recipe.setRecipeTitle(recipeData.getRecipe_title());
            recipe.setRecipeImg(recipeData.getRecipe_img_url());
            recipe.setRecipeMaterials(StringToMaterial(recipeData.getRecipe_material()));
            recipe.setRecipeSteps(StringToStep(recipeData.getRecipe_step(), recipeData.getRecipe_id()));
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
            step.setStepDescription(i+1+"、"+list.get(i));
            stepList.add(step);
        }
        return  stepList;
    }

    public static List<Object> RecipeToObject(List<Recipe> recipeList) {
        List<Object> list=new ArrayList<>();
        for(int i=0;i<recipeList.size();i++){
            list.add(recipeList.get(i));
        }
        return list;
    }

    public static String MaterialToString(List<Material> materialList){
        String materials="";
        for(int i=0;i<materialList.size();i++) {
           materials += materialList.get(i).getMaterialName();
            if(i!=materialList.size()-1) materials+="、";
        }
        return materials;
    }

}
