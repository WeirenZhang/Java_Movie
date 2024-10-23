package com.weiren.zhang.movie_java.api;

import com.weiren.zhang.movie_java.model.movieinfomain.MovieDateTabItemModel;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieInfoModel;
import com.weiren.zhang.movie_java.model.movieinfomain.StoreInfoModel;
import com.weiren.zhang.movie_java.model.movieinfomain.VideoModel;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInfoMainApi {
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<MovieInfoModel>> getMovieInfoList(@Query("movie_id") String page, @Query("type") String type);

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<StoreInfoModel>> getStoreInfoList(@Query("movie_id") String page, @Query("type") String type);

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<VideoModel>> getVideoList(@Query("movie_id") String page, @Query("type") String type);

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<MovieDateTabItemModel>> getMovieDateList(@Query("movie_id") String page, @Query("type") String type);
}
