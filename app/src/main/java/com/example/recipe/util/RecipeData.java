package com.example.recipe.util;

public class RecipeData {
    private int recipe_id;
    private String recipe_title;
    private String recipe_img_url;
    private String recipe_material;
    private String recipe_step;
    private String recipe_type;

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String revipe_title) {
        this.recipe_title = revipe_title;
    }

    public String getRecipe_img_url() {
        return recipe_img_url;
    }

    public void setRecipe_img_url(String recipe_img_url) {
        this.recipe_img_url = recipe_img_url;
    }

    public String getRecipe_material() {
        return recipe_material;
    }

    public void setRecipe_material(String recipe_material) {
        this.recipe_material = recipe_material;
    }

    public String getRecipe_step() {
        return recipe_step;
    }

    public void setRecipe_step(String recipe_step) {
        this.recipe_step = recipe_step;
    }

    public String getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(String recipe_type) {
        this.recipe_type = recipe_type;
    }
}
