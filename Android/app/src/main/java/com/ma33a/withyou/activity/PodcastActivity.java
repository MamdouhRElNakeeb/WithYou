package com.ma33a.withyou.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

    ImageView menuRightBtn;

    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podcast_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initDrawer();

        ButterKnife.bind(this) ;
        RequestPodcast();
        registerReceiver();
        initRecycler();
    }

    private void initRecycler() {
       rvPodcast.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>() ;
       podcastAdapter = new PodcastAdapter(list, this, this);

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
                    podcastAdapter=new PodcastAdapter(list, PodcastActivity.this, PodcastActivity.this);
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


    void initDrawer(){

        mDrawerLayout = (DrawerLayout) findViewById(R.id.podcast_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuRightBtn = (ImageView) findViewById(R.id.menuRightBtn);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        toolbarTitle.setText("برامج صوتية");

        Typeface face = Typeface.createFromAsset(getAssets(),
                "hacen.ttf");

        toolbarTitle.setTypeface(face);

        menuRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
                else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_msgs:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(0).setChecked(true);

                        webIntent("https://ma33a.com/messages");
                        return true;

                    case R.id.nav_profile:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        //startActivity(new Intent(Home.this, Profile.class));
                        navigationView.getMenu().getItem(1).setChecked(true);

                        webIntent("https://ma33a.com/blog/wp-admin/profile.php");
                        return true;

                    case R.id.nav_about:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(2).setChecked(true);

                        webIntent("https://ma33a.com/blog/about-us-2");

                        return true;

                    case R.id.nav_zina:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(3).setChecked(true);

                        webIntent("http://zinakamoura.com");

                        return true;

                    case R.id.nav_podcast:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(4).setChecked(true);

                        //webIntent("https://ma33a.com/podcast");
                        startActivity(new Intent(getBaseContext(), PodcastActivity.class));

                        return true;

                    case R.id.nav_articles:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(5).setChecked(true);

                        webIntent("https://ma33a.com/blog");

                        return true;

                    case R.id.nav_lib:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(6).setChecked(true);

                        webIntent("https://ma33a.com/library");
                        return true;

                    case R.id.nav_school:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(7).setChecked(true);

                        webIntent("https://ma33a.com/school");
                        return true;

                    case R.id.nav_videos:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(8).setChecked(true);

                        webIntent("https://ma33a.com/vidoes");
                        return true;

                    case R.id.nav_guide:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(9).setChecked(true);

                        webIntent("https://ma33a.com/guide");
                        return true;

                    case R.id.nav_fb:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(10).setChecked(true);

                        String fbURL;
                        PackageManager packageManager = getPackageManager();
                        try {
                            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
                            if (versionCode >= 3002850) { //newer versions of fb app
                                fbURL =  "fb://facewebmodal/f?href=https://www.facebook.com/george.dababneh";
                            } else { //older versions of fb app
                                fbURL =  "fb://page/473894799438834";
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            fbURL = "https://www.facebook.com/george.dababneh"; //normal web url
                        }

                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        facebookIntent.setData(Uri.parse(fbURL));
                        startActivity(facebookIntent);

                        return true;

                    case R.id.nav_contact:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(11).setChecked(true);

                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.setData(Uri.parse("sms:" + "+201119229100"));
                        sendIntent.putExtra("sms_body", "\n\nMa33a App");
                        startActivity(sendIntent);

                        return true;


                    case R.id.nav_download:
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                        navigationView.getMenu().getItem(12).setChecked(true);

                        webIntent("https://ma33a.com/download");

                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void webIntent(String url){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
