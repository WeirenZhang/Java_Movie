package com.weiren.zhang.movie_java.adapter.movieinfomain;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.VideoItemBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.VideoModel;

public class VideoAdapter extends BaseQuickAdapter<VideoModel, BaseDataBindingHolder<VideoItemBinding>> {

    public VideoAdapter() {
        super(R.layout.video_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<VideoItemBinding> bindingHolder, VideoModel videoModel) {
        VideoItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(videoModel);
            dataBinding.executePendingBindings();
        }
    }
}

