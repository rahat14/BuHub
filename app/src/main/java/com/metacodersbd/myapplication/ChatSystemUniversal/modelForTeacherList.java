package com.metacodersbd.myapplication.ChatSystemUniversal;

public class modelForTeacherList {

    String name , dept , pplink , id , notificationID   ;

    public modelForTeacherList() {
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public modelForTeacherList(String name, String dept, String pplink, String id, String notificationID) {
        this.name = name;
        this.dept = dept;
        this.pplink = pplink;
        this.id = id;
        this.notificationID = notificationID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPplink() {
        return pplink;
    }

    public void setPplink(String pplink) {
        this.pplink = pplink;
    }
}
