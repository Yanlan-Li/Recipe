package com.example.recipe.bean;

import java.io.Serializable;

//步骤
public class Step implements Serializable {
    private int recipeId; //
    private int stepCount;//第几步
    private String stepDescription;


    public int getStepId() {
        return recipeId;
    }

    public void setStepId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

}
