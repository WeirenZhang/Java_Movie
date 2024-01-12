package com.weiren.zhang.movie_java.model.movieinfomain;

public class VideoModel {
    public VideoModel(String title, String href, String cover) {
        this.title = title;
        this.href = href;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    private String href;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    private String cover;
}
