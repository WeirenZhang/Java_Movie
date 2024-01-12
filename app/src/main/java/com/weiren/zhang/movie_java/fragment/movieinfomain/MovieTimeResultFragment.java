package com.weiren.zhang.movie_java.fragment.movieinfomain;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.weiren.zhang.library_base.fragment.BaseBindFragment;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.movieinfomain.MovieTimeResultAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieDateTabItemModel;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;

import java.util.ArrayList;

public class MovieTimeResultFragment extends BaseBindFragment<RecyclerviewBinding> {
    private MovieTimeResultAdapter mAdapter;
    private MovieDateTabItemModel.MovieTimeTabItemModel movieTimeTabItemModel;

    public static final String Movie_Time_Result_KEY = "movie_time_result_key";

    public static MovieTimeResultFragment newInstance(String Movie_Time_Result) {
        MovieTimeResultFragment fragment = new MovieTimeResultFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Movie_Time_Result_KEY, Movie_Time_Result);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewDataBinding.includeRefresh.refresh.setEnableRefresh(false);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
        mViewDataBinding.includeRefresh.recy.setAdapter(mAdapter = new MovieTimeResultAdapter());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MovieDateTabItemModel.MovieTimeTabItemModel.MovieTimeResultModel homeBean = (MovieDateTabItemModel.MovieTimeTabItemModel.MovieTimeResultModel) adapter.getData().get(position);
            TheaterAreaModel.TheaterInfoModel theaterInfoModel = new TheaterAreaModel.TheaterInfoModel(
                    homeBean.getId(),
                    homeBean.getTheater(),
                    "",
                    ""
            );
            ARouter.getInstance().build(RouterActivityPath.Theater.PATH_TheaterResult_HOME)
                    .withString(BaseConstant.Theater_ID_KEY, GsonExt.toJson(theaterInfoModel))
                    .navigation();
        });
    }

    @Override
    protected void initData() {
        mAdapter.setList(new ArrayList<>());
        String Movie_Time_Result = getArguments().getString(Movie_Time_Result_KEY);
        if (Movie_Time_Result != null) {
            movieTimeTabItemModel = GsonExt.fromJson(Movie_Time_Result, MovieDateTabItemModel.MovieTimeTabItemModel.class);
            mAdapter.setList(movieTimeTabItemModel.getData());
        }
    }
}
