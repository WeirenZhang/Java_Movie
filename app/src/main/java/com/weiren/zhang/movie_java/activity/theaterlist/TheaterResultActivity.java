package com.weiren.zhang.movie_java.activity.theaterlist;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.weiren.zhang.library_base.activity.BaseBindVMActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.storage.MmkvHelper;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.theaterlist.TheaterResultAdapter;
import com.weiren.zhang.movie_java.databinding.Recyclerview1Binding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterDateItemModel;
import com.weiren.zhang.movie_java.viewmodel.theaterlist.TheaterResultViewModel;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterActivityPath.Theater.PATH_TheaterResult_HOME)
public class TheaterResultActivity extends BaseBindVMActivity<Recyclerview1Binding, TheaterResultViewModel>
        implements OnRefreshListener {

    @Autowired(name = BaseConstant.Theater_ID_KEY)
    public String theaterInfoModelJSON = "";

    private TheaterAreaModel.TheaterInfoModel theaterInfoModel;

    private TheaterResultAdapter theaterResultAdapter;

    private List<TheaterDateItemModel> dataList;

    @Override
    protected int getLayoutId() {
        return R.layout.recyclerview1;
    }

    @Override
    protected void initView() {
        super.initView();

        ARouter.getInstance().inject(this);
        theaterInfoModel = GsonExt.fromJson(theaterInfoModelJSON, TheaterAreaModel.TheaterInfoModel.class);
        //set actionbar title
        actionbar.setTitle(theaterInfoModel.getName());
        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);

        setLoadSir(mViewDataBinding.includeRefresh.refresh);
        loadData();

        mViewDataBinding.includeRefresh.recy.setLayoutManager(new LinearLayoutManager(this));
        /*
        mViewDataBinding.includeRefresh.recy.addItemDecoration(new CustomItemDecoration(this,
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, com.weiren.zhang.library_common.R.drawable.linear_split_line));
        */
        mViewDataBinding.includeRefresh.recy.setAdapter(theaterResultAdapter = new TheaterResultAdapter());

        theaterResultAdapter.setOnItemClickListener((adapter, view, position) -> {
            TheaterDateItemModel.TheaterResultModel homeBean = (TheaterDateItemModel.TheaterResultModel) adapter.getData().get(position);
            MovieListModel movieListModel = new MovieListModel(
                    homeBean.getTheaterlist_name(),
                    homeBean.getEn(),
                    "",
                    homeBean.getRelease_foto(),
                    homeBean.getId()
            );
            ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_MovieInfoMain_HOME)
                    .withString(BaseConstant.Movie_ID_KEY, GsonExt.toJson(movieListModel))
                    .navigation();
        });

        mViewDataBinding.includeRefresh.refresh.setOnRefreshListener(this);
        mViewDataBinding.includeRefresh.refresh.setEnableLoadMore(false);
    }

    // method to inflate the options menu when
    // the user opens the menu for the first time
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myfavourite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.myfavourite) {
            System.out.println(theaterInfoModel.getName());
            List<TheaterAreaModel.TheaterInfoModel> mProjTabList = MmkvHelper.getInstance().getTList(BaseConstant.Theater_RECORD, TheaterAreaModel.TheaterInfoModel.class);
            if (null != mProjTabList) {
                System.out.println(BaseConstant.Theater_RECORD + " size: " + mProjTabList.size());
                boolean check = false;
                if (mProjTabList.size() > 0) {
                    for (int i = 0; i < mProjTabList.size(); i++) {
                        if (TextUtils.equals(mProjTabList.get(i).getId(), theaterInfoModel.getId())) {
                            check = true;
                            break;
                        }
                    }
                }
                if (!check || mProjTabList.size() == 0) {
                    mProjTabList.add(theaterInfoModel);
                    MmkvHelper.getInstance().saveList(BaseConstant.Theater_RECORD, mProjTabList);
                    ToastUtils.showShort(theaterInfoModel.getName() + " 加入中");
                    System.out.println(theaterInfoModel.getName() + " 加入中");
                } else {
                    ToastUtils.showShort(theaterInfoModel.getName() + " 已加入我的最愛");
                    System.out.println(theaterInfoModel.getName() + " 已加入我的最愛");
                }
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        super.initData();

        mViewModel.mListMutable.observe(this, movieListEntity -> {
            if (mViewDataBinding.includeRefresh.refresh.getState().isOpening) {
                mViewDataBinding.includeRefresh.refresh.finishRefresh();
                mViewDataBinding.includeRefresh.refresh.finishLoadMore();
            }

            dataList = movieListEntity;

            if (dataList != null && dataList.size() > 0) {
                showContent();
                initTheaterResult(dataList, 0);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("選擇日期");
                List<String> list = new ArrayList<>();
                for (TheaterDateItemModel item : dataList) {
                    list.add(item.getDate());
                }
                CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
                builder.setItems(cs, (dialog, which) -> initTheaterResult(dataList, which));
                AlertDialog dialog = builder.create();
                mViewDataBinding.date.setOnClickListener(v -> dialog.show());
            } else {
                showEmpty();
            }
        });
    }

    private void initTheaterResult(List<TheaterDateItemModel> data, int which) {
        mViewDataBinding.date.setText(data.get(which).getDate());
        theaterResultAdapter.setList(new ArrayList<>());
        theaterResultAdapter.addData(data.get(which).getData());
    }

    private void loadData() {
        mViewModel.getTheaterResultList(theaterInfoModel.getId());
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }
}
