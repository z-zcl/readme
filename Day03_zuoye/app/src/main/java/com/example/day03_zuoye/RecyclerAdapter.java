package com.example.day03_zuoye;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<InfoBean.DataBean.DatasBean> datas;
    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    int ONE = 1;
    int TWO = 2;

    public RecyclerAdapter(Context context, ArrayList<InfoBean.DataBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.one, null);
            return new ViewHolder1(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.two, null);
            return new ViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type=viewHolder.getItemViewType();
        InfoBean.DataBean.DatasBean bean = datas.get(i);
        RequestOptions options = new RequestOptions().circleCrop();
        if (type==ONE){
            ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
            Glide.with(context).load(datas.get(i).getEnvelopePic()).apply(options).into(viewHolder1.one_img);
            viewHolder1.one_title.setText(bean.getTitle());
        }else{
            ViewHolder2 viewHolder2= (ViewHolder2) viewHolder;
            Glide.with(context).load(datas.get(i).getEnvelopePic()).apply(options).into(viewHolder2.two_img1);
            Glide.with(context).load(datas.get(i).getEnvelopePic()).apply(options).into(viewHolder2.two_img2);
            Glide.with(context).load(datas.get(i).getEnvelopePic()).apply(options).into(viewHolder2.two_img3);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.Click(view,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ONE;
        } else {
            return TWO;
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView one_img;
        public TextView one_text;
        public TextView one_title;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.one_img = (ImageView) rootView.findViewById(R.id.one_img);
            this.one_text = (TextView) rootView.findViewById(R.id.one_text);
            this.one_title = (TextView) rootView.findViewById(R.id.one_title);
        }

    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView two_img1;
        public ImageView two_img2;
        public ImageView two_img3;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.two_img1 = (ImageView) rootView.findViewById(R.id.two_img1);
            this.two_img2 = (ImageView) rootView.findViewById(R.id.two_img2);
            this.two_img3 = (ImageView) rootView.findViewById(R.id.two_img3);
        }

    }
    interface OnClickItem{
        void Click(View view,int i);
    }
}
