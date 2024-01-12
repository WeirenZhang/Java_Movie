package com.weiren.zhang.movie_java.activity.movieinfomain;

import static com.weiren.zhang.library_common.ext.GsonExt.toJson;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.weiren.zhang.library_base.activity.BaseBindVMActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_base.viewmodel.BaseViewModel;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.library_common.storage.MmkvHelper;
import com.weiren.zhang.movie_java.databinding.MovieInfoMainActivityBinding;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

import java.util.List;

@Route(path = RouterActivityPath.MovieInfoMain.PATH_MovieInfoMain_HOME)
public class MovieInfoMainActivity extends BaseBindVMActivity<MovieInfoMainActivityBinding, BaseViewModel> implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Autowired(name = BaseConstant.Movie_ID_KEY)
    public String movieModelJSON = "";

    private MovieListModel movieListModel;

    private int mCurrentPage = -1;

    public static final int FRAGMENT_MovieInfo = 1;
    public static final int FRAGMENT_StoreInfo = 2;
    public static final int FRAGMENT_MovieTimeTabs = 3;
    public static final int FRAGMENT_Video = 4;

    private FragmentManager mFragmentManager;
    private SparseArray<Fragment> mFragmentMap;

    private Fragment mMovieInfoFragment, mStoreInfoFragment, mMovieTimeTabsFragment, mVideoFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.movie_info_main_activity;
    }

    @Override
    protected void initView() {
        super.initView();

        ARouter.getInstance().inject(this);
        movieListModel = GsonExt.fromJson(movieModelJSON, MovieListModel.class);
        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);
        //set actionbar title
        actionbar.setTitle(movieListModel.getTitle());

        mFragmentManager = getSupportFragmentManager();
        mFragmentMap = new SparseArray<>();
        pageTo(FRAGMENT_MovieInfo);
        mViewDataBinding.mBottomNavigationView.setOnNavigationItemSelectedListener(this);
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
            System.out.println(movieListModel.getTitle());
            List<MovieListModel> mProjTabList = MmkvHelper.getInstance().getTList(BaseConstant.Movie_RECORD, MovieListModel.class);
            if (null != mProjTabList) {
                System.out.println(BaseConstant.Movie_RECORD + " size: " + mProjTabList.size());
                boolean check = false;
                if (mProjTabList.size() > 0) {
                    for (int i = 0; i < mProjTabList.size(); i++) {
                        if (TextUtils.equals(mProjTabList.get(i).getId(), movieListModel.getId())) {
                            check = true;
                            break;
                        }
                    }
                }
                if (!check || mProjTabList.size() == 0) {
                    mProjTabList.add(movieListModel);
                    MmkvHelper.getInstance().saveList(BaseConstant.Movie_RECORD, mProjTabList);
                    ToastUtils.showShort(movieListModel.getTitle() + " 加入中");
                    System.out.println(movieListModel.getTitle() + " 加入中");
                } else {
                    ToastUtils.showShort(movieListModel.getTitle() + " 已加入我的最愛");
                    System.out.println(movieListModel.getTitle() + " 已加入我的最愛");
                }
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_daily) {
            if (mCurrentPage != FRAGMENT_MovieInfo) {
                pageTo(FRAGMENT_MovieInfo);
            }
            return true;
        } else if (itemId == R.id.item_discover) {
            if (mCurrentPage != FRAGMENT_StoreInfo) {
                pageTo(FRAGMENT_StoreInfo);
            }
            return true;
        } else if (itemId == R.id.item_hot) {
            if (mCurrentPage != FRAGMENT_MovieTimeTabs) {
                pageTo(FRAGMENT_MovieTimeTabs);
            }
            return true;
        } else if (itemId == R.id.item_person) {
            if (mCurrentPage != FRAGMENT_Video) {
                pageTo(FRAGMENT_Video);
            }
            return true;
        }
        return false;
    }

    /**
     * 滚动到指定fragment
     */
    public void pageTo(int pageIndex) {
        mCurrentPage = pageIndex;
        if (mCurrentPage == FRAGMENT_MovieInfo) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_daily);
        } else if (mCurrentPage == FRAGMENT_StoreInfo) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_discover);
        } else if (mCurrentPage == FRAGMENT_MovieTimeTabs) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_hot);
        } else if (mCurrentPage == FRAGMENT_Video) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_person);
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        showFragment(pageIndex, transaction, mFragmentMap.get(pageIndex));
    }

    /**
     * 隐藏
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mMovieInfoFragment != null) {
            transaction.hide(mMovieInfoFragment);
        }

        if (mStoreInfoFragment != null) {
            transaction.hide(mStoreInfoFragment);
        }

        if (mMovieTimeTabsFragment != null) {
            transaction.hide(mMovieTimeTabsFragment);
        }

        if (mVideoFragment != null) {
            transaction.hide(mVideoFragment);
        }
    }

    /**
     * 显示
     */
    private void showFragment(int index, FragmentTransaction transaction, Fragment fragment) {
        if (fragment == null) {
            // 当传入的fragment没有被初始化
            if (index == FRAGMENT_MovieInfo) {
                fragment = mMovieInfoFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_MovieInfo_HOME)
                        .withString(BaseConstant.Movie_ID_KEY, toJson(movieListModel)).navigation();
            } else if (index == FRAGMENT_StoreInfo) {
                fragment = mStoreInfoFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_StoreInfo_HOME).withString(BaseConstant.Movie_ID_KEY, toJson(movieListModel)).navigation();
            } else if (index == FRAGMENT_MovieTimeTabs) {
                fragment = mMovieTimeTabsFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_MovieTimeTabs_HOME).withString(BaseConstant.Movie_ID_KEY, toJson(movieListModel)).navigation();
            } else {
                fragment = mVideoFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MovieInfoMain.PATH_Video_HOME).withString(BaseConstant.Movie_ID_KEY, toJson(movieListModel)).navigation();
            }
            transaction.add(R.id.mContentFL, fragment, fragment.getClass().getSimpleName());
            // 缓存住已经初始化的fragment，以便点击tab时传入到此方法中。
            mFragmentMap.put(index, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
    }
}
