package com.deaf.firsttalk;

import java.util.ArrayList;

public class Course_part_Pojo implements java.io.Serializable{
    public String pname;
    public String cwid;
    public String read;
    public String video;

    public Course_part_Pojo() {
    }

    public Course_part_Pojo(String pname, String cwid,String read, String video) {
        this.pname = pname;
        this.cwid = cwid;

    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }



}