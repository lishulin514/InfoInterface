package com.telezone.model;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Device {

        private String point;
        private String fzh;
        private String name;
        private String duty;
        private String dep;
        private String wz;
        private String inTime;
        private String outTime;
        private Integer state;
        private Integer k1;
        private Integer alarm;
        private String zts;

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getFzh() {
            return fzh;
        }

        public void setFzh(String fzh) {
            this.fzh = fzh;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getDep() {
            return dep;
        }

        public void setDep(String dep) {
            this.dep = dep;
        }

        public String getWz() {
            return wz;
        }

        public void setWz(String wz) {
            this.wz = wz;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getK1() {
            return k1;
        }

        public void setK1(Integer k1) {
            this.k1 = k1;
        }

        public Integer getAlarm() {
            return alarm;
        }

        public void setAlarm(Integer alarm) {
            this.alarm = alarm;
        }

        public String getZts() {
            return zts;
        }

        public void setZts(String zts) {
            this.zts = zts;
        }
}
