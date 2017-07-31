package com.ma33a.withyou.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ma33a.withyou.R;
import com.ma33a.withyou.adapter.SliderRVAdapter;
import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.helper.GetJSON;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Home extends AppCompatActivity {

    LinearLayout podcastLL, guideLL, libraryLL, articlesLL, schoolLL, videosLL, registerLL, loginLL, fbLL, smsLL, emailLL;
    TextView podcastTV, guideTV, libraryTV, articlesTV, schoolTV, videosTV, registerTV, loginTV;

    ImageView menuRightBtn;

    RecyclerView sliderRV;
    ArrayList <String> slidesArr;
    SliderRVAdapter sliderRVAdapter;


    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        String s = getPackageName() ;

        Log.e("Package",s) ;

        initDrawer();

        syncSlider();

        podcastLL = (LinearLayout) findViewById(R.id.podcastLL);
        guideLL = (LinearLayout) findViewById(R.id.userGuidetLL);
        libraryLL = (LinearLayout) findViewById(R.id.libraryLL);
        articlesLL = (LinearLayout) findViewById(R.id.articlesLL);
        schoolLL = (LinearLayout) findViewById(R.id.schoolLL);
        videosLL = (LinearLayout) findViewById(R.id.videosLL);
        registerLL = (LinearLayout) findViewById(R.id.registerLL);
        loginLL = (LinearLayout) findViewById(R.id.loginLL);
        fbLL = (LinearLayout) findViewById(R.id.fbLL);
        smsLL = (LinearLayout) findViewById(R.id.smsLL);
        emailLL = (LinearLayout) findViewById(R.id.emailLL);

        podcastTV = (TextView) findViewById(R.id.podcastTV);
        guideTV = (TextView) findViewById(R.id.guideTV);
        libraryTV = (TextView) findViewById(R.id.libraryTV);
        articlesTV = (TextView) findViewById(R.id.articlesTV);
        schoolTV = (TextView) findViewById(R.id.schoolTV);
        videosTV = (TextView) findViewById(R.id.videosTV);
        registerTV = (TextView) findViewById(R.id.registerTV);
        loginTV = (TextView) findViewById(R.id.loginTV);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "hacen.ttf");

        podcastTV.setTypeface(face);
        guideTV.setTypeface(face);
        libraryTV.setTypeface(face);
        articlesTV.setTypeface(face);
        schoolTV.setTypeface(face);
        videosTV.setTypeface(face);
        registerTV.setTypeface(face);
        loginTV.setTypeface(face);

        podcastLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String url = "https://ma33a.com/podcast";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                */

                Intent intent=new Intent(Home.this,PodcastActivity.class);
                startActivity(intent);
            }
        });

        guideLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/guide";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        libraryLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           /**     String url = "https://ma33a.com/library";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i); **/

                Intent intent=new Intent(Home.this,LibraryActivity.class);
                startActivity(intent);
            }
        });

        articlesLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/blog";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        schoolLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/school";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        videosLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/videos";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        registerLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/blog/login/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        loginLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://ma33a.com/blog/login/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        fbLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            }
        });

        smsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + "+201119229100"));
                sendIntent.putExtra("sms_body", "\n\nMa33a App");
                startActivity(sendIntent);
            }
        });

        emailLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","info@ma33a.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ma33a app contact");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "\n\nSent From Ma33a Mobile Application");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

    }

    private void initSLider(){
        sliderRV = (RecyclerView) findViewById(R.id.sliderRV);
        sliderRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        sliderRVAdapter = new SliderRVAdapter(this, slidesArr);
        sliderRV.setAdapter(sliderRVAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(sliderRV);
        sliderRV.setOnFlingListener(snapHelper);

    }

    void syncSlider(){

        slidesArr = new ArrayList<>();

        GetJSON getJSON = new GetJSON();

        String url = AppConsts.MA33A_URL + "app/slides/slider.php";

        Log.d("url", url);

        getJSON.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Something went wrong

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    Log.d("response", responseStr);

                    try {

                        JSONArray slidesJArray = new JSONArray(responseStr);

                        for (int i = 0; i <slidesJArray.length(); i++){

                            String slideName = slidesJArray.get(i).toString();
                            slidesArr.add(slideName);

                        }

                        new Handler(Looper.getMainLooper()).post(new Runnable() {

                            @Override
                            public void run() {
                                initSLider();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    // Request not successful
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(Home.this, "Check network connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }


    void initDrawer(){

        mDrawerLayout = (DrawerLayout) findViewById(R.id.homeDL);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuRightBtn = (ImageView) findViewById(R.id.menuRightBtn);

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
