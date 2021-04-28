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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.R;
import com.example.recipe.bean.Material;
import com.example.recipe.bean.Recipe;
import com.example.recipe.bean.Step;
import com.example.recipe.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter  extends RecyclerView.Adapter{
    List<Object> list;
    private int stepN;
    private int materialN;
    private String recipeTitle;
    private String recipeImg;
    private LayoutInflater inflater;
    private static final int TOP = 0;
    private static final int TEXT1 = 1;
    private static final int MATERIAL= 2;
    private static final int TEXT2 = 3;
    private static final int STEP = 4;

    public DetailAdapter(Context context){
        inflater = LayoutInflater.from(context);
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
        recipeTitle=(String)list.get(0);
        recipeImg=(String)list.get(1);
        this.materialN=materialN;
        this.stepN=stepN;
        notifyDataSetChanged();
    }

    //创建ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TOP:
                View top= inflater.inflate(R.layout.layout_detail_top, parent, false);
                return new topHolder(top);
            case TEXT1:
                View text1 = inflater.inflate(R.layout.layout_detail_text1, parent, false);
                return new text1Holder(text1);
            case MATERIAL:
                View material= inflater.inflate(R.layout.layout_detail_material, parent, false);
                return new materialHolder(material);
            case TEXT2:
                View text2 = inflater.inflate(R.layout.layout_detail_text2, parent, false);
                return new text2Holder(text2);
            case STEP:
                View step = inflater.inflate(R.layout.layout_detail_step, parent, false);
                return new stepHolder(step);
            default:
                return null;
        }
    }


    //填充视图
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof topHolder) {
            setTop(((topHolder)holder));
        } else if (holder instanceof text1Holder) {
        } else if (holder instanceof materialHolder) {
            setMaterial((materialHolder)holder,position);
        } else if (holder instanceof text2Holder) {
            setText2((text2Holder) holder);
        } else if (holder instanceof stepHolder) {
            setStep((stepHolder)holder,position);
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
                   return 1;
                }
            });
        }
    }

    public class topHolder extends RecyclerView.ViewHolder {

        private ImageView detailImg;
        private TextView detailTitle;
        topHolder(View itemView) {
            super(itemView);
            detailImg=itemView.findViewById(R.id.detail_img);
            detailTitle=itemView.findViewById(R.id.detail_name);
        }
    }

    public class text1Holder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        public text1Holder(View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.detail_text1);
        }
    }

    public class materialHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView materialName;

        public materialHolder(View itemView) {
            super(itemView);
             layout=itemView.findViewById(R.id.detail_material);
            materialName=itemView.findViewById(R.id.detail_material_name);

        }
    }

    public class text2Holder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView stepsNumber;
        public text2Holder(View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.detail_text2);
            stepsNumber=itemView.findViewById(R.id.detail_step_number);
        }
    }

    public class stepHolder extends RecyclerView.ViewHolder {

        private TextView detailDescription;

        public stepHolder(View itemView) {
            super(itemView);

           detailDescription=itemView.findViewById(R.id.detail_step_description);

        }
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return list.size();
    }

    private void setText2(text2Holder holder){
        holder.stepsNumber.setText("(共"+stepN+"步)");
    }

    private void setStep(stepHolder holder, int position){
        //holder.detailImg.setImageResource(stepList.get(position).getStepImg());
       holder.detailDescription.setText(((Step)list.get(position)).getStepCount()+"."+
               ((Step)list.get(position)).getStepDescription());
    }

    private void setMaterial(materialHolder holder, int position){
        holder.materialName.setText(((Material)list.get(position)).getMaterialName());
    }

    private void setTop(topHolder holder){
        holder.detailTitle.setText(recipeTitle);
        setImg(holder,recipeImg);
    }

    public void setImg(@NonNull topHolder  holder,String url){
        @SuppressLint("HandlerLeak")Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.d("TAG","detail handleMessage");
                        Bitmap bmp=(Bitmap)msg.obj;
                        holder.detailImg.setImageBitmap(bmp);
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
                Log.d("TAG","detail thread run");
                handler.sendMessage(msg);
            }
        }).start();
    }

}
