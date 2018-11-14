package com.by5388.xw.supportview.recycler.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.by5388.xw.supportview.R;
import com.by5388.xw.supportview.recycler.view.bean.PictureBean;

import java.util.List;

/**
 * 图片显示设配器
 * 数据来源:参考"干货 rxJava "demo
 * http://gank.io/api/data/福利/10/1
 *
 * @author by5388  on 2018/11/13.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureHolder> {
    private Context context;
    /**
     * 布局打气筒 可以由ViewGroup viewGroup.getContext() 之中获取Context,而不需要在构造方法中传入context
     */
    private LayoutInflater inflater;
    private List<PictureBean> beans;
    private PictureItemOnClickListener listener;

    public PictureAdapter(@NonNull Context context, @NonNull List<PictureBean> beans, @NonNull PictureItemOnClickListener listener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.beans = beans;
        this.listener = listener;
    }

    public void setBeans(List<PictureBean> beans) {
        this.beans = beans;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_picture, viewGroup, false);
        return new PictureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureHolder pictureHolder, int position) {
        // TODO: 2018/11/13  使用图片加载工具把图片加载到imageView之中
        final PictureBean bean = beans.get(position);
        //Fixme item的大小不合适
        Glide.with(context).load(bean.getUrl()).into(pictureHolder.imageView);
        pictureHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.saveBitmap(bean.getUrl());
            }
        });
        pictureHolder.textView.setText(bean.getDesc());
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class PictureHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        PictureHolder(View itemView) {
            super(itemView);
            // TODO: 2018/11/13
            imageView = itemView.findViewById(R.id.image_picture);
            textView = itemView.findViewById(R.id.textView_picture_title);
        }
    }
}
