package com.weiren.zhang.movie_java.model.theaterlist;

import java.util.List;

public class TheaterAreaModel {

    public String getTheater_top() {
        return theater_top;
    }

    public void setTheater_top(String theater_top) {
        this.theater_top = theater_top;
    }

    private String theater_top;

    public List<TheaterInfoModel> getTheater_list() {
        return theater_list;
    }

    public void setTheater_list(List<TheaterInfoModel> theater_list) {
        this.theater_list = theater_list;
    }

    private List<TheaterInfoModel> theater_list;

    public static class TheaterInfoModel {
        public TheaterInfoModel(String id, String name, String adds, String tel) {
            this.id = id;
            this.name = name;
            this.adds = adds;
            this.tel = tel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public String getAdds() {
            return adds;
        }

        public void setAdds(String adds) {
            this.adds = adds;
        }

        private String adds;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        private String tel;
    }
}
