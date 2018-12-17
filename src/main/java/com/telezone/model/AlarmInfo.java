package com.telezone.model;

public class AlarmInfo {

    private Integer RecordID;
    private Integer state;
    private String info;
    private String language;

    public Integer getRecordID() {
        return RecordID;
    }

    public void setRecordID(Integer recordID) {
        RecordID = recordID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
