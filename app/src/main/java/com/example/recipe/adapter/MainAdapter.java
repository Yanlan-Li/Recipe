package com.example.recipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.BR;
import com.example.recipe.detail.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.bean.Recipe;
import com.example.recipe.main.BindingClick;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<Recipe> list;
    private static final int SORT = 2;
    private static final int TEXT = 3;
    private static final int INTRODUCE = 4;
    private Context context;



    //用于设置Item的事件Presenter
    protected BindingClick ItemClick;

    public MainAdapter setItemClick(BindingClick itemClick) {
        ItemClick = itemClick;
        return this;
    }


    public MainAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
       if (position ==0) {
            return SORT;
        } else if (position ==1) {
            return TEXT;
        } else return INTRODUCE;

    }

    public void setList(List<Recipe> list) {
        Log.d("","setList");
        this.list = list;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case SORT:
                Log.d("main","分类");
                ViewDataBinding sortBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_main_sort, parent, false);
                MainAdapter.ViewHolder sortViewHolder = new MainAdapter.ViewHolder(sortBinding);
                View sort=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_sort,parent,false);
                return sortViewHolder;
            case TEXT:
                Log.d("main","s推荐标题");
                ViewDataBinding textBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_main_text, parent, false);
                MainAdapter.ViewHolder textViewHolder = new MainAdapter.ViewHolder(textBinding);
                View text=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_text,parent,false);
                return textViewHolder;
            case INTRODUCE:
                Log.d("main"," 推荐");
                ViewDataBinding introBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_main_introduce_item, parent, false);
                MainAdapter.ViewHolder introViewHolder = new MainAdapter.ViewHolder(introBinding);
                View intro=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_introduce_item,parent,false);
                introViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View v)
                    {
                           int pos = introViewHolder.getLayoutPosition();
                           Intent intent=new Intent(context, DetailActivity.class);
                           intent.putExtra("recipe",list.get(pos));
                           context.startActivity(intent);

                     }
                 });
                return introViewHolder;
            default:
                return null;
        }
    }


    //填充视图
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        if(position==0){//sort
            holder.getBinding().setVariable(BR.itemClick, ItemClick);
        } else if(position>1) {//introduce
            holder.getBinding().setVariable(BR.recipe, list.get(position));
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


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case SORT:
                            return 2;
                        case TEXT:
                            return 2;
                        case INTRODUCE:
                            return 1;
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
