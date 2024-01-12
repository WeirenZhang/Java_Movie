package com.weiren.zhang.movie_java.api;

import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterDateItemModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheaterListApi {
    @GET("macros/s/AKfycbwwB2Ke85PFeQqt2P9BRZFOxWif6JI4_ImblPyfFlP-VTJLkJJ6sZkCMD4tPhF_g8yT/exec")
    Observable<List<TheaterAreaModel>> getTheaterList(@Query("type") String page);

    @GET("macros/s/AKfycbwwB2Ke85PFeQqt2P9BRZFOxWif6JI4_ImblPyfFlP-VTJLkJJ6sZkCMD4tPhF_g8yT/exec")
    Observable<List<TheaterDateItemModel>> getTheaterResultList(@Query("cinema_id") String page, @Query("type") String type);
}