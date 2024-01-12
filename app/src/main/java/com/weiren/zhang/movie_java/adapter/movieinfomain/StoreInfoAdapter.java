package com.weiren.zhang.movie_java.adapter.movieinfomain;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.StoreInfoItemBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.StoreInfoModel;

public class StoreInfoAdapter extends BaseQuickAdapter<StoreInfoModel, BaseDataBindingHolder<StoreInfoItemBinding>> {

    public StoreInfoAdapter() {
        super(R.layout.store_info_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<StoreInfoItemBinding> bindingHolder, StoreInfoModel storeInfoModel) {
        StoreInfoItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(storeInfoModel);
            dataBinding.executePendingBindings();
        }
    }
}
