package com.weiren.zhang.movie_java.model;

import java.util.List;

public class TypesModel {
    public List<TypeModel> getTypes() {
        return types;
    }

    public void setTypes(List<TypeModel> types) {
        this.types = types;
    }

    private List<TypeModel> types;

    public List<TimeModel> getTimes() {
        return times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }

    private List<TimeModel> times;
}
