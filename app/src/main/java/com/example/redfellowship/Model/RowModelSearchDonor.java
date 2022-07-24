package com.example.redfellowship.Model;

import java.util.ArrayList;

public class RowModelSearchDonor {


    String sdonorname, sdonorbloodtype,sdage,sdlastdonation,sdonoraddress;
    int image;
    public RowModelSearchDonor(String sdonorname,String sdonorbloodtype,String sdage,String sdlastdonation, String sdonoraddress,int image) {
        this.sdonorname = sdonorname;
        this.sdonorbloodtype = sdonorbloodtype;
        this.sdage=sdage;
        this.sdlastdonation=sdlastdonation;
        this.sdonoraddress = sdonoraddress;
        this.image=image;
    }

    public String getsdname() {
        return sdonorname;
    }

    public void setsdname(String sdonorname) {
        this.sdonorname = sdonorname;
    }

    public String getsdbloodtype() {
        return sdonorbloodtype;
    }

    public void setsdbloodtype(String sdonorbloodtype) {
        this.sdonorbloodtype = sdonorbloodtype;
    }

    public String getsdage() {
        return sdage;
    }

    public void setsdage(String sdage) {
        this.sdage = sdage;
    }

    public String getsdlastdonation() {
        return sdlastdonation;
    }

    public void setsdlastdonation(String sdlastdonation) {
        this.sdlastdonation = sdlastdonation;
    }

    public String getsdaddress() { return sdonoraddress; }

    public void setsdaddress(String sdonoraddress) { this.sdonoraddress = sdonoraddress; }

    public int getsdimage() {
        return image;
    }

    public void setsdimage(int image) {
        this.image = image;
    }
}
