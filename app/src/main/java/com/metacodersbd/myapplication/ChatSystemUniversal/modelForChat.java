package com.metacodersbd.myapplication.ChatSystemUniversal;

public class modelForChat {

    String uid , pushid , name , msg ,time  , pplink;

    public modelForChat(String uid, String pushid, String name, String msg, String time, String pplink) {
        this.uid = uid;
        this.pushid = pushid;
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.pplink = pplink;
    }

    public modelForChat() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPplink() {
        return pplink;
    }

    public void setPplink(String pplink) {
        this.pplink = pplink;
    }
}
