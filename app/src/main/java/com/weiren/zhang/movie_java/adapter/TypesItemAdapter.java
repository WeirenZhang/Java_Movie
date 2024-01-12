package com.weiren.zhang.movie_java.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.TypesItemBinding;
import com.weiren.zhang.movie_java.model.TypesModel;

public class TypesItemAdapter extends BaseQuickAdapter<TypesModel, BaseDataBindingHolder<TypesItemBinding>> {

    public TypesItemAdapter(int count) {
        super(R.layout.types_item);
        Count = count;
    }

    private int Count;
    private RecyclerView.RecycledViewPool typesviewPool = new RecyclerView.RecycledViewPool();
    private RecyclerView.RecycledViewPool timesviewPool = new RecyclerView.RecycledViewPool();

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<TypesItemBinding> bindingHolder, TypesModel typesModel) {

        TypesItemBinding binding = DataBindingUtil.getBinding(bindingHolder.itemView);
        binding.setModel(typesModel);

        // Create layout manager with initial prefetch item count
        GridLayoutManager TypeslayoutManager = new GridLayoutManager(binding.types.getContext(), Count);
        GridLayoutManager TimeslayoutManager = new GridLayoutManager(binding.times.getContext(), Count);

        TypeslayoutManager.setInitialPrefetchItemCount(typesModel.getTypes().size());
        TimeslayoutManager.setInitialPrefetchItemCount(typesModel.getTimes().size());

        TypeItemAdapter typeItemAdapter = new TypeItemAdapter();
        typeItemAdapter.setList(typesModel.getTypes());

        TimeItemAdapter timeItemAdapter = new TimeItemAdapter();
        timeItemAdapter.setList(typesModel.getTimes());

        if (binding != null) {
            binding.types.setLayoutManager(TypeslayoutManager);
            binding.types.setAdapter(typeItemAdapter);
            //binding.types.setRecycledViewPool(typesviewPool);
        }

        if (binding != null) {
            binding.times.setLayoutManager(TimeslayoutManager);
            binding.times.setAdapter(timeItemAdapter);
            //binding.times.setRecycledViewPool(timesviewPool);
        }
    }
}