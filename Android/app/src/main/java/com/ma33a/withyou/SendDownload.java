package com.ma33a.withyou;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by khaled on 17/07/17.
 */

public interface SendDownload {
    void downloadPodcast(String Url, String File_Name, ProgressBar pb, TextView tv);
    void openPodcast(String filePath,TextView tv);
}
