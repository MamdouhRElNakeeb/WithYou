package com.ma33a.withyou.network.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by khaled on 15/07/17.
 */
@Root(name="rss", strict=false)
public class RssFeed {

    @Element(name = "title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<PodcastRssFeed> podcastList;

    public List<PodcastRssFeed> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(List<PodcastRssFeed> podcastList) {
        this.podcastList = podcastList;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

}