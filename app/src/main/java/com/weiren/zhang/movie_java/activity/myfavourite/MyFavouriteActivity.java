package com.weiren.zhang.movie_java.activity.myfavourite;

import android.util.SparseArray;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.weiren.zhang.library_base.activity.BaseBindVMActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_base.viewmodel.BaseViewModel;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.MyfavouriteActivityBinding;

@Route(path = RouterActivityPath.MyFavourite.PATH_MyFavourite_HOME)
public class MyFavouriteActivity extends BaseBindVMActivity<MyfavouriteActivityBinding, BaseViewModel> implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int mCurrentPage = -1;

    public static final int FRAGMENT_MovieMyFavourite = 1;
    public static final int FRAGMENT_TheaterMyFavourite = 2;

    private FragmentManager mFragmentManager;
    private SparseArray<Fragment> mFragmentMap;

    private Fragment mMovieMyFavouriteFragment, mTheaterMyFavouriteFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.myfavourite_activity;
    }

    @Override
    protected void initView() {
        super.initView();

        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);
        //set actionbar title
        actionbar.setTitle("我的最愛");

        mFragmentManager = getSupportFragmentManager();
        mFragmentMap = new SparseArray<>();
        pageTo(FRAGMENT_MovieMyFavourite);
        mViewDataBinding.mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_daily) {
            if (mCurrentPage != FRAGMENT_MovieMyFavourite) {
                pageTo(FRAGMENT_MovieMyFavourite);
            }
            return true;
        } else if (itemId == R.id.item_discover) {
            if (mCurrentPage != FRAGMENT_TheaterMyFavourite) {
                pageTo(FRAGMENT_TheaterMyFavourite);
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
        if (mCurrentPage == FRAGMENT_MovieMyFavourite) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_daily);
        } else if (mCurrentPage == FRAGMENT_TheaterMyFavourite) {
            mViewDataBinding.mBottomNavigationView.setSelectedItemId(R.id.item_discover);
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        showFragment(pageIndex, transaction, mFragmentMap.get(pageIndex));
    }

    /**
     * 隐藏
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mMovieMyFavouriteFragment != null) {
            transaction.hide(mMovieMyFavouriteFragment);
        }

        if (mTheaterMyFavouriteFragment != null) {
            transaction.hide(mTheaterMyFavouriteFragment);
        }
    }

    /**
     * 显示
     */
    private void showFragment(int index, FragmentTransaction transaction, Fragment fragment) {
        if (fragment == null) {
            // 当传入的fragment没有被初始化
            if (index == FRAGMENT_MovieMyFavourite) {
                fragment = mMovieMyFavouriteFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MyFavourite.PATH_MovieMyFavourite_HOME).navigation();
            } else if (index == FRAGMENT_TheaterMyFavourite) {
                fragment = mTheaterMyFavouriteFragment = (Fragment) ARouter.getInstance().build(RouterActivityPath.MyFavourite.PATH_TheaterMyFavourite_HOME).navigation();
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