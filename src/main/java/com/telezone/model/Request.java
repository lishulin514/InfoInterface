package com.telezone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private List<CallType> PointInfo = new ArrayList<>();

    public List<CallType> getPointInfo() {
        return PointInfo;
    }
    @JsonProperty("PointInfo")
    public void setPointInfo(List<CallType> pointInfo) {
        PointInfo = pointInfo;
    }
}
