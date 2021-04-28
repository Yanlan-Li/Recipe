package com.example.recipe.bean;

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
}
