package com.weiren.zhang.movie_java.fragment.movieinfomain;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.weiren.zhang.library_base.fragment.BaseBindVMFragment;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.MovieTimeTabsFragmentBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieDateTabItemModel;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;
import com.weiren.zhang.movie_java.viewmodel.movieinfomain.MovieTimeResultViewModel;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterActivityPath.MovieInfoMain.PATH_MovieTimeTabs_HOME)
public class MovieTimeTabsFragment extends BaseBindVMFragment<MovieTimeTabsFragmentBinding, MovieTimeResultViewModel> {

    @Autowired(name = BaseConstant.Movie_ID_KEY)
    public String movieModelJSON = "";

    private MovieListModel movieListModel;

    private boolean isLoading = true;

    @Override
    public int getLayoutId() {
        return R.layout.movie_time_tabs_fragment;
    }

    @Override
    protected void initView() {
        super.initView();

        setLoadSir(mViewDataBinding.includeRefresh.refresh);

        ARouter.getInstance().inject(this);
        movieListModel = GsonExt.fromJson(movieModelJSON, MovieListModel.class);
        loadData();
    }

    @Override
    protected void initData() {
        super.initData();

        mViewModel.mListMutable.observe(this, datasBeans -> {
            if (isLoading)
                showContent();
            if (datasBeans != null && datasBeans.size() > 0) {
                initTabInfo(datasBeans, 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("選擇日期");
                List<String> list = new ArrayList<>();
                for (MovieDateTabItemModel item : datasBeans) {
                    list.add(item.getDate());
                }
                CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
                builder.setItems(cs, (dialog, which) -> initTabInfo(datasBeans, which));
                AlertDialog dialog = builder.create();
                mViewDataBinding.date.setOnClickListener(v -> dialog.show());
                mViewDataBinding.mEmptyLL.setVisibility(View.INVISIBLE);
            } else {
                showEmpty();
                //mViewDataBinding.date.setVisibility(View.INVISIBLE);
            }
            isLoading = false;
        });
    }

    private void loadData() {
        mViewModel.getMovieDateList(movieListModel.getId());
    }

    private void initTabInfo(List<MovieDateTabItemModel> tabInfo, int which) {
        List<Fragment> fragmentList = new ArrayList<>();
        mViewDataBinding.date.setText(tabInfo.get(which).getDate());
        for (MovieDateTabItemModel.MovieTimeTabItemModel data : tabInfo.get(which).getList()) {
            mViewDataBinding.mTabLayout.addTab(mViewDataBinding.mTabLayout.newTab());
            fragmentList.add(MovieTimeResultFragment.newInstance(GsonExt.toJson(data)));
        }
        mViewDataBinding.mViewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }
        });
        new TabLayoutMediator(
                mViewDataBinding.mTabLayout, mViewDataBinding.mViewPager,
                (tab, position) -> tab.setText(tabInfo.get(which).getList().get(position).getArea())
        ).attach();
    }

    @Override
    protected void onRetryBtnClick() {
        super.onRetryBtnClick();

    }
}
