package com.ma33a.withyou.network.model;

import android.webkit.URLUtil;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by khaled on 15/07/17.
 */

@Root(name = "item", strict = false)
public class PodcastRssFeed {


    @Element(name = "title")
    String title ;

    @Element(name = "link")
    String link ;

    @Element(name = "duration")
    String duration ;

    @Element(name = "description")
    String description;

    public String getFileName(){
        return URLUtil.guessFileName(link, null, null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

