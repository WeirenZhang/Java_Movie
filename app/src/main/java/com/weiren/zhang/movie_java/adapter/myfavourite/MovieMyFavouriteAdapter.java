package com.weiren.zhang.movie_java.adapter.myfavourite;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.MyfavouriteMovieListItemBinding;
import com.weiren.zhang.movie_java.model.movielist.MovieListModel;

public class MovieMyFavouriteAdapter extends BaseQuickAdapter<MovieListModel, BaseDataBindingHolder<MyfavouriteMovieListItemBinding>> {

    public MovieMyFavouriteAdapter() {
        super(R.layout.myfavourite_movie_list_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<MyfavouriteMovieListItemBinding> bindingHolder, MovieListModel movieListModel) {
        MyfavouriteMovieListItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(movieListModel);
            dataBinding.executePendingBindings();
        }
    }
}