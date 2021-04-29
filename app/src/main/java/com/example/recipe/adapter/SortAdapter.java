package com.example.recipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.BR;
import com.example.recipe.R;
import com.example.recipe.bean.Sort;
import com.example.recipe.search.view.SearchActivity;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {
    private List<Sort> list;
    private Context context;
    public SortAdapter(Context context){
        this.context=context;
    }

    public void setList(List<Sort> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public SortAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_sort_item, parent, false);
        SortAdapter.ViewHolder viewHolder = new SortAdapter.ViewHolder(binding);
        View result=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sort_item,parent,false);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int pos = viewHolder.getLayoutPosition();
                Intent intent=new Intent(context, SearchActivity.class);
                intent.putExtra("key",list.get(pos).getSortName());
                context.startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortAdapter.ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.sort,list.get(position));
        holder.getBinding().executePendingBindings();
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
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
}