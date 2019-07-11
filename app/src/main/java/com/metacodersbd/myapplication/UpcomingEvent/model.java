package com.metacodersbd.myapplication.UpcomingEvent;

public class model {

    String  pushId , id , titte , desc , date , month, fee , venu  ,time  , dpt ;



    public model() {
    }

    public model(String pushId, String id, String titte, String desc, String date, String month, String fee, String venu, String time, String dpt) {
        this.pushId = pushId;
        this.id = id;
        this.titte = titte;
        this.desc = desc;
        this.date = date;
        this.month = month;
        this.fee = fee;
        this.venu = venu;
        this.time = time;
        this.dpt = dpt;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitte() {
        return titte;
    }

    public void setTitte(String titte) {
        this.titte = titte;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getVenu() {
        return venu;
    }

    public void setVenu(String venu) {
        this.venu = venu;
    }
}
