package com.metacodersbd.myapplication.RoutineActivity;

public class modelForRoutine {

    String examID , classRoutine , examRoutine  , classid;


    public modelForRoutine() {
    }

    public modelForRoutine(String examID, String classRoutine, String examRoutine, String classid) {
        this.examID = examID;
        this.classRoutine = classRoutine;
        this.examRoutine = examRoutine;
        this.classid = classid;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getClassRoutine() {
        return classRoutine;
    }

    public void setClassRoutine(String classRoutine) {
        this.classRoutine = classRoutine;
    }

    public String getExamRoutine() {
        return examRoutine;
    }

    public void setExamRoutine(String examRoutine) {
        this.examRoutine = examRoutine;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }
}
