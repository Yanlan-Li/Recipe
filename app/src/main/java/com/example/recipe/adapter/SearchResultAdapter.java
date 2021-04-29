package com.example.recipe.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recipe.BR;
import com.example.recipe.detail.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.bean.Recipe;
import java.util.ArrayList;
import java.util.List;
//搜索页面rvAdapter
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{
    private List<Recipe> recipeList;
    private List<Object> list;
    private static final int RESULT = 0;
    private static final int EMPTY = 1;
    private Context context;

    public SearchResultAdapter(Context context){

        this.context=context;
    }

    public void setList(List<Object> list) {
        this.list = list;
        if(list.get(0) instanceof Recipe){
            recipeList=new ArrayList<>();
            for(int i=0;i<list.size();i++) recipeList.add((Recipe)list.get(i));
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        if(list.get(0) instanceof String){
            return EMPTY;
        }
        else return RESULT;

    }

    //创建ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case RESULT:
                ViewDataBinding resultBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_search_result_item, parent, false);
                ViewHolder homeViewHolder = new ViewHolder(resultBinding);
                View result=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_result_item,parent,false);
                homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int pos = homeViewHolder.getLayoutPosition();
                    Intent intent=new Intent(context, DetailActivity.class);
                    intent.putExtra("recipe",recipeList.get(pos));
                    context.startActivity(intent);

                }
              });
                return homeViewHolder;

            case EMPTY:
                ViewDataBinding emptyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_search_result_empty, parent, false);
                ViewHolder emptyViewHolder = new ViewHolder(emptyBinding);
                View empty=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_result_empty,parent,false);
                return emptyViewHolder;
            default:
                return null;
        }
    }
    //填充视图
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.recipe,list.get(position));
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
