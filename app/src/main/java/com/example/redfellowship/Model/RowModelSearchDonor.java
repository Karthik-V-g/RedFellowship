package com.example.redfellowship.Model;

public class RowModelSearchDonor {


    String sdonorname, sdonorbloodtype,sdage,sdlastdonation,sdonoraddress,sdonorlatitude,sdonorlongitutde;
    int image;
    public RowModelSearchDonor(String sdonorname,String sdonorbloodtype,String sdage,String sdlastdonation, String sdonoraddress,int image,String sdonorlatitude,String sdonorlongitutde) {
        this.sdonorname = sdonorname;
        this.sdonorbloodtype = sdonorbloodtype;
        this.sdage=sdage;
        this.sdlastdonation=sdlastdonation;
        this.sdonoraddress = sdonoraddress;
        this.image=image;
        this.sdonorlatitude=sdonorlatitude;
        this.sdonorlongitutde=sdonorlongitutde;
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

    public String getsdlatitude() { return sdonorlatitude; }

    public void setsdlatitude(String sdonorlongitutde) { this.sdonorlatitude = sdonorlatitude; }

    public String getsdlongitude() { return sdonorlongitutde; }

    public void setsdlongitude(String sdonorlongitutde) { this.sdonorlongitutde = sdonorlongitutde; }
}
