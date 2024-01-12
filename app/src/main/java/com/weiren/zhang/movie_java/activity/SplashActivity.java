package com.weiren.zhang.movie_java.activity;

import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.weiren.zhang.library_base.activity.BaseBindActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.movie_java.databinding.ActivitySplashBinding;
import com.weiren.zhang.movie_java.R;

public class SplashActivity extends BaseBindActivity<ActivitySplashBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        mViewDataBinding.particleview.startAnim();
    }

    @Override
    protected void initData() {
        super.initData();

        new Handler().postDelayed(() -> {
            //你想要延遲執行的程式碼
            ARouter.getInstance().build(RouterActivityPath.MovieHome.PATH_Movie_HOME).navigation();
            finish();
        }, 4000);
    }
}
