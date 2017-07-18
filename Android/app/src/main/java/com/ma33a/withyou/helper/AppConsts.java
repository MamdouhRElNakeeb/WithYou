package com.ma33a.withyou.helper;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by mamdouhelnakeeb on 7/2/17.
 */

public class AppConsts {

    public static final String MA33A_URL = "https://ma33a.com/";
    public static final String PODCAST_URL="";
    public static final String PODCAST_DOWNLOAD_URL="" ;
    public static final String PACKAGE_NAME = "com.ma33a.withyou" ;


    public static String createPackageFolder()
    {
        String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "/Android/data/" + PACKAGE_NAME);

        if(!folder.exists())
        {
            folder.mkdir();
            Log.e("Created", "Folder Created At :" + folder.getPath().toString() ) ;
            createFolder();
        }
        else
        {
            Log.e("exist", "Folder Created At :" + folder.getPath().toString() ) ;
            createFolder() ;
        }
        return folder.getPath().toString();
    }


    public static String createFolder()
    {
        String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "/Android/data/" + PACKAGE_NAME+"/Podcast");
        if(!folder.exists())
        {
            folder.mkdir();
            Log.e("CreateFolder_function", "Folder Created At :" + folder.getPath().toString() ) ;
        }
        else
        {
            Log.e("CreateFolder_function", "Folder Created At :" + folder.getPath().toString() ) ;
        }
        return folder.getPath().toString();
    }

    public static Boolean checkExist(String filePath){

        File file = new File(filePath);

        Log.e("checkExist_Function",file.getPath().toString()) ;

        if(file.exists()) {
            Log.e("checkExist_Function","true") ;
            return true;
        }

        return false ;
    }

    public static String path(String filePath){

        File file = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/" + PACKAGE_NAME +"/Podcast/"+filePath);

        Log.e("path_function",file.getPath().toString()) ;

        return file.getPath().toString();
    }


}
