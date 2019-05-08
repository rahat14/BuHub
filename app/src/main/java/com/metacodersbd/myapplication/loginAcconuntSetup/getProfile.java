package com.metacodersbd.myapplication.loginAcconuntSetup;

//model class

public class getProfile {

private  String user_name , user_phn , user_image , user_dpt , user_bloodgroup , user_batch , cgpa ;


    public getProfile() {
    }

    public getProfile(String user_name, String user_phn, String user_image, String user_dpt, String user_bloodgroup, String user_batch, String cgpa) {
        this.user_name = user_name;
        this.user_phn = user_phn;
        this.user_image = user_image;
        this.user_dpt = user_dpt;
        this.user_bloodgroup = user_bloodgroup;
        this.user_batch = user_batch;
        this.cgpa = cgpa;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phn() {
        return user_phn;
    }

    public void setUser_phn(String user_phn) {
        this.user_phn = user_phn;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_dpt() {
        return user_dpt;
    }

    public void setUser_dpt(String user_dpt) {
        this.user_dpt = user_dpt;
    }

    public String getUser_bloodgroup() {
        return user_bloodgroup;
    }

    public void setUser_bloodgroup(String user_bloodgroup) {
        this.user_bloodgroup = user_bloodgroup;
    }

    public String getUser_batch() {
        return user_batch;
    }

    public void setUser_batch(String user_batch) {
        this.user_batch = user_batch;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}
