package com.example.recipe.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.recipe.util.DataTransfer;
import com.squareup.picasso.Picasso;//Square公司出品的一款非常优秀的开源图片加载库
import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private int recipeId;
    private String recipeImg;
    private String recipeTitle;
    private List<Material> recipeMaterials;  //材料
    private List<Step> recipeSteps;//步骤


    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public List<Material> getRecipeMaterials() {
        return recipeMaterials;
    }

    public void setRecipeMaterials(List<Material> recipeMaterials) {
        this.recipeMaterials = recipeMaterials;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public List<Step> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<Step> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    @BindingAdapter("url")  //用来设置view的属性值,对某个控件进行复杂操作
    public static void getImage(ImageView view, String url){
        Picasso.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("material")
    public static void getMaterial(TextView textView, List<Material> materialList){
        String s= DataTransfer.MaterialToString(materialList);
        textView.setText(s);
    }
}
