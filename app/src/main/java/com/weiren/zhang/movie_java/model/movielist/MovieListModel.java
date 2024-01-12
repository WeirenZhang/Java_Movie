package com.weiren.zhang.movie_java.model.movielist;

public class MovieListModel {
    public MovieListModel(String title, String en, String release_movie_time, String thumb, String id) {
        this.title = title;
        this.en = en;
        this.release_movie_time = release_movie_time;
        this.thumb = thumb;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    private String en;

    public String getRelease_movie_time() {
        return release_movie_time;
    }

    public void setRelease_movie_time(String release_movie_time) {
        this.release_movie_time = release_movie_time;
    }

    private String release_movie_time;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    private String thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
