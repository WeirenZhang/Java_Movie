package com.weiren.zhang.movie_java.fragment.movieinfomain;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weiren.zhang.library_base.fragment.BaseBindVMFragment;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.utils.CustomItemDecoration;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.movieinfomain.MovieInfoAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;
import com.weiren.zhang.movie_java.viewmodel.movieinfomain.MovieInfoViewModel;

@Route(path = RouterActivityPath.MovieInfoMain.PATH_MovieInfo_HOME)
public class MovieInfoFragment extends BaseBindVMFragment<RecyclerviewBinding, MovieInfoViewModel>
        implements OnRefreshListener {

    @Autowired(name = BaseConstant.Movie_ID_KEY)
    public String movieModelJSON = "";

    private MovieListModel movieListModel;

    private MovieInfoAdapter movieInfoAdapter;
    private boolean isLoading = true;

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();

        setLoadSir(mViewDataBinding.includeRefresh.refresh);

        ARouter.getInstance().inject(this);
        movieListModel = GsonExt.fromJson(movieModelJSON, MovieListModel.class);
        loadData();

        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewDataBinding.includeRefresh.recy.addItemDecoration(new CustomItemDecoration(getActivity(),
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, com.weiren.zhang.library_common.R.drawable.linear_split_line));
        movieInfoAdapter = new MovieInfoAdapter();
        mViewDataBinding.includeRefresh.recy.setAdapter(movieInfoAdapter);
        mViewDataBinding.includeRefresh.refresh.setOnRefreshListener(this);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        super.initData();

        mViewModel.mListMutable.observe(this, datasBeans -> {
            if (mViewDataBinding.includeRefresh.refresh.getState().isOpening) {
                mViewDataBinding.includeRefresh.refresh.finishRefresh();
            }
            if (isLoading)
                showContent();
            if (datasBeans != null && datasBeans.size() > 0) {
                movieInfoAdapter.setList(datasBeans);
            } else {
                showEmpty();
            }
            isLoading = false;
        });
    }

    private void loadData() {
        mViewModel.getMovieInfoList(movieListModel.getId());
    }

    @Override
    protected void onRetryBtnClick() {
        super.onRetryBtnClick();

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }
}

