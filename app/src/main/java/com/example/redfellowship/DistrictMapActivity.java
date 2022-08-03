package com.example.redfellowship;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationServices;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.Circle;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DistrictMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "CircleDemoActivity";

    private SupportMapFragment mSupportMapFragment;
    private final static int REQUEST_CODE = 100;
    private HuaweiMap hMap;
    private double lat,lon;

    private Circle mCircle;
    FusedLocationProviderClient fusedLocationProviderClient;

    List<LatLng> coord=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapInCircle);
        mSupportMapFragment.getMapAsync(this);
    }
    /******************************************************************************************************/

    private void getLastLocation(){

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            lat= location.getLatitude();lon= location.getLongitude();

                               Geocoder geocoder = new Geocoder(DistrictMapActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                              //  +addresses.get(0).getAddressLine(0)+" "+addresses.get(0).getCountryName()+
                                Toast.makeText(DistrictMapActivity.this,addresses.get(0).getLocality()+" "+addresses.get(0).getSubAdminArea()+" "+addresses.get(0).getAdminArea()+" "+lat+" "+lon, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    /******************************************************************************************************/

    public void onMapReady(HuaweiMap paramHuaweiMap) {
        Log.i(TAG, "onMapReady: ");
        hMap = paramHuaweiMap;
        hMap.setMyLocationEnabled(true);
        //hMap.setInfoWindowAdapter(new CircleDemoActivity().CustomInfoWindowAdapter());
        //hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.42352218, 77.56064701), 16));
        // Toast.makeText(this, lat+"omr"+lon, Toast.LENGTH_SHORT).show();
        addMarker();
        hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));


    }

    /******************************************************************************************************/


    public void addMarker() {
        if (null == hMap) {
            return;
        }
        coord.add(new LatLng(48.894815937846694,2.334336934007315));
        coord.add(new LatLng(48.89330543732833,2.337345838413332));
        coord.add(new LatLng(48.890129909061166,2.334782359351596));
        coord.add(new LatLng(48.8944244810053,2.325320164305154));
        coord.add(new LatLng(48.89980307945247,2.332050217991183));

        coord.add(new LatLng(48.875435080068,2.334800424489361));
        coord.add(new LatLng(48.901141166286365,2.3594313649827683));
        coord.add(new LatLng(48.91370546113079,2.3319434812588127));
        coord.add(new LatLng(48.91030670744172,2.323759256906409));
        coord.add(new LatLng(48.882960571506885,2.321769285776498));

        String title="s";


        // Uses a colored icon.

        for(int i=0;i<coord.size();i++) {
            // Toast.makeText(this, i+" location ", Toast.LENGTH_SHORT).show();

                hMap.addMarker(new MarkerOptions().position(coord.get(i)).title(title).snippet("hello").clusterable(true));

        }

    }

/******************************************************************************************************/



}
