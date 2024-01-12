package com.weiren.zhang.movie_java.adapter.movieinfomain;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.TypesItemAdapter;
import com.weiren.zhang.movie_java.databinding.MovieTimeResultItemBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.MovieDateTabItemModel;

public class MovieTimeResultAdapter extends BaseQuickAdapter<MovieDateTabItemModel.MovieTimeTabItemModel.MovieTimeResultModel, BaseDataBindingHolder<MovieTimeResultItemBinding>> {

    public MovieTimeResultAdapter() {
        super(R.layout.movie_time_result_item);
    }

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<MovieTimeResultItemBinding> bindingHolder, MovieDateTabItemModel.MovieTimeTabItemModel.MovieTimeResultModel movieTimeResultModel) {
        MovieTimeResultItemBinding binding = DataBindingUtil.getBinding(bindingHolder.itemView);
        if (binding.types != null) {
            binding.setModel(movieTimeResultModel);
            binding.executePendingBindings();

            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    binding.types.getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            layoutManager.setInitialPrefetchItemCount(movieTimeResultModel.getTypes().size());

            TypesItemAdapter typesItemAdapter = new TypesItemAdapter(3);
            typesItemAdapter.setList(movieTimeResultModel.getTypes());

            binding.types.setLayoutManager(layoutManager);
            binding.types.setAdapter(typesItemAdapter);
            binding.types.setRecycledViewPool(viewPool);
        }
    }
}
