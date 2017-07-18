package com.ma33a.withyou.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ma33a.withyou.R;
import com.ma33a.withyou.SendDownload;
import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.network.model.PodcastRssFeed;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khaled on 15/07/17.
 */

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder> {
    List<PodcastRssFeed> list;
    SendDownload sendDownload;

    public PodcastAdapter(List<PodcastRssFeed> list, SendDownload sendDownload) {
        this.list=list;
        this.sendDownload=sendDownload ;
    }

    @Override
    public PodcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item,parent,false) ;
        return new PodcastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PodcastViewHolder holder, int position) {

        String file_name = AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()) ;
        int pos = holder.getAdapterPosition();

        if(AppConsts.checkExist(file_name)){
            holder.ll_download.setVisibility(View.GONE);
            holder.pb.setVisibility(View.GONE);
            holder.tv_pb.setVisibility(View.GONE);
        }

        holder.tv_title.setText(list.get(pos).getTitle());
        holder.tv_duration.setText(list.get(pos).getDuration());

        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =list.get(holder.getAdapterPosition()).getLink() ;
                String file_name=list.get(holder.getAdapterPosition()).getFileName();
                sendDownload.downloadPodcast(url,file_name,holder.pb,holder.tv_pb);
            }
        });

        holder.ll_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_file = AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()) ;
                if(AppConsts.checkExist(current_file)){
                    sendDownload.openPodcast(AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()),holder.tv_listen);
                }
                else{
                    sendDownload.openPodcast(list.get(holder.getAdapterPosition()).getLink(),holder.tv_listen);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PodcastViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_cover)
        ImageView iv_cover ;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.ll_download)
        LinearLayout ll_download ;
        @BindView(R.id.ll_listen)
        LinearLayout ll_listen ;
        @BindView(R.id.pb_download)
        ProgressBar pb;
        @BindView(R.id.tv_pb)
        TextView tv_pb ;
        @BindView(R.id.tv_duration)
        TextView tv_duration ;
        @BindView(R.id.tv_listen)
        TextView tv_listen ;

        public PodcastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView) ;

        }
    }
}
