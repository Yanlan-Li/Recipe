package com.example.recipe.sort;

import com.example.recipe.bean.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortModel {
     List<String> allSort=Arrays.asList("家常菜","素菜","凉菜","面食","粥","汤","下饭菜","西餐","川菜",
            "烘培","饮品","糕点","荤菜","腌制","卤菜","主食","农家菜","下酒菜","日料","韩料","海鲜","甜点",
            "云南菜","主食","早餐","清淡","...");

    private List<Sort> sortList;

    public List<Sort> getSortList(){
        sortList=new ArrayList<>();
        for(int i=0;i<allSort.size();i++){
            Sort sort=new Sort(i+1,allSort.get(i).toString());
            sortList.add(sort);
        }
        return sortList;
    }




}
