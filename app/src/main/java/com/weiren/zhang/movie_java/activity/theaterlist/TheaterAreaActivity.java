package com.weiren.zhang.movie_java.activity.theaterlist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weiren.zhang.library_base.activity.BaseBindVMActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.utils.CustomItemDecoration;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.theaterlist.TheaterAreaAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.viewmodel.theaterlist.TheaterAreaViewModel;

import java.util.List;

@Route(path = RouterActivityPath.Theater.PATH_Area_HOME)
public class TheaterAreaActivity extends BaseBindVMActivity<RecyclerviewBinding, TheaterAreaViewModel>
        implements OnRefreshListener {

    private TheaterAreaAdapter theaterAreaAdapter;

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
        actionbar.setTitle("地區");

        setLoadSir(mViewDataBinding.includeRefresh.refresh);
        loadData();
        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(this));
        mViewDataBinding.includeRefresh.recy.addItemDecoration(new CustomItemDecoration(this,
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, com.weiren.zhang.library_common.R.drawable.linear_split_line));
        mViewDataBinding.includeRefresh.recy.setAdapter(theaterAreaAdapter = new TheaterAreaAdapter());

        theaterAreaAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(RouterActivityPath.Theater.PATH_TheaterList_HOME)
                    .withString(BaseConstant.Area_ID_KEY, GsonExt.toJson(adapter.getData().get(position)))
                    .navigation();
        });

        mViewDataBinding.includeRefresh.refresh.setOnRefreshListener(this);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        super.initData();
        mViewModel.mListMutable.observe(this, movieListEntity -> {
            if (mViewDataBinding.includeRefresh.refresh.getState().isOpening) {
                mViewDataBinding.includeRefresh.refresh.finishRefresh();
                mViewDataBinding.includeRefresh.refresh.finishLoadMore();
            }

            List<TheaterAreaModel> dataList = movieListEntity;

            if (dataList != null && dataList.size() > 0) {
                showContent();
                theaterAreaAdapter.setList(dataList);
            } else {
                showEmpty();
            }
        });
    }

    private void loadData() {
        mViewModel.getTheaterList();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }
}
