package com.example.recipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.R;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Sort;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {
    private List<Sort> list;

    public void setList(List<Sort> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public SortAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_sort_item, parent, false);
        return new SortAdapter.ViewHolder(view);
    }

    //定义接口 OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private SortAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(SortAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;

    }

    //填充视图
    @Override
    public void onBindViewHolder(@NonNull SortAdapter.ViewHolder holder, int position) {
        holder.sort.setText(list.get(position).getSortName());

        //点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    // 这里利用回调来给RecyclerView设置点击事件
                    mOnItemClickListener.onItemClick(holder.itemView, pos);

                }
            });

        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sort;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sort = itemView.findViewById(R.id.sort);

        }
    }
}