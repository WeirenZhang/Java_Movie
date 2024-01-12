package com.weiren.zhang.library_base.activity;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.weiren.zhang.library_base.event.IEventBus;
import com.weiren.zhang.library_base.interf.IBaseView;
import com.weiren.zhang.library_base.loadsir.EmptyCallback;
import com.weiren.zhang.library_base.loadsir.ErrorCallback;
import com.weiren.zhang.library_base.loadsir.LoadingCallback;
import org.greenrobot.eventbus.EventBus;
/**
 * Created by zjp on 2020/4/30 16:12
 */
public abstract class BaseBindActivity<V extends ViewDataBinding>
        extends AppCompatActivity implements IBaseView {

    protected V mViewDataBinding;

    protected ActionBar actionbar;

    protected ImmersionBar mImmersionBar;

    protected LoadService mLoadService;

    private boolean isShowedContent = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initImmersionBar();
        // actionbar
        actionbar = getSupportActionBar();
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setLifecycleOwner(this);
        initView();
        initData();
        if (this instanceof IEventBus)
            EventBus.getDefault().register(this);
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initView() {

    }

    protected void initData() {

    }

    /**
     * 注册LoadSir
     *
     * @param view 替换视图
     */
    public void setLoadSir(View view) {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault()
                    .register(view, (Callback.OnReloadListener) v -> onRetryBtnClick());
        }
        showLoading();
    }

    @Override
    public void showContent() {
        if (null != mLoadService) {
            isShowedContent = true;
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showLoading() {
        if (null != mLoadService) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showEmpty() {
        if (null != mLoadService) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void showFailure(@Nullable String message) {
        if (null != mLoadService) {
            if (!isShowedContent) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                ToastUtils.showShort(message);
            }
        }
    }

    /**
     * 失败重试,重新加载事件
     */
    protected void onRetryBtnClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this instanceof IEventBus)
            EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
