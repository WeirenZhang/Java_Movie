package com.weiren.zhang.library_base.config;

/**
 * Created by zjp on 2020/5/9 16:36
 */
public class ModuleLifecycleReflexs {

    /**
     * 基础库
     */
    private static final String BaseInit = "com.weiren.zhang.library_base.module.CommonModuleInit";

    public static String[] initModuleNames = {BaseInit};
}
