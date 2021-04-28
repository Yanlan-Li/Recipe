package com.example.recipe;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.example.recipe.databinding.ActivityMainBinding;
import com.example.recipe.main.MainFragment;
import com.example.recipe.sort.SortFragment;
import com.example.recipe.user.view.UserFragment;

import static android.graphics.Color.GRAY;


//主页面 /*底部导航栏ViewPager + Fragment*/
public class MainActivity  extends FragmentActivity implements View.OnClickListener, MainFragment.FragmentInteraction {
    private ActivityMainBinding binding;
    //声明四个Tab分别对应的Fragment
    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment mFrag3;

    @Override
    public void searchActionProcess(String str) {
        if (str.equals("turn to sort")) {
            selectTab(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initClick();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }

    private void initClick() {
        //初始化四个Tab的点击事件
        binding.bottom.tab1.setOnClickListener(this);
        binding.bottom.tab2.setOnClickListener(this);
        binding.bottom.tab3.setOnClickListener(this);

    }


    //处理Tab的点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab1:
                selectTab(0);
                break;
            case R.id.tab2:
                selectTab(1);
                break;
            case R.id.tab3:
                selectTab(2);
                break;


        }

    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        resetImgs();
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            //当选中点击的是第一页的Tab时
            case 0:
                binding.bottom.tab1Img1.setImageResource(R.mipmap.main2);
                binding.bottom.tab1Txt.setTextColor(Color.parseColor("#FF8C00"));
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFrag1 == null) {
                    mFrag1 = new MainFragment();
                    transaction.add(R.id.main_content, mFrag1);
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFrag1);
                }
                break;
            case 1:
                binding.bottom.tab2Img2.setImageResource(R.mipmap.sort2);
                binding.bottom.tab2Txt.setTextColor(Color.parseColor("#FF8C00"));
                if (mFrag2 == null) {
                    mFrag2 = new SortFragment();
                    transaction.add(R.id.main_content, mFrag2);
                } else {
                    transaction.show(mFrag2);
                }
                break;
            case 2:
                binding.bottom.tab3Img3.setImageResource(R.mipmap.my2);
                binding.bottom.tab3Txt.setTextColor(Color.parseColor("#FF8C00"));
                if (mFrag3 == null) {
                    mFrag3 = new UserFragment();
                    transaction.add(R.id.main_content, mFrag3);
                } else {
                    transaction.show(mFrag3);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
        if (mFrag3 != null) {
            transaction.hide(mFrag3);
        }
    }

    private void resetImgs() {
        binding.bottom.tab1Img1.setImageResource(R.mipmap.main1);
        binding.bottom.tab1Txt.setTextColor(GRAY);
        binding.bottom.tab2Img2.setImageResource(R.mipmap.sort1);
        binding.bottom.tab2Txt.setTextColor(GRAY);
        binding.bottom.tab3Img3.setImageResource(R.mipmap.my1);
        binding.bottom.tab3Txt.setTextColor(GRAY);
    }

}