package com.ma33a.withyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mamdouhelnakeeb on 7/2/17.
 */

public class SliderRVAdapter extends RecyclerView.Adapter<SliderRVAdapter.ViewHolder> {

    private ArrayList <String> slidesArr;
    private Context context;

    public SliderRVAdapter(Context context, ArrayList <String> slidesArr){
        this.context = context;
        this.slidesArr = slidesArr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String slideName = slidesArr.get(position);

        Picasso.with(context).load(AppConsts.MA33A_URL + "app/slides/" + slideName).placeholder(R.drawable.slider_temp).into(holder.slideIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(AppConsts.MA33A_URL + "slide" + String.valueOf(holder.getAdapterPosition() + 1)));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return slidesArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView slideIV;

        public ViewHolder(View itemView) {
            super(itemView);

            slideIV = (ImageView) itemView.findViewById(R.id.sliderIV);
        }
    }
}