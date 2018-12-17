package com.telezone.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AreaInfo")
public class AreaInfo {

    private Integer areaid;
    @Id
    private String areaName;
    private String areaOrder;
    private String parentArea;
    private String areaType;
    private Integer maxSum;
    private Integer caiJueSum;
    private Integer stayMinute;
    private String areainfo;

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaOrder() {
        return areaOrder;
    }

    public void setAreaOrder(String areaOrder) {
        this.areaOrder = areaOrder;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public Integer getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(Integer maxSum) {
        this.maxSum = maxSum;
    }

    public Integer getCaiJueSum() {
        return caiJueSum;
    }

    public void setCaiJueSum(Integer caiJueSum) {
        this.caiJueSum = caiJueSum;
    }

    public Integer getStayMinute() {
        return stayMinute;
    }

    public void setStayMinute(Integer stayMinute) {
        this.stayMinute = stayMinute;
    }

    public String getAreainfo() {
        return areainfo;
    }

    public void setAreainfo(String areainfo) {
        this.areainfo = areainfo;
    }
}
