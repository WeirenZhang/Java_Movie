package com.weiren.zhang.movie_java;

import com.weiren.zhang.library_base.application.BaseApplication;
import com.weiren.zhang.library_base.config.ModuleLifecycleConfig;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //setDebug(BuildConfig.DEBUG);
        // 初始化需要初始化的组件
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}