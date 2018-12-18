package com.metacodersbd.myapplication.loginAcconuntSetup;

public class accountSetupUploadModel {

    String user_name ;
    String user_phn ;
    String user_dpt ;
    String user_batch ;
    String user_bloodgroup ;
    String user_image ;
    String cgpa ;


    public accountSetupUploadModel() {


    }

    public accountSetupUploadModel(String user_name, String user_phn, String user_dpt, String user_batch, String user_bloodgroup, String user_image , String cgpa) {
        this.user_name = user_name;
        this.user_phn = user_phn;
        this.user_dpt = user_dpt;
        this.user_batch = user_batch;
        this.user_bloodgroup = user_bloodgroup;
        this.user_image = user_image;
        this.cgpa=cgpa;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_phn() {
        return user_phn;
    }

    public String getUser_dpt() {
        return user_dpt;
    }

    public String getUser_batch() {
        return user_batch;
    }

    public String getUser_bloodgroup() {
        return user_bloodgroup;
    }

    public String getUser_image() {
        return user_image;
    }
    public  String getCgpa(){return cgpa;}
}
