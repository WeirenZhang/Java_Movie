package com.weiren.zhang.movie_java.api;

import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListApi {
    @GET("macros/s/AKfycbwwB2Ke85PFeQqt2P9BRZFOxWif6JI4_ImblPyfFlP-VTJLkJJ6sZkCMD4tPhF_g8yT/exec")
    Observable<List<MovieListModel>> getMovieList(@Query("page") String page, @Query("type") String type, @Query("tab") String tab);
}
