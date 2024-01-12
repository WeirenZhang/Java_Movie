package com.weiren.zhang.movie_java.activity.theaterlist;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.reflect.TypeToken;
import com.weiren.zhang.library_base.activity.BaseBindActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.utils.CustomItemDecoration;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.theaterlist.TheaterListAdapter;
import com.weiren.zhang.movie_java.databinding.RecyclerviewBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;

@Route(path = RouterActivityPath.Theater.PATH_TheaterList_HOME)
public class TheaterListActivity extends BaseBindActivity<RecyclerviewBinding> {

    @Autowired(name = BaseConstant.Area_ID_KEY)
    public String theaterAreaModelJSON = "";

    private TheaterAreaModel theaterAreaModel;

    private TheaterListAdapter theaterListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initView() {
        super.initView();

        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);

        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(this));
        mViewDataBinding.includeRefresh.recy.addItemDecoration(new CustomItemDecoration(this,
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, com.weiren.zhang.library_common.R.drawable.linear_split_line));
        mViewDataBinding.includeRefresh.recy.setAdapter(theaterListAdapter = new TheaterListAdapter());

        theaterListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(RouterActivityPath.Theater.PATH_TheaterResult_HOME)
                    .withString(BaseConstant.Theater_ID_KEY, GsonExt.toJson(adapter.getData().get(position)))
                    .navigation();
        });

        mViewDataBinding.includeRefresh.refresh.setEnableRefresh(false);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        super.initData();
        ARouter.getInstance().inject(this);
        theaterAreaModel = GsonExt.fromJson(theaterAreaModelJSON, TheaterAreaModel.class);
        //set actionbar title
        actionbar.setTitle(theaterAreaModel.getTheater_top());
        theaterListAdapter.setList(theaterAreaModel.getTheater_list());
    }
}