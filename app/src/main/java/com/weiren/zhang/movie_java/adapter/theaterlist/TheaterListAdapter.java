package com.weiren.zhang.movie_java.adapter.theaterlist;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.databinding.TheaterListItemBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_java.R;

public class TheaterListAdapter extends BaseQuickAdapter<TheaterAreaModel.TheaterInfoModel, BaseDataBindingHolder<TheaterListItemBinding>> {

    public TheaterListAdapter() {
        super(R.layout.theater_list_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<TheaterListItemBinding> bindingHolder, TheaterAreaModel.TheaterInfoModel theaterInfoModel) {
        TheaterListItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(theaterInfoModel);
            dataBinding.executePendingBindings();
        }
    }
}


