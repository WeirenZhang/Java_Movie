package com.weiren.zhang.movie_java.adapter.theaterlist;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.adapter.TypesItemAdapter;
import com.weiren.zhang.movie_java.databinding.TheaterResultItemBinding;
import com.weiren.zhang.movie_java.model.theaterlist.TheaterDateItemModel;

public class TheaterResultAdapter extends BaseQuickAdapter<TheaterDateItemModel.TheaterResultModel, BaseDataBindingHolder<TheaterResultItemBinding>> {

    public TheaterResultAdapter() {
        super(R.layout.theater_result_item);
    }

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<TheaterResultItemBinding> bindingHolder, TheaterDateItemModel.TheaterResultModel theaterResultModel) {
        TheaterResultItemBinding binding = DataBindingUtil.getBinding(bindingHolder.itemView);
        if (binding.types != null) {
            binding.setModel(theaterResultModel);
            binding.executePendingBindings();

            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    binding.types.getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            layoutManager.setInitialPrefetchItemCount(theaterResultModel.getTypes().size());

            TypesItemAdapter typesItemAdapter = new TypesItemAdapter(2);
            typesItemAdapter.setList(theaterResultModel.getTypes());

            binding.types.setLayoutManager(layoutManager);
            binding.types.setAdapter(typesItemAdapter);
            binding.types.setRecycledViewPool(viewPool);
        }
    }
}
