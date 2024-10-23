package com.weiren.zhang.movie_java.api;

import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterDateItemModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheaterListApi {
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<TheaterAreaModel>> getTheaterList(@Query("type") String page);

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    Observable<List<TheaterDateItemModel>> getTheaterResultList(@Query("cinema_id") String page, @Query("type") String type);
}