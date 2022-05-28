package com.example.redfellowship.Model;

public class RowModel {
    String bbname,bbcontact,bbaddress;
    public RowModel(String bbname, String bbcontact, String bbaddress) {
        this.bbname = bbname;
        this.bbcontact = bbcontact;
        this.bbaddress = bbaddress;
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



}
