package com.example.redfellowship;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redfellowship.utils.MapUtils;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.Circle;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DistrictMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "CircleDemoActivity";

    private SupportMapFragment mSupportMapFragment;
    private final static int REQUEST_CODE = 100;
    private HuaweiMap hMap;
    private double lat,lon,alat=0.0,alon=0.0;

    private Circle mCircle;


    List<LatLng> coord=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(this);
        MapsInitializer.setApiKey(MapUtils.API_KEY);
        setContentView(R.layout.activity_district_map);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));



        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapInCircle);
        mSupportMapFragment.getMapAsync(this);
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
       // hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));


    }

    /******************************************************************************************************/


    public void addMarker() {
        if (null == hMap) {
            return;
        }
        coord.add(new LatLng(11.42299180664515,77.56089747018181));
        coord.add(new LatLng(11.421036030665205,77.56017066932642));
        coord.add(new LatLng(11.414379863687222,77.55618508326901));
        coord.add(new LatLng(11.480975778495297,77.53446592883736));
        coord.add(new LatLng(11.444599238882638,77.5704376190648));

       /* coord.add(new LatLng(48.875435080068,2.334800424489361));
        coord.add(new LatLng(48.901141166286365,2.3594313649827683));
        coord.add(new LatLng(48.91370546113079,2.3319434812588127));
        coord.add(new LatLng(48.91030670744172,2.323759256906409));
        coord.add(new LatLng(48.882960571506885,2.321769285776498));*/


        for(int i=0;i<coord.size();i++){
            alat+= coord.get(i).latitude;
            alon+=coord.get(i).longitude;
        }
        alat=alat/coord.size();alon=alon/coord.size();
      //  Toast.makeText(this, ""+alat+alon, Toast.LENGTH_SHORT).show();
        // Uses a colored icon.
        hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(alat, alon), 14));
        for(int i=0;i<coord.size();i++) {
            // Toast.makeText(this, i+" location ", Toast.LENGTH_SHORT).show();

                hMap.addMarker(new MarkerOptions().position(coord.get(i)).title("").snippet("Donor").clusterable(true));

        }

    }

/******************************************************************************************************/

@Override
public void onBackPressed() {
    super.onBackPressed();
    startActivity(new Intent(DistrictMapActivity.this,SearchDonorByRequester.class));
}


}
