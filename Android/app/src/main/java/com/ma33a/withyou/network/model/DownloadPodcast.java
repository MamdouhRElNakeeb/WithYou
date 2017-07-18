package com.ma33a.withyou.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khaled on 16/07/17.
 */
public class DownloadPodcast implements Parcelable{

    public DownloadPodcast(){

    }

    private int progress;
    private int currentFileSize;
    private int totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(progress);
        dest.writeInt(currentFileSize);
        dest.writeInt(totalFileSize);
    }

    private DownloadPodcast(Parcel in) {

        progress = in.readInt();
        currentFileSize = in.readInt();
        totalFileSize = in.readInt();
    }

    public static final Parcelable.Creator<DownloadPodcast> CREATOR = new Parcelable.Creator<DownloadPodcast>() {
        public DownloadPodcast createFromParcel(Parcel in) {
            return new DownloadPodcast(in);
        }

        public DownloadPodcast[] newArray(int size) {
            return new DownloadPodcast[size];
        }
    };
}