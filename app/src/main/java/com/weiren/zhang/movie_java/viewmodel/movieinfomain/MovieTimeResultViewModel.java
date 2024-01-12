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
import com.weiren.zhang.movie_java.model.movieinfomain.MovieDateTabItemModel;

import java.util.List;

public class MovieTimeResultViewModel extends BaseViewModel {

    public MutableLiveData<List<MovieDateTabItemModel>> mListMutable = new MutableLiveData<>();

    public MovieTimeResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMovieDateList(String movie_id) {
        RetrofitHelper.getInstance().create(MovieInfoMainApi.class)
                .getMovieDateList(movie_id, "MovieTime")
                .compose(new IoMainScheduler<>())
                .doOnSubscribe(this)  //  请求与ViewModel周期同步
                .subscribe(new NetHelperObserver<>(new NetCallback<List<MovieDateTabItemModel>>() {

                    @Override
                    public void success(List<MovieDateTabItemModel> response) {
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
