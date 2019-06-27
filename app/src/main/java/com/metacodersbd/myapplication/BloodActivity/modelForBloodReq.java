package com.metacodersbd.myapplication.BloodActivity;

public class modelForBloodReq {

    String postID , uid , needer  , loc , timee , datee , bg ,phone, purl ;

    public modelForBloodReq() {
    }

    public modelForBloodReq(String postID, String uid, String needer, String loc, String timee, String datee, String bg, String phone, String purl) {
        this.postID = postID;
        this.uid = uid;
        this.needer = needer;
        this.loc = loc;
        this.timee = timee;
        this.datee = datee;
        this.bg = bg;
        this.phone = phone;
        this.purl = purl;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNeeder() {
        return needer;
    }

    public void setNeeder(String needer) {
        this.needer = needer;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTimee() {
        return timee;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }
}
