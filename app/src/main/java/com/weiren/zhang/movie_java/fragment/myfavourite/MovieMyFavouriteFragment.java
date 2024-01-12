package com.weiren.zhang.movie_java.fragment.myfavourite;

import android.content.DialogInterface;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.weiren.zhang.library_base.fragment.BaseBindFragment;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.storage.MmkvHelper;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.myfavourite.MovieMyFavouriteAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

import java.util.List;

@Route(path = RouterActivityPath.MyFavourite.PATH_MovieMyFavourite_HOME)
public class MovieMyFavouriteFragment extends BaseBindFragment<RecyclerviewBinding> {

    private MovieMyFavouriteAdapter movieMyFavouriteAdapter = new MovieMyFavouriteAdapter();
    private boolean isLoading = true;
    private List<MovieListModel> mProjTabList;

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();

        setLoadSir(mViewDataBinding.includeRefresh.refresh);

        mViewDataBinding.includeRefresh.refresh.setEnableRefresh(false);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
        mViewDataBinding.includeRefresh.recy.setAdapter(movieMyFavouriteAdapter);
        movieMyFavouriteAdapter.setOnItemClickListener((adapter, view, position) -> {
            MovieListModel homeBean = (MovieListModel) adapter.getData().get(position);
            ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_MovieInfoMain_HOME)
                    .withString(BaseConstant.Movie_ID_KEY, GsonExt.toJson(homeBean))
                    .navigation();
        });
        movieMyFavouriteAdapter.addChildClickViewIds(R.id.delete);
        movieMyFavouriteAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.delete) {
                MovieListModel homeBean = (MovieListModel) adapter.getData().get(position);
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("您確定要刪除 " + homeBean.getTitle() + " 嗎?")
                        .setPositiveButton("删除", (dialog, which) -> {
                            mProjTabList.remove(homeBean);
                            MmkvHelper.getInstance().saveList(BaseConstant.Movie_RECORD, mProjTabList);
                            adapter.getData().remove(position);
                            adapter.notifyItemRemoved(position);
                            if (mProjTabList.size() == 0) {
                                showEmpty();
                            }
                        })
                        .setNeutralButton("取消", (dialog, which) -> {
                            // do nothing
                        })
                        .show();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        loadData();
    }

    private void loadData() {
        mProjTabList = MmkvHelper.getInstance().getTList(BaseConstant.Movie_RECORD, MovieListModel.class);
        if (null != mProjTabList) {
            if (isLoading)
                showContent();
            System.out.println(BaseConstant.Movie_RECORD + " size: " + mProjTabList.size());
            if (mProjTabList.size() > 0) {
                movieMyFavouriteAdapter.setList(mProjTabList);
            } else {
                showEmpty();
            }
            isLoading = false;
        }
    }

    @Override
    protected void onRetryBtnClick() {
        super.onRetryBtnClick();

    }
}


