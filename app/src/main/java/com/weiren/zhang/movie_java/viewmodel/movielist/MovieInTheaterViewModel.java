package com.weiren.zhang.movie_java.viewmodel.movielist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.weiren.zhang.library_base.viewmodel.BaseViewModel;
import com.weiren.zhang.library_network.https.RetrofitHelper;
import com.weiren.zhang.library_network.observer.NetCallback;
import com.weiren.zhang.library_network.observer.NetHelperObserver;
import com.weiren.zhang.library_network.scheduler.IoMainScheduler;
import com.weiren.zhang.movie_java.api.MovieListApi;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

import java.util.List;

public class MovieInTheaterViewModel extends BaseViewModel {

    public MutableLiveData<List<MovieListModel>> mListMutable = new MutableLiveData<>();

    public MovieInTheaterViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovieComingSoonList(int page) {
        RetrofitHelper.getInstance().create(MovieListApi.class)
                .getMovieList(String.valueOf(page), "MovieList", "0")
                .compose(new IoMainScheduler<>())
                .doOnSubscribe(this)  //  请求与ViewModel周期同步
                .subscribe(new NetHelperObserver<>(new NetCallback<List<MovieListModel>>() {

                    @Override
                    public void success(List<MovieListModel> response) {
                        if (response != null) {
                            mListMutable.postValue(response);
                        } else {

                        }
                    }

                    @Override
                    public void error(String msg) {
                        mListMutable.postValue(null);
                        ToastUtils.showShort(msg);
                    }
                }));
    }
}
