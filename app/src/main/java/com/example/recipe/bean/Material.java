package com.example.recipe.bean;

import java.io.Serializable;

public class Material implements Serializable {

    private String materialName;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

}

