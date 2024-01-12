package com.weiren.zhang.library_base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
 * Created by zjp on 2020/4/30 17:14
 */
public abstract class BaseBindFragment<V extends ViewDataBinding>
        extends Fragment implements IBaseView {

    protected ImmersionBar mImmersionBar;

    protected V mViewDataBinding;

    protected LoadService mLoadService;

    private boolean isShowedContent = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initImmersionBar();
        mViewDataBinding =
                DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mViewDataBinding.setLifecycleOwner(this);
        if (this instanceof IEventBus)
            EventBus.getDefault().register(this);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @LayoutRes
    public abstract int getLayoutId();

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
}
