package com.weiren.zhang.movie_java.activity.movielist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiren.zhang.library_base.activity.BaseBindVMActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.bean.page.PageInfo;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.utils.CustomItemDecoration;
import com.weiren.zhang.movie_java.adapter.movielist.MovieListAdapter;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;
import com.weiren.zhang.movie_java.viewmodel.movielist.MovieListViewModel;

import java.util.List;

@Route(path = RouterActivityPath.MovieList.PATH_MovieList_HOME)
public class MovieListActivity extends BaseBindVMActivity<RecyclerviewBinding, MovieListViewModel>
        implements OnRefreshLoadMoreListener {

    private MovieListAdapter movieListAdapter;

    private PageInfo pageInfo;

    @Autowired(name = BaseConstant.Home_ID_KEY)
    public String Home_ID = "";

    private String[] Text = new String[]{
            "本周新片",
            "本期首輪",
            "本期二輪",
            "近期上映",
            "新片快報"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);
        //set actionbar title
        actionbar.setTitle(Text[Integer.parseInt(Home_ID)]);

        pageInfo = new PageInfo();
        setLoadSir(mViewDataBinding.includeRefresh.refresh);
        loadData();
        mViewDataBinding.includeRefresh.refresh.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(this));
        mViewDataBinding.includeRefresh.recy.addItemDecoration(new CustomItemDecoration(this,
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, com.weiren.zhang.library_common.R.drawable.linear_split_line));
        mViewDataBinding.includeRefresh.recy.setAdapter(movieListAdapter = new MovieListAdapter());

        movieListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_MovieInfoMain_HOME)
                    .withString(BaseConstant.Movie_ID_KEY, GsonExt.toJson(adapter.getData().get(position)))
                    .navigation();
        });

        mViewDataBinding.includeRefresh.refresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mViewModel.mListMutable.observe(this, movieListEntity -> {
            if (mViewDataBinding.includeRefresh.refresh.getState().isOpening) {
                mViewDataBinding.includeRefresh.refresh.finishRefresh();
                mViewDataBinding.includeRefresh.refresh.finishLoadMore();
            }

            List<MovieListModel> dataList = movieListEntity;

            if (pageInfo.page == 1) {
                if (dataList != null && dataList.size() > 0) {
                    showContent();
                    movieListAdapter.setList(dataList);
                    pageInfo.nextPage();
                } else {
                    showEmpty();
                }
            } else {
                if (dataList != null && dataList.size() > 0) {
                    movieListAdapter.addData(dataList);
                    pageInfo.nextPage();
                } else if (dataList != null && dataList.size() == 0) {
                    mViewDataBinding.includeRefresh.refresh.finishLoadMoreWithNoMoreData();
                } else {
                    showContent();
                }
            }
        });
    }

    private void loadData() {
        mViewModel.getMovieList(pageInfo.page, Home_ID);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageInfo.reset();
        loadData();
    }
}
