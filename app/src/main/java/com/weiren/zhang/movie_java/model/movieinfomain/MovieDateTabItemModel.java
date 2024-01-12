package com.weiren.zhang.movie_java.model.movieinfomain;

import com.weiren.zhang.movie_java.model.TypesModel;

import java.util.List;

public class MovieDateTabItemModel {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public List<MovieTimeTabItemModel> getList() {
        return list;
    }

    public void setList(List<MovieTimeTabItemModel> list) {
        this.list = list;
    }

    private List<MovieTimeTabItemModel> list;

    public class MovieTimeTabItemModel {
        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        private String area;

        public List<MovieTimeResultModel> getData() {
            return data;
        }

        public void setData(List<MovieTimeResultModel> data) {
            this.data = data;
        }

        private List<MovieTimeResultModel> data;

        public class MovieTimeResultModel {
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            private String id;

            public String getTheater() {
                return theater;
            }

            public void setTheater(String theater) {
                this.theater = theater;
            }

            private String theater;

            public List<TypesModel> getTypes() {
                return types;
            }

            public void setTypes(List<TypesModel> types) {
                this.types = types;
            }

            private List<TypesModel> types;
        }
    }
}
