package com.ma33a.withyou.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ma33a.withyou.R;
import com.ma33a.withyou.contract.BookFunctions;
import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.network.model.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khaled on 31/07/17.
 */

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {

    List<Book> list;
    BookFunctions bookFunctions ;

    public LibraryAdapter(List<Book> list, BookFunctions bookFunctions) {
        this.list = list;
        this.bookFunctions = bookFunctions;
    }

    @Override
    public LibraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.library_item,parent,false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LibraryViewHolder holder, int position) {
        String file_name = AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()) ;
        int pos = holder.getAdapterPosition();

        if(AppConsts.checkExist(file_name)){
            holder.btn_download.setVisibility(View.GONE);
            holder.pb.setVisibility(View.GONE);
            holder.tv_download.setVisibility(View.GONE);
        }



        holder.btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =list.get(holder.getAdapterPosition()).getFileLink() ;
                String file_name=list.get(holder.getAdapterPosition()).getFileName();
                bookFunctions.downloadBook(url,file_name,holder.pb,holder.tv_download);
            }
        });

        holder.btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_file = AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()) ;
                if(AppConsts.checkExist(current_file)){
                    bookFunctions.openBook(AppConsts.path(list.get(holder.getAdapterPosition()).getFileName()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_download)
        LinearLayout btn_download ;
        @BindView(R.id.ll_read)
        LinearLayout btn_read ;
        @BindView(R.id.tv_read)
        TextView tv_read;
        @BindView(R.id.tv_download)
        TextView tv_download ;
        @BindView(R.id.pb_download)
        ProgressBar pb ;

        public LibraryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
