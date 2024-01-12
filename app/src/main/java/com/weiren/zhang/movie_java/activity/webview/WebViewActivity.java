package com.weiren.zhang.movie_java.activity.webview;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.weiren.zhang.library_base.activity.BaseBindActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.ActivityWebviewBrowserBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.VideoModel;

@Route(path = RouterActivityPath.WebView.PATH_WebView_HOME)
public class WebViewActivity extends BaseBindActivity<ActivityWebviewBrowserBinding> {
    @Autowired(name = BaseConstant.Video_ID_KEY)
    public String videoModelJSON = "";
    private VideoModel videoModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview_browser;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        videoModel = GsonExt.fromJson(videoModelJSON, VideoModel.class);
        String loadUrl = videoModel.getHref();
        String title = videoModel.getTitle();

        // set back button
        actionbar.setDisplayHomeAsUpEnabled(true);
        //set actionbar title
        actionbar.setTitle(title);

        WebViewOption(loadUrl, title);
    }

    private void WebViewOption(String loadUrl, String title) {
        WebView webView = mViewDataBinding.pbWebView;
        webView.getSettings().setJavaScriptEnabled(true);
        //允许打开js新窗口
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyChromeWebViewClient());
        webView.loadUrl(loadUrl);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private class MyChromeWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mViewDataBinding.pbAd.setVisibility(View.GONE);
            } else {
                mViewDataBinding.pbAd.setVisibility(View.VISIBLE);
                mViewDataBinding.pbAd.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mViewDataBinding.pbWebView.canGoBack()) {
            mViewDataBinding.pbWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


