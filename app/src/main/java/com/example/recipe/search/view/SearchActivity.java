package com.example.recipe.search.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.recipe.R;
import com.example.recipe.databinding.ActivitySearchBinding;

public class SearchActivity  extends AppCompatActivity implements View.OnClickListener, SearchActionFragment.FragmentInteraction {
   private ActivitySearchBinding binding;
    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment last;
    private Fragment current;
    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("search","oncreate");
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_search);
        Intent intent = getIntent();
        if(!intent.getStringExtra("key").equals("")){
            key=intent.getStringExtra("key");
        }
        initEvents();
    }

    @Override
    public void searchActionProcess(String str) {
        Log.i("search","搜索框搜索");
        if (str != null) {
            key=str;
            binding.search.searchKey.setText(key);
            selectFragment(1);
        }
    }



    public void initEvents(){
        Log.i("search","init event");
        binding.search.searchKey.setOnClickListener(this);
        binding.search.searchButton.setOnClickListener(this);
        binding.search.searchBackButton.setOnClickListener(this);

        if(!key.equals("")) {
            binding.search.searchKey.setText(key);
            selectFragment(1);
        }else selectFragment(0);
    }


    @Override
    public void onClick(View view) {
        Log.i("search","onclick");
        InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }

        switch (view.getId()){
            case R.id.search_key:
                selectFragment(0);
                break;
            case R.id.search_button:
                Log.d("TAG", "点击Button ");
                selectFragment(1);
                break;
            case R.id.search_back_button:
                Log.d("last", ""+last);
                if(last==mFrag1&&mFrag1!=null){ //若上一个页面是frag1，则返回frag1
                    binding.search.searchKey.setText("");
                    selectFragment(0);
                } else  onBackPressed();//若上一个页面是mainfra，则返回主页
                break;
        }
    }

    private void selectFragment(int i) {
        Log.i("search","selectFragment");
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        last=current;//记录上一个页面
        switch (i) {
            //当选中点击的是第一页的Tab时
            case 0:
                mFrag1 = new SearchActionFragment();
                transaction.add(R.id.search_content, mFrag1);
                current=mFrag1;
                binding.search.searchKey.setCursorVisible(true);
                break;
            case 1:
                if(binding.search.searchKey.getText().toString().trim().equals("")) {//输入框为空，则无操作
                   transaction.show(current);
                } else{
                    key=binding.search.searchKey.getText().toString();
                    Log.e("TAG", "Search message="+ key);
                    mFrag2 = new SearchResultFragment();
                    Log.e("TAG", "selectFragment: no init" );
                    transaction.add(R.id.search_content, mFrag2);
                    binding.search.searchKey.setCursorVisible(false);
                     current=mFrag2;
                }
                break;

        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将两个个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
    }
    public String getKey(){ return  key;}

}