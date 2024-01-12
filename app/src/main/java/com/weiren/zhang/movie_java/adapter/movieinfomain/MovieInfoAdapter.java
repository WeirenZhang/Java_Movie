package com.weiren.zhang.movie_java.adapter.movieinfomain;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.MovieInfoItemBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieInfoModel;

public class MovieInfoAdapter extends BaseQuickAdapter<MovieInfoModel, BaseDataBindingHolder<MovieInfoItemBinding>> {

    public MovieInfoAdapter() {
        super(R.layout.movie_info_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<MovieInfoItemBinding> bindingHolder, MovieInfoModel movieInfoModel) {
        MovieInfoItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(movieInfoModel);
            dataBinding.executePendingBindings();
        }
    }
}
