package com.example.redfellowship.Model;

public class RowModelSearchBloodBank {
    String sbbname, sbbbloodtype,sbbunits,sbbaddress;
    int image;
    public RowModelSearchBloodBank(String sbbname,String sbbbloodtype,String sbbunits,String sbbaddress) {
        this.sbbname = sbbname;
        this.sbbbloodtype = sbbbloodtype;
        this.sbbunits=sbbunits;
        this.sbbaddress=sbbaddress;

    }

    public String getsbbname() {
        return sbbname;
    }

    public void setsbbname(String sbbname) { this.sbbname = sbbname; }

    public String getsbbbloodtype() {
        return sbbbloodtype;
    }

    public void setsbbbloodtype(String sbbbloodtype) {
        this.sbbbloodtype = sbbbloodtype;
    }

    public String getsbbunits() {
        return sbbunits;
    }

    public void setsbbunits(String sbbunits) {
        this.sbbunits = sbbunits;
    }

    public String getsbbaddress() { return sbbaddress; }

    public void setsbbaddress(String sbbaddress) { this.sbbaddress = sbbaddress; }

}

