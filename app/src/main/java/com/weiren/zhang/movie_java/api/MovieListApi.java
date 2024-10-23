package com.weiren.zhang.movie_java.api;

import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListApi {
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<MovieListModel>> getMovieList(@Query("page") String page, @Query("type") String type, @Query("tab") String tab);
}
