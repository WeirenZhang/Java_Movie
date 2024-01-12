package com.weiren.zhang.library_base.router;

/**
 * Created by zjp on 2020/5/6 17:04
 * 类描述: 用于在组件化开发中,利用ARouter 进行Activity跳转的统一路径注册, 注册时请写好注释、标注界面功能业务
 */
public class RouterActivityPath {

    public static class MovieHome {
        public static final String PATH_Movie_HOME = "/moviehome/home";
    }

    public static class WebView {
        public static final String PATH_WebView_HOME = "/webView/webView";
    }

    public static class MovieInfoMain {
        public static final String PATH_MovieInfoMain_HOME = "/movieinfomain/movieinfomain";
        public static final String PATH_MovieInfo_HOME = "/movieinfomain/movieinfo";
        public static final String PATH_StoreInfo_HOME = "/movieinfomain/storeinfo";
        public static final String PATH_MovieTimeTabs_HOME = "/movieinfomain/movietimetabs";
        public static final String PATH_Video_HOME = "/movieinfomain/video";
    }

    public static class MovieList {
        public static final String PATH_ComingSoon_HOME = "/movielist/comingsoon";
        public static final String PATH_InTheater_HOME = "/movielist/intheater";
    }

    public static class Theater {
        public static final String PATH_Area_HOME = "/theater/area";
        public static final String PATH_TheaterList_HOME = "/theater/theaterlist";
        public static final String PATH_TheaterResult_HOME = "/theater/theaterresult";
    }

    public static class MyFavourite {
        public static final String PATH_MyFavourite_HOME = "/myfavourite/myfavourite";
        public static final String PATH_MovieMyFavourite_HOME = "/myfavourite/moviemyfavourite";
        public static final String PATH_TheaterMyFavourite_HOME = "/myfavourite/theatermyfavourite";
    }
}
