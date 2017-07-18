package com.ma33a.withyou.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ma33a.withyou.R;
import com.ma33a.withyou.SendDownload;
import com.ma33a.withyou.adapter.PodcastAdapter;
import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.network.ServiceGenerator;
import com.ma33a.withyou.network.api.RssClient;
import com.ma33a.withyou.network.model.DownloadPodcast;
import com.ma33a.withyou.network.model.PodcastRssFeed;
import com.ma33a.withyou.network.model.RssFeed;
import com.ma33a.withyou.service.DownloadService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PodcastActivity extends AppCompatActivity implements SendDownload {
    PodcastAdapter podcastAdapter;
    @BindView(R.id.podcast_recycler) RecyclerView rvPodcast ;
    ProgressBar pb ;
    TextView tv_pb ;
    List<PodcastRssFeed> list ;
    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;
    MediaPlayer mp ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podcast_activity);
        ButterKnife.bind(this) ;
        RequestPodcast();
        registerReceiver();
        initRecycler();
    }

    private void initRecycler() {
       rvPodcast.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>() ;
       podcastAdapter=new PodcastAdapter(list,this);

    }

    private void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MESSAGE_PROGRESS)){

                DownloadPodcast download = intent.getParcelableExtra("download");
                Log.e("Progress",String.valueOf(download.getProgress()));
                pb.setProgress(download.getProgress());
                if(download.getProgress() == 100){
                    pb.setVisibility(View.GONE);
                    tv_pb.setText("File Download Complete");
                } else {
                    tv_pb.setText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));
                }
            }
        }
    };

    private void openMp3(String file_name) {
        Log.e("file",file_name) ;



    }

    private void RequestPodcast() {

        RssClient client= ServiceGenerator.createService(RssClient.class) ;

        Call<RssFeed> call=client.getRssPodcast();

        call.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    RssFeed rss = response.body();
                    System.out.println("Channel title: " + rss.getChannelTitle());


                    list = rss.getPodcastList() ;
                    for(int i=0;i<list.size();i++){
                        Log.e("link",list.get(i).getFileName());
                    }
                    podcastAdapter=new PodcastAdapter(list,PodcastActivity.this);
                    rvPodcast.setAdapter(podcastAdapter);
                }
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                Toast.makeText(PodcastActivity.this,"Fail",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void downloadPodcast(String Url, String File_Name, ProgressBar pb, TextView tv) {
        if(!checkPermission())
            requestPermission();
        else{
            this.pb = pb ;
            this.tv_pb = tv;

            pb.setVisibility(View.VISIBLE);
            tv_pb.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this,DownloadService.class);
            intent.putExtra("File_Name",File_Name);
            intent.putExtra("File_Link",Url);
            startService(intent);
        }


    }

    @Override
    public void openPodcast(String filePath,TextView tv) {


        if(tv.getText()==getResources().getString(R.string.listen)) {
             mp = new MediaPlayer();

            try {
                Log.e("openPodcast", filePath);
                tv.setText(getResources().getString(R.string.pause));
                mp.setDataSource(filePath);
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            tv.setText(getResources().getString(R.string.listen));
            closePodcast();
        }
    }

    public void closePodcast() {
        mp.stop();
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;
        }
    }

    private void requestPermission(){

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    AppConsts.createPackageFolder();
                } else {

                    Snackbar.make(findViewById(R.id.podcast_activity),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }


}
