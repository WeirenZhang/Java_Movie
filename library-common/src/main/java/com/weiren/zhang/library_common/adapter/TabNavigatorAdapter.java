package com.weiren.zhang.library_common.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.weiren.zhang.library_common.R;
import com.weiren.zhang.library_common.adapter.navigator.ScaleTransitionPagerTitleView;
import com.weiren.zhang.library_common.callback.OnTabClickListener;
import com.weiren.zhang.library_common.callback.TabSelectListener;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.List;

/**
 * Created by zjp on 2020/7/1 13:39
 */
public class TabNavigatorAdapter extends CommonNavigatorAdapter {

    private OnTabClickListener onTabClickListener;

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    private List<String> tabNameList;

    public TabNavigatorAdapter(List<String> list) {
        this.tabNameList = list;
    }

    @Override
    public int getCount() {
        return tabNameList == null ? 0 : tabNameList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        ScaleTransitionPagerTitleView scaleTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
        scaleTransitionPagerTitleView.setText(tabNameList.get(index));
        scaleTransitionPagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
        scaleTransitionPagerTitleView.setPadding(40, 0, 40, 0);
        scaleTransitionPagerTitleView.setNormalColor(context.getResources().getColor(R.color.c_666666));
        scaleTransitionPagerTitleView.setSelectedColor(context.getResources().getColor(R.color.colorPrimary));
        scaleTransitionPagerTitleView.setOnClickListener(view -> {
            if (null != onTabClickListener)
                onTabClickListener.onTabClick(view, index);
        });

        //选中结果后将字体加粗
        scaleTransitionPagerTitleView.setTabSelectListener(new TabSelectListener() {
            @Override
            public void onSelect(int index, int totalCount) {
                TextPaint paint = scaleTransitionPagerTitleView.getPaint();
                paint.setFakeBoldText(true);
            }

            @Override
            public void onDeselected(int index, int totalCount) {
                TextPaint paint = scaleTransitionPagerTitleView.getPaint();
                paint.setFakeBoldText(false);
            }
        });
        return scaleTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight((float) UIUtil.dip2px(context, 3.0));
        indicator.setLineWidth((float) UIUtil.dip2px(context, 20.0));
        indicator.setRoundRadius((float) UIUtil.dip2px(context, 3.0));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
        indicator.setColors(context.getResources().getColor(R.color.colorPrimary));
        return indicator;
    }
}
