package com.deaf.firsttalk;

import java.util.ArrayList;

public class Course_part_Pojo implements java.io.Serializable{
    public String pname;
    public String cwid;
    public ArrayList<String> read;
    public ArrayList<String> video;

    public Course_part_Pojo() {
    }

    public Course_part_Pojo(String pname, String cwid, ArrayList<String> read, ArrayList<String> video) {
        this.pname = pname;
        this.cwid = cwid;
        this.read = read;
        this.video = video;
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

    public ArrayList<String> getRead() {
        return read;
    }

    public void setRead(ArrayList<String> read) {
        this.read = read;
    }

    public ArrayList<String> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<String> video) {
        this.video = video;
    }

}