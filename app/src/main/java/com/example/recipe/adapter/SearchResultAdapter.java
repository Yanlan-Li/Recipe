package com.example.recipe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.util.DataTransfer;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;
//搜索页面rvAdapter
public class SearchResultAdapter extends RecyclerView.Adapter{
    private List<Recipe> recipeList;
    private List<Object> list;
    private LayoutInflater inflater;
    private static final int RESULT = 0;
    private static final int EMPTY = 1;
    private Context context;

    public SearchResultAdapter(Context context){
        inflater = LayoutInflater.from(context);
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case RESULT:
                View result = inflater.inflate(R.layout.layout_search_result_item,parent,false);
                return new SearchResultAdapter.resultHolder(result);
            case EMPTY:
                View empty = inflater.inflate(R.layout.layout_search_result_empty,parent,false);
                return new SearchResultAdapter.emptyHolder(empty);
            default:
                return null;
        }
    }


    //填充视图
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchResultAdapter.resultHolder) {
            setResult((resultHolder) holder,position);
        } else if (holder instanceof SearchResultAdapter.emptyHolder) {
        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class resultHolder extends RecyclerView.ViewHolder {
        private ImageView searchImg;
        private TextView searchTitle;
        private TextView searchMaterial;

        public resultHolder(@NonNull View itemView) {
            super(itemView);
            searchImg= itemView.findViewById(R.id.search_item_img);
            searchTitle=itemView.findViewById(R.id.search_item_titile);
            searchMaterial=itemView.findViewById(R.id.search_item_material);

        }
    }

    class emptyHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        public emptyHolder(@NonNull View itemView){
            super(itemView);
            layout=itemView.findViewById(R.id.search_result_emtpy);
        }
    }

    public void setResult(resultHolder holder,int  position){
        holder.searchTitle.setText(recipeList.get(position).getRecipeTitle());
        String materials="";
        List<Material> materialList=recipeList.get(position).getRecipeMaterials();
        for(int i=0;i<materialList.size();i++) {   //还需设置显示长度
            materials += materialList.get(i).getMaterialName();
            if(i!=materialList.size()-1) materials+="、";
        }

        holder.searchMaterial.setText(materials);
        setImg(holder,recipeList.get(position).getRecipeImg());
        holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    Intent intent=new Intent(context, DetailActivity.class);
                    intent.putExtra("recipe",recipeList.get(position));
                    context.startActivity(intent);

                }
            });

        }

    public void setImg(@NonNull SearchResultAdapter.resultHolder holder,String url){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.d("TAG","search result handleMessage");
                        Bitmap bmp=(Bitmap)msg.obj;
                        holder.searchImg.setImageBitmap(bmp);
                        break;
                }
            };
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Bitmap bmp = HttpUtils.getURLimage(url);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                Log.d("TAG","search result thread run");
                handler.sendMessage(msg);
            }
        }).start();
    }

}
