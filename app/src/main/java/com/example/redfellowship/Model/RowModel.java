package com.example.redfellowship.Model;

public class RowModel {
    String bbname,bbcontact,bbaddress,bblat,bblon;
    public RowModel(String bbname, String bbcontact, String bbaddress,String bblat,String bblon) {
        this.bbname = bbname;
        this.bbcontact = bbcontact;
        this.bbaddress = bbaddress;
        this.bblat=bblat;
        this.bblon=bblon;
    }

    public String getBbname() {
        return bbname;
    }

    public void setBbname(String bbname) {
        this.bbname = bbname;
    }

    public String getBbcontact() {
        return bbcontact;
    }

    public void setBbcontact(String bbcontact) {
        this.bbcontact = bbcontact;
    }

    public String getBbaddress() {
        return bbaddress;
    }

    public void setBbaddress(String bbaddress) {
        this.bbaddress = bbaddress;
    }

    public String getBblat() {
        return bblat;
    }

    public void setBblat(String bblat) {
        this.bblat = bblat;
    }

    public String getBblon() {
        return bblon;
    }

    public void setBblon(String bblon) {
        this.bbaddress = bblon;
    }



}
