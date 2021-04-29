package com.example.recipe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.BR;
import com.example.recipe.R;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Step;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter  extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
    List<Object> list;
    private int stepN;
    private int materialN;
    private String recipeTitle;
    private String recipeImg;

    private static final int TOP = 0;
    private static final int TEXT1 = 1;
    private static final int MATERIAL= 2;
    private static final int TEXT2 = 3;
    private static final int STEP = 4;
    private Context context;
    public DetailAdapter(Context context){
        this.context=context;
    }


    @Override
    public int getItemViewType(int position) {
        if ( position == 0) {
            return TOP;
        } else if (position==1) {
            return TEXT1;
        } else if (position >=2&&position<materialN+2) {
            return MATERIAL;
        } else if (position ==materialN+2) {
            return TEXT2;
        } else return STEP;

    }

    public void setList(List<Object> list,int materialN,int stepN) {
        Log.d("TAG","Detail set adapter list");
        this.list = list;
        //recipeTitle=(Recipe)(list.get(0)).;
        //recipeImg=(String)list.get(1);
        this.materialN=materialN;
        this.stepN=stepN;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TOP:
                ViewDataBinding topBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_detail_top, parent, false);
                DetailAdapter.ViewHolder topViewHolder = new DetailAdapter.ViewHolder(topBinding);
                View sort=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail_top,parent,false);
                return topViewHolder;
            case TEXT1:
                ViewDataBinding text1Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_detail_text1, parent, false);
                DetailAdapter.ViewHolder text1ViewHolder = new DetailAdapter.ViewHolder(text1Binding);
                View text1=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail_text1,parent,false);
                return text1ViewHolder;
            case MATERIAL:
                ViewDataBinding materialBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_detail_material, parent, false);
                DetailAdapter.ViewHolder materialViewHolder = new DetailAdapter.ViewHolder(materialBinding);
                View material=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail_material,parent,false);
                return materialViewHolder;
            case TEXT2:
                ViewDataBinding text2Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_detail_text2, parent, false);
                DetailAdapter.ViewHolder text2ViewHolder = new DetailAdapter.ViewHolder(text2Binding);
                View text2=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail_text2,parent,false);
                return text2ViewHolder;
            case STEP:
                ViewDataBinding stepBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_detail_step, parent, false);
                DetailAdapter.ViewHolder stepViewHolder = new DetailAdapter.ViewHolder(stepBinding);
                View step=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail_step,parent,false);
                return stepViewHolder;
            default:
                return null;
        }
    }

    //填充视图
    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {
        if ( position == 0) {//top
            holder.getBinding().setVariable(BR.recipe,list.get(position));
        } else if (position==1) {//text1
        } else if (position >=2&&position<materialN+2) {//material
            holder.getBinding().setVariable(BR.material,list.get(position));
        } else if (position ==materialN+2) {//text2
            holder.getBinding().setVariable(BR.str,"(共"+stepN+"步)");
        }else{
            holder.getBinding().setVariable(BR.step,list.get(position));
        }
        holder.getBinding().executePendingBindings();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list.size();
    }

}
