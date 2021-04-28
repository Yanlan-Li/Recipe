package com.example.recipe.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipe.MainActivity;
import com.example.recipe.R;
import com.example.recipe.adapter.MainAdapter;
import com.example.recipe.bean.Data;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Sort;
import com.example.recipe.databinding.FragmentMainBinding;
import com.example.recipe.DetailActivity;
import com.example.recipe.search.SearchActionFragment;
import com.example.recipe.search.SearchActivity;
import com.example.recipe.sort.SortFragment;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements View.OnClickListener{

    private FragmentMainBinding binding;
    private RecyclerView mainRecyclerView;
    private MainAdapter adapter;
    private List<Recipe> recipeList;
    private View view;
    private MainFragment.FragmentInteraction listerner;

    //  定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void searchActionProcess(String str);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainFragment.FragmentInteraction) {
            listerner = (MainFragment.FragmentInteraction)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }

    //把传递进来的activity对象释放掉
    @Override
    public void onDetach() {
        super.onDetach();
        listerner = null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMainBinding .inflate(inflater);
        view = binding.getRoot();
        initView();
        initData();
        initClick();
        return view;
    }
    private void initClick(){
        binding.mainSearch.setOnClickListener(this);
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onSortClick(View view, int position) {
                Log.i("Main","Sort click");
                String searchKey="";
                switch (view.getId()){
                    case R.id.main_sort_jiachang:
                        searchKey="家常菜";
                        break;
                    case R.id.main_sort_hongpei:
                        searchKey="烘培";
                        break;
                    case R.id.main_sort_mianshi:
                        searchKey="面";
                        break;
                    case R.id.main_sort_chuancai:
                        searchKey="川菜";
                        break;
                    case R.id.main_sort_sucai:
                        searchKey="素菜";
                        break;
                    case R.id.main_sort_liangcai:
                        searchKey="凉菜";
                        break;
                    case R.id.main_sort_tang:
                        searchKey="汤";
                        break;
                    case R.id.main_sort_xican:
                        searchKey="西式";
                        break;
                    case R.id.main_sort_xiafan:
                        searchKey="下饭菜";
                        break;
                    case R.id.main_sort_allsort:
                       searchKey="";
                        break;
                }
                  if(view.getId()!=R.id.main_sort_allsort) {
                      Intent intent = new Intent(getActivity(), SearchActivity.class);
                      intent.putExtra("key", searchKey);
                      startActivity(intent);
                  }else{
                      listerner.searchActionProcess("turn to sort");
                  }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_search:
                Log.i("main frag","search click");
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("key","");
                startActivity(intent);
                break;
        }
    }

    private void initView() {//初始化recyclerview
        adapter = new MainAdapter(getActivity());
        GridLayoutManager layoutManger = new GridLayoutManager(getActivity(), 2);
        mainRecyclerView = view.findViewById(R.id.main_rv);
        mainRecyclerView.setLayoutManager(layoutManger);
        mainRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        recipeList = new ArrayList<>();

        List<Data> dataList= HttpUtils.getRandomRecipe(18);
        Log.e("main introduce", "setData" );
        List<Recipe> temp= DataTransfer.DataToRecipe(dataList);
        for(int i=0;i<2;i++) recipeList.add(null);
        for (int i = 2; i <temp.size()+2 ; i++)
            recipeList.add(temp.get(i-2));
        adapter.setList(recipeList);
    }

}