package com.deaf.firsttalk;

import java.util.ArrayList;

public class CourseImgPojo implements java.io.Serializable {
    public String cid;
    public String cname;
    public String cuniversity;
    public String cimage;
    public ArrayList<String> week;
    public String winfo;
    public String wname;


    public CourseImgPojo() {

    }

    public CourseImgPojo(String cid, String cname, String cuniversity, String cimage, ArrayList<String> week, String winfo, String wname, String pname, String cwid, ArrayList<String> read, ArrayList<String> video) {
        this.cid = cid;
        this.cname = cname;
        this.cuniversity = cuniversity;
        this.cimage = cimage;
        this.week = week;
        this.winfo = winfo;
        this.wname = wname;

    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getWinfo() {
        return winfo;
    }

    public void setWinfo(String winfo) {
        this.winfo = winfo;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }


    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCuniversity() {
        return cuniversity;
    }

    public void setCuniversity(String cuniversity) {
        this.cuniversity = cuniversity;
    }

    public ArrayList<String> getWeek() {
        return week;
    }

    public void setWeek(ArrayList<String> week) {
        this.week = week;
    }
}
