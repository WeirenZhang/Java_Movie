package com.weiren.zhang.movie_java.adapter.movielist;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.databinding.MovieListItemBinding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;
import com.weiren.zhang.movie_java.R;

public class MovieListAdapter extends BaseQuickAdapter<MovieListModel, BaseDataBindingHolder<MovieListItemBinding>> {

    public MovieListAdapter() {
        super(R.layout.movie_list_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<MovieListItemBinding> bindingHolder, MovieListModel movieListModel) {
        MovieListItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(movieListModel);
            dataBinding.executePendingBindings();
        }
    }
}


