package com.ma33a.withyou.contract;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by khaled on 31/07/17.
 */

public interface BookFunctions {

    void downloadBook(String Url, String File_Name, ProgressBar pb, TextView tv);
    void openBook(String filePath);
}
