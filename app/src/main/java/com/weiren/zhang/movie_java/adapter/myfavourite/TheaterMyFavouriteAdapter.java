package com.weiren.zhang.movie_java.adapter.myfavourite;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.MyfavouriteTheaterListItemBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterAreaModel;

public class TheaterMyFavouriteAdapter extends BaseQuickAdapter<TheaterAreaModel.TheaterInfoModel, BaseDataBindingHolder<MyfavouriteTheaterListItemBinding>> {

    public TheaterMyFavouriteAdapter() {
        super(R.layout.myfavourite_theater_list_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<MyfavouriteTheaterListItemBinding> bindingHolder, TheaterAreaModel.TheaterInfoModel theaterInfoModel) {
        MyfavouriteTheaterListItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(theaterInfoModel);
            dataBinding.executePendingBindings();
        }
    }
}
