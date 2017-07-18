package com.ma33a.withyou.network.api;

import com.ma33a.withyou.network.model.RssFeed;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by khaled on 15/07/17.
 */

public interface  RssClient {

    @GET("rss")
    Call<RssFeed> getRssPodcast() ;

    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


}
