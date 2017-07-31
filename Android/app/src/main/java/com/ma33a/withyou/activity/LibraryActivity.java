package com.ma33a.withyou.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
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

import com.ma33a.withyou.R;
import com.ma33a.withyou.adapter.LibraryAdapter;
import com.ma33a.withyou.contract.BookFunctions;
import com.ma33a.withyou.helper.AppConsts;
import com.ma33a.withyou.network.model.Book;
import com.ma33a.withyou.network.model.DownloadPodcast;
import com.ma33a.withyou.service.DownloadService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibraryActivity extends AppCompatActivity implements BookFunctions {
    LibraryAdapter libraryAdapter;
    @BindView(R.id.library_recycler)
    RecyclerView rv_library ;
    ProgressBar pb ;
    TextView tv_pb ;
    List<Book> list ;

    public static final String MESSAGE_PROGRESS = "book_progress";
    private static final int PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_activity);
        ButterKnife.bind(this);
        registerReceiver();
        initRecycler();
    }

    private void initRecycler() {
        rv_library.setLayoutManager(new LinearLayoutManager(this));
        List<Book> list=new ArrayList<>();
        list.add(new Book("Hello","120","e.epub","https://github.com/FolioReader/FolioReader-Android/blob/master/sample/src/main/assets/PhysicsSyllabus.epub?raw=true"));
        list.add(new Book("The Silver Chair.epub","130","a.epub","https://github.com/FolioReader/FolioReader-Android/blob/master/sample/src/main/assets/The%20Silver%20Chair.epub?raw=true"));
        list.add(new Book("aayesha.epub","150","c.epub","https://github.com/FolioReader/FolioReader-Android/blob/master/sample/src/main/assets/aayesha.epub?raw=true"));

        libraryAdapter=new LibraryAdapter(list,this);

        rv_library.setAdapter(libraryAdapter);
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


    @Override
    public void downloadBook(String Url, String File_Name, ProgressBar pb, TextView tv) {
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

                    Snackbar.make(findViewById(R.id.library_activity),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }


    @Override
    public void openBook(String filePath) {
       /** Log.e("Folio",filePath);
        Intent intent = new Intent(LibraryActivity.this, FolioActivity.class);
        intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE, FolioActivity.EpubSourceType.SD_CARD);
        intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, filePath);
        startActivity(intent);**/
    }
}
