package com.telezone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallType {
    private String Id;
    private Integer type;
    private Integer CallType;
    private Integer  CallPersonDefType;
    private String fzhList;
    private String CallTime;

    public String getId() {
        return Id;
    }
    @JsonProperty("Id")
    public void setId(String id) {
        Id = id;
    }

    public Integer getType() {
        return type;
    }
    @JsonProperty("Type")
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCallType() {
        return CallType;
    }
    @JsonProperty("CallType")
    public void setCallType(Integer callType) {
        CallType = callType;
    }

    public Integer getCallPersonDefType() {
        return CallPersonDefType;
    }
    @JsonProperty("CallPersonDefType")
    public void setCallPersonDefType(Integer callPersonDefType) {
        CallPersonDefType = callPersonDefType;
    }

    public String getFzhList() {
        return fzhList;
    }
    @JsonProperty("fzhList")
    public void setFzhList(String fzhList) {
        this.fzhList = fzhList;
    }

    public String getCallTime() {
        return CallTime;
    }
    @JsonProperty("CallTime")
    public void setCallTime(String callTime) {
        CallTime = callTime;
    }
}
