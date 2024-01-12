package com.weiren.zhang.movie_java.adapter.theaterlist;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.databinding.AreaListItemBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.R;

public class TheaterAreaAdapter extends BaseQuickAdapter<TheaterAreaModel, BaseDataBindingHolder<AreaListItemBinding>> {

    public TheaterAreaAdapter() {
        super(R.layout.area_list_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<AreaListItemBinding> bindingHolder, TheaterAreaModel theaterAreaModel) {
        AreaListItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(theaterAreaModel);
            dataBinding.executePendingBindings();
        }
    }
}


