package com.weiren.zhang.movie_java.model.theaterlist;

import com.weiren.zhang.movie_java.model.TypesModel;

import java.util.List;

public class TheaterDateItemModel {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public List<TheaterResultModel> getData() {
        return data;
    }

    public void setData(List<TheaterResultModel> data) {
        this.data = data;
    }

    private List<TheaterResultModel> data;

    public class TheaterResultModel {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public String getRelease_foto() {
            return release_foto;
        }

        public void setRelease_foto(String release_foto) {
            this.release_foto = release_foto;
        }

        private String release_foto;

        public String getTheaterlist_name() {
            return theaterlist_name;
        }

        public void setTheaterlist_name(String theaterlist_name) {
            this.theaterlist_name = theaterlist_name;
        }

        private String theaterlist_name;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        private String en;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        private String icon;

        public List<TypesModel> getTypes() {
            return types;
        }

        public void setTypes(List<TypesModel> types) {
            this.types = types;
        }

        private List<TypesModel> types;
    }
}


