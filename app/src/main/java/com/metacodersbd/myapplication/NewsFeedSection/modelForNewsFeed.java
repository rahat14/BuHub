package com.metacodersbd.myapplication.NewsFeedSection;

public class modelForNewsFeed {
    String ntitle , nimage ,nuid , pp_link , pname, date , pushid  ,likeCount  ;

    public modelForNewsFeed() {
    }

    public modelForNewsFeed(String ntitle, String nimage, String nuid, String pp_link, String pname, String date, String pushid, String likeCount) {
        this.ntitle = ntitle;
        this.nimage = nimage;
        this.nuid = nuid;
        this.pp_link = pp_link;
        this.pname = pname;
        this.date = date;
        this.pushid = pushid;
        this.likeCount = likeCount;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNimage() {
        return nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage;
    }

    public String getNuid() {
        return nuid;
    }

    public void setNuid(String nuid) {
        this.nuid = nuid;
    }

    public String getPp_link() {
        return pp_link;
    }

    public void setPp_link(String pp_link) {
        this.pp_link = pp_link;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
