package com.weiren.zhang.movie_java.activity;

import static com.blankj.utilcode.util.GsonUtils.toJson;

import android.widget.SimpleAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.weiren.zhang.library_base.activity.BaseBindActivity;
import com.weiren.zhang.library_base.router.RouterActivityPath;
import com.weiren.zhang.library_common.constant.BaseConstant;
import com.weiren.zhang.library_common.ext.GsonExt;
import com.weiren.zhang.movie_java.R;
import com.weiren.zhang.movie_java.databinding.ActivityMainBinding;
import com.weiren.zhang.movie_java.model.movieinfomain.VideoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Route(path = RouterActivityPath.MovieHome.PATH_Movie_HOME)
public class HomeActivity extends BaseBindActivity<ActivityMainBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private int[] image = new int[]{
            R.drawable.enl_1,
            R.drawable.enl_4,
            R.drawable.enl_5,
            R.drawable.enl_3,
            R.drawable.enl_6
    };
    private String[] imgText = new String[]{
            "現正熱映",
            "即將上映",
            "電影院",
            "我的最愛",
            "網路訂票"
    };

    @Override
    protected void initView() {
        super.initView();

        //set actionbar title
        actionbar.setTitle("電影時刻表");

        ArrayList<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                items,
                R.layout.grid_item,
                new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text}
        );
        mViewDataBinding.mainPageGridview.setNumColumns(3);
        mViewDataBinding.mainPageGridview.setAdapter(adapter);
        mViewDataBinding.mainPageGridview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    ARouter.getInstance().build(RouterActivityPath.MovieList.PATH_InTheater_HOME)
                            .navigation();
                    break;
                case 1:
                    ARouter.getInstance().build(RouterActivityPath.MovieList.PATH_ComingSoon_HOME)
                            .navigation();
                    break;
                case 2:
                    ARouter.getInstance().build(RouterActivityPath.Theater.PATH_Area_HOME)
                            .navigation();
                    break;
                case 3:
                    ARouter.getInstance().build(RouterActivityPath.MyFavourite.PATH_MyFavourite_HOME)
                            .navigation();
                    break;
                case 4:
                    VideoModel videoModel = new VideoModel(
                            "網路訂票",
                            "https://www.ezding.com.tw/faq?comeFromApp=true&device=app",
                            ""
                    );
                    ARouter.getInstance().build(RouterActivityPath.WebView.PATH_WebView_HOME)
                            .withString(BaseConstant.Video_ID_KEY, GsonExt.toJson(videoModel))
                            .navigation();

                    break;
            }
        });
    }
}