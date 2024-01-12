package com.weiren.zhang.movie_java.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.TypeItemBinding;
import com.weiren.zhang.movie_java.model.TypeModel;

public class TypeItemAdapter extends BaseQuickAdapter<TypeModel, BaseDataBindingHolder<TypeItemBinding>> {

    public TypeItemAdapter() {
        super(R.layout.type_item);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<TypeItemBinding> bindingHolder, TypeModel typeModel) {
        TypeItemBinding dataBinding = bindingHolder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setModel(typeModel);
            dataBinding.executePendingBindings();
        }
    }
}