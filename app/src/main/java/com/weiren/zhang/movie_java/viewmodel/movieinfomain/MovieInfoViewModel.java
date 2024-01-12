package com.weiren.zhang.movie_java.viewmodel.movieinfomain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.weiren.zhang.library_base.viewmodel.BaseViewModel;
import com.weiren.zhang.library_network.https.RetrofitHelper;
import com.weiren.zhang.library_network.observer.NetCallback;
import com.weiren.zhang.library_network.observer.NetHelperObserver;
import com.weiren.zhang.library_network.scheduler.IoMainScheduler;
import com.weiren.zhang.movie_java.api.MovieInfoMainApi;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieInfoModel;

import java.util.List;

public class MovieInfoViewModel extends BaseViewModel {

    public MutableLiveData<List<MovieInfoModel>> mListMutable = new MutableLiveData<>();

    public MovieInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovieInfoList(String movie_id) {
        RetrofitHelper.getInstance().create(MovieInfoMainApi.class)
                .getMovieInfoList(movie_id, "MovieInfo")
                .compose(new IoMainScheduler<>())
                .doOnSubscribe(this)  //  请求与ViewModel周期同步
                .subscribe(new NetHelperObserver<>(new NetCallback<List<MovieInfoModel>>() {

                    @Override
                    public void success(List<MovieInfoModel> response) {
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
