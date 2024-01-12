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
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;

import java.util.List;

public class TheaterAreaViewModel extends BaseViewModel {

    public MutableLiveData<List<TheaterAreaModel>> mListMutable = new MutableLiveData<>();

    public TheaterAreaViewModel(@NonNull Application application) {
        super(application);
    }

    public void getTheaterList() {
        RetrofitHelper.getInstance().create(TheaterListApi.class)
                .getTheaterList("Area")
                .compose(new IoMainScheduler<>())
                .doOnSubscribe(this)  //  请求与ViewModel周期同步
                .subscribe(new NetHelperObserver<>(new NetCallback<List<TheaterAreaModel>>() {

                    @Override
                    public void success(List<TheaterAreaModel> response) {
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
