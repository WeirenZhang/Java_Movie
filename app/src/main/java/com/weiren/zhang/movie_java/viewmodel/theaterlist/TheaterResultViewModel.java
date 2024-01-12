package com.weiren.zhang.movie_java.viewmodel.theaterlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.ToastUtils;
import com.weiren.zhang.library_base.viewmodel.BaseViewModel;
import com.weiren.zhang.library_network.https.RetrofitHelper;
import com.weiren.zhang.library_network.observer.NetCallback;
import com.weiren.zhang.library_network.observer.NetHelperObserver;
import com.weiren.zhang.library_network.scheduler.IoMainScheduler;
import com.weiren.zhang.movie_java.api.TheaterListApi;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterDateItemModel;

import java.util.List;

public class TheaterResultViewModel extends BaseViewModel {

    public MutableLiveData<List<TheaterDateItemModel>> mListMutable = new MutableLiveData<>();

    public TheaterResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void getTheaterResultList(String cinema_id) {
        RetrofitHelper.getInstance().create(TheaterListApi.class)
                .getTheaterResultList(cinema_id, "TheaterResult")
                .compose(new IoMainScheduler<>())
                .doOnSubscribe(this)  //  请求与ViewModel周期同步
                .subscribe(new NetHelperObserver<>(new NetCallback<List<TheaterDateItemModel>>() {

                    @Override
                    public void success(List<TheaterDateItemModel> response) {
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
