package com.weiren.zhang.library_base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.weiren.zhang.library_base.R;

/**
 * Created by zjp on 2020/5/15 13:35
 */
public class ErrorCallback extends Callback {

    @Override
    protected int onCreateView()
    {
        return R.layout.base_layout_error;
    }
}
