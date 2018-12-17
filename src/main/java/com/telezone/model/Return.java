package com.telezone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Return<T> {
    private List<T> PointInfo = new ArrayList<>();
    @JsonProperty("PointInfo")
    public List<T> getPointInfo() {
        return PointInfo;
    }

    public void setPointInfo(List<T> pointInfo) {
        PointInfo = pointInfo;
    }
}
