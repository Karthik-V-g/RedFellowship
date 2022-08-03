package com.example.redfellowship.utils;

import android.app.Application;

public class CurrentLocation extends Application {
    public static Double lat=0.0,lon=0.0;
    //String locality,district,state,country;

    private static CurrentLocation singleton;

    public static CurrentLocation getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton=this;
    }
}
