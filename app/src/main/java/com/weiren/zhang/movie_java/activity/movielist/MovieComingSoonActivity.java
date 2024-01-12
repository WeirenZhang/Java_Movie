package com.weiren.zhang.movie_java.activity.movielist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.weiren.zhang.movie_java.viewmodel.movielist.MovieComingSoonViewModel;

import java.util.List;

@Route(path = RouterActivityPath.MovieList.PATH_ComingSoon_HOME)
public class MovieComingSoonActivity extends BaseBindVMActivity<RecyclerviewBinding, MovieComingSoonViewModel>
        implements OnRefreshLoadMoreListener {

    private MovieListAdapter movieListAdapter;

    private PageInfo pageInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();

        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);
        //set actionbar title
        actionbar.setTitle("即將上映");

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
        mViewModel.getMovieComingSoonList(pageInfo.page);
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
