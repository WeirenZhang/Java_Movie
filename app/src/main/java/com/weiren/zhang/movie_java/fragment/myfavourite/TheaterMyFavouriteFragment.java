package com.weiren.zhang.movie_java.fragment.myfavourite;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.weiren.zhang.library_base.fragment.BaseBindFragment;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.storage.MmkvHelper;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.myfavourite.TheaterMyFavouriteAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;

import java.util.List;

@Route(path = RouterActivityPath.MyFavourite.PATH_TheaterMyFavourite_HOME)
public class TheaterMyFavouriteFragment extends BaseBindFragment<RecyclerviewBinding> {

    private TheaterMyFavouriteAdapter theaterMyFavouriteAdapter = new TheaterMyFavouriteAdapter();
    private boolean isLoading = true;
    private List<TheaterAreaModel.TheaterInfoModel> mProjTabList;

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
        mViewDataBinding.includeRefresh.recy.setAdapter(theaterMyFavouriteAdapter);
        theaterMyFavouriteAdapter.setOnItemClickListener((adapter, view, position) -> {
            TheaterAreaModel.TheaterInfoModel homeBean = (TheaterAreaModel.TheaterInfoModel) adapter.getData().get(position);
            ARouter.getInstance().build(RouterActivityPath.Theater.PATH_TheaterResult_HOME)
                    .withString(BaseConstant.Theater_ID_KEY, GsonExt.toJson(homeBean))
                    .navigation();
        });
        theaterMyFavouriteAdapter.addChildClickViewIds(R.id.delete);
        theaterMyFavouriteAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.delete) {
                TheaterAreaModel.TheaterInfoModel homeBean = (TheaterAreaModel.TheaterInfoModel) adapter.getData().get(position);
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("您確定要刪除 " + homeBean.getName() + " 嗎?")
                        .setPositiveButton("删除", (dialog, which) -> {
                            mProjTabList.remove(homeBean);
                            MmkvHelper.getInstance().saveList(BaseConstant.Theater_RECORD, mProjTabList);
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
        mProjTabList = MmkvHelper.getInstance().getTList(BaseConstant.Theater_RECORD, TheaterAreaModel.TheaterInfoModel.class);
        if (null != mProjTabList) {
            if (isLoading)
                showContent();
            System.out.println(BaseConstant.Theater_RECORD + " size: " + mProjTabList.size());
            if (mProjTabList.size() > 0) {
                theaterMyFavouriteAdapter.setList(mProjTabList);
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

