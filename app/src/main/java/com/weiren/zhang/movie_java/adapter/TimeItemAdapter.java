package com.weiren.zhang.movie_java.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.TimeItemBinding;
import com.weiren.zhang.movie_java.model.TimeModel;

public class TimeItemAdapter extends BaseQuickAdapter<TimeModel, BaseDataBindingHolder<TimeItemBinding>> {

    public TimeItemAdapter() {
        super(R.layout.time_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<TimeItemBinding> bindingHolder, TimeModel timeModel) {
        TimeItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(timeModel);
            dataBinding.executePendingBindings();
        }
    }
}