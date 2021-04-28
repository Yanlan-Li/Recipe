package com.example.recipe.bean;

public class Sort {
    private int sortId;
    private String sortName;

    public Sort(int sortId,String sortName){
        this.sortId=sortId;
        this.sortName=sortName;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}
