package com.metacodersbd.myapplication.ChatSystemUniversal;

public class modelForChat {

    String uid , pushid , name , msg ;

    public modelForChat() {
    }

    public modelForChat(String uid, String pushid, String name, String msg) {
        this.uid = uid;
        this.pushid = pushid;
        this.name = name;
        this.msg = msg;
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
}
