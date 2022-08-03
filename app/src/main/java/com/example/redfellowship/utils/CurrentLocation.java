package com.example.redfellowship.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation{
    Double lat,lon;
    String locality,district,state,country;

    public CurrentLocation(Double lat,Double lon,String locality,String district,String state,String country){
        this.lat=lat;
        this.lon=lon;
        this.locality=locality;
        this.district=district;
        this.state=state;
        this.country=country;
    }

    public String getLastLocation(Context context){
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            lat= location.getLatitude();lon= location.getLongitude();

                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                locality=addresses.get(0).getLocality();
                                district=addresses.get(0).getSubAdminArea();
                                state=addresses.get(0).getAdminArea();
                                country=addresses.get(0).getCountryName();
                              //  +addresses.get(0).getAddressLine(0)+" "+addresses.get(0).getCountryName()+
                                Toast.makeText(context,"cl"+addresses.get(0).getLocality()+" "+addresses.get(0).getSubAdminArea()+" "+addresses.get(0).getAdminArea()+" "+lat+" "+lon+district, Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return district;

    }

    public double getlat(){
        return lat;
    }
    public double getlon(){
        return lon;
    }
    public String getLocality(){
        return locality;
    }
    public String getDistrict(){
        return district;
    }
    public String getState(){
        return state;
    }
    public String getCountry(){
        return country;
    }
}
