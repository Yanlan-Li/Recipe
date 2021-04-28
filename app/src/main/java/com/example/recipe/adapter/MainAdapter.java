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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.DetailActivity;
import com.example.recipe.R;
import com.example.recipe.bean.Recipe;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter  extends RecyclerView.Adapter{
    private List<Recipe> list;
    private LayoutInflater inflater;
   // private static final int TOP = 0;
    //private static final int SEARCH = 1;
    private static final int SORT = 2;
    private static final int TEXT = 3;
    private static final int INTRODUCE = 4;
    private Context context;

    public MainAdapter(Context context){
        inflater = LayoutInflater.from(context);
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
        this.list = list;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case SORT:
                View sort= inflater.inflate(R.layout.layout_main_sort, parent, false);
                return new sortHolder(sort,mOnItemClickListener);
            case TEXT:
                View text = inflater.inflate(R.layout.layout_main_text, parent, false);
                return new textHolder(text);
            case INTRODUCE:
                View introduce = inflater.inflate(R.layout.layout_main_introduce_item, parent, false);
                return new introduceHolder(introduce);
            default:
                return null;
        }
    }

    //定义接口 OnItemClickListener
    public interface OnItemClickListener {
        void onSortClick(View view,int position);
    }

    private MainAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(MainAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    //填充视图
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if (holder instanceof sortHolder) {
        } else if (holder instanceof textHolder) {
        } else if (holder instanceof introduceHolder) {
             setIntroduce((introduceHolder)holder,position);
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



    public class sortHolder extends RecyclerView.ViewHolder {
        //private GridLayout layout;
        private LinearLayout jiaChang;
        private LinearLayout hongPei;
        private LinearLayout suCai;
        private LinearLayout liangCai;
        private LinearLayout xiaFan;
        private LinearLayout chuanCai;
        private LinearLayout mianShi;
        private LinearLayout tang;
        private LinearLayout xiCan;
        private LinearLayout allSort;
        public sortHolder(View itemView,final OnItemClickListener onClickListener) {
            super(itemView);
           // layout=itemView.findViewById(R.id.main_sort);
            jiaChang=itemView.findViewById(R.id.main_sort_jiachang);
            hongPei=itemView.findViewById(R.id.main_sort_hongpei);
            suCai=itemView.findViewById(R.id.main_sort_sucai);
            liangCai=itemView.findViewById(R.id.main_sort_liangcai);
            xiaFan=itemView.findViewById(R.id.main_sort_xiafan);
            chuanCai=itemView.findViewById(R.id.main_sort_chuancai);
            mianShi=itemView.findViewById(R.id.main_sort_mianshi);
            tang=itemView.findViewById(R.id.main_sort_tang);
            xiCan=itemView.findViewById(R.id.main_sort_xican);
            allSort=itemView.findViewById(R.id.main_sort_allsort);
            List<LinearLayout> list=new ArrayList<>();
            list.add(jiaChang);
            list.add(hongPei);
            list.add(suCai);
            list.add(liangCai);
            list.add(chuanCai);
            list.add(mianShi);
            list.add(tang);
            list.add(xiCan);
            list.add(allSort);
            list.add(xiaFan);
            setSortOnClick(list,mOnItemClickListener);
    }

    public void setSortOnClick(List<LinearLayout> list,final OnItemClickListener onClickListener){
            for(int i=0;i<list.size();i++){
              list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        int position = getAdapterPosition();
                        //确保position值有效
                        if (position != RecyclerView.NO_POSITION) {
                            onClickListener.onSortClick(view, position);
                        }
                    }
                }
            });
        }
       }
    }



    public class textHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        public textHolder(View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.main_text);
        }
    }

    public class introduceHolder extends RecyclerView.ViewHolder {
        private LinearLayout introduceLayout;
        private ImageView introduceImg;
        private TextView introduceName;

        public introduceHolder(View itemView) {
            super(itemView);
            introduceLayout=itemView.findViewById(R.id.main_introduce);
            introduceImg=itemView.findViewById(R.id.intorude_img);
            introduceName=itemView.findViewById(R.id.introude_name);

        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private void setIntroduce(introduceHolder holder, int position){
        holder.introduceName.setText(list.get(position).getRecipeTitle());
        setImg(holder,list.get(position).getRecipeImg());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int pos = holder.getLayoutPosition();
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("recipe",list.get(position));
                context.startActivity(intent);

            }
        });
    }

    public void setImg(introduceHolder holder, String url){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.d("TAG","main introduce handleMessage");
                        Bitmap bmp=(Bitmap)msg.obj;
                        holder.introduceImg.setImageBitmap(bmp);
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
                Log.d("TAG","main introduce thread run");
                handler.sendMessage(msg);
            }
        }).start();
    }

}
