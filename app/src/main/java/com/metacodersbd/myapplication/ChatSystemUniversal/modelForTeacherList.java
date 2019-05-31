package com.metacodersbd.myapplication.ChatSystemUniversal;

public class modelForTeacherList {

    String name , dept , pplink ;

    public modelForTeacherList() {
    }

    public modelForTeacherList(String name, String dept, String pplink) {
        this.name = name;
        this.dept = dept;
        this.pplink = pplink;
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
