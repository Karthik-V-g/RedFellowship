package com.example.redfellowship;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.redfellowship.utils.CurrentLocation;
import com.example.redfellowship.utils.MapUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.huawei.hms.maps.MapsInitializer;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchDonorByRequester extends AppCompatActivity {

    boolean first = true;
    Double lat,lon;
    TabLayout tabLayout;
    ViewPager2 viewPager2, vp3;
    PagerAdapter pagerAdapter, pagerAdapter1;
    AutoCompleteTextView autoCompleteTxt1, autoCompleteTxt2, autoCompleteTxt3, autoCompleteTxt4;
    ArrayAdapter<String> adapterItems1, adapterItems2, adapterItems3, adapterItems4;
    ImageView search1, search2;
    TextView map1, map2;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final String[] RUNTIME_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET};

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasPermissions(SearchDonorByRequester.this, RUNTIME_PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, RUNTIME_PERMISSIONS, REQUEST_CODE);
        }

        MapsInitializer.initialize(this);
        MapsInitializer.setApiKey(MapUtils.API_KEY);
        setContentView(R.layout.activity_search_donor_by_requester);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        /*-------------------------------------------------------------------------------------------*/

        FragmentManager fm = getSupportFragmentManager();

        search1 = findViewById(R.id.imgsearch1);
        search2 = findViewById(R.id.imgsearch2);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager2a);
        map1 = findViewById(R.id.textView4a);
        map2 = findViewById(R.id.textView5a);

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pagerAdapter = new PagerAdapter(fm, getLifecycle(), 1);
                viewPager2.setAdapter(pagerAdapter);

                Toast.makeText(SearchDonorByRequester.this, "District", Toast.LENGTH_SHORT).show();

            }
        });
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pagerAdapter = new PagerAdapter(fm, getLifecycle(), 2);
                viewPager2.setAdapter(pagerAdapter);

                Toast.makeText(SearchDonorByRequester.this, "Distance", Toast.LENGTH_SHORT).show();
            }
        });

        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SearchDonorByRequester.this, DistrictMapActivity.class));
            }
        });

        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SearchDonorByRequester.this, CircleDemoActivity.class));
            }
        });
        /*-------------------------------------------------------------------------------------------*/

        autoCompleteTxt1 = findViewById(R.id.auto_complete_txt1);

        adapterItems1 = new ArrayAdapter<String>(this, R.layout.list_item, getResources().getStringArray(R.array.Blood_Group));
        autoCompleteTxt1.setAdapter(adapterItems1);

        autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        /*-------------------------------------------------------------------------------------------*/
        autoCompleteTxt2 = findViewById(R.id.auto_complete_txt2);

        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item, getResources().getStringArray(R.array.District));
        autoCompleteTxt2.setAdapter(adapterItems2);

        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        /*-------------------------------------------------------------------------------------------*/
        autoCompleteTxt3 = findViewById(R.id.auto_complete_txt3);

        adapterItems3 = new ArrayAdapter<String>(this, R.layout.list_item, getResources().getStringArray(R.array.Distance));
        autoCompleteTxt3.setAdapter(adapterItems3);

        autoCompleteTxt3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        /*-------------------------------------------------------------------------------------------*/
        autoCompleteTxt4 = findViewById(R.id.auto_complete_txt4);

        adapterItems4 = new ArrayAdapter<String>(this, R.layout.list_item, getResources().getStringArray(R.array.Blood_Group));
        autoCompleteTxt4.setAdapter(adapterItems4);

        autoCompleteTxt4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        /*-------------------------------------------------------------------------------------------*/


        pagerAdapter = new PagerAdapter(fm, getLifecycle(), 3);
        viewPager2.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        /*-------------------------------------------------------------------------------------------*/
        /*DonorFragment donorFragment=new DonorFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.viewpager2,donorFragment);
        fragmentTransaction.commit();*/

        /*Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        String lon = intent.getStringExtra("lon");
        Toast.makeText(this, lat + lon, Toast.LENGTH_SHORT).show();*/

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(SearchDonorByRequester.this);
        //getLastLocation();

        if (ActivityCompat.checkSelfPermission(SearchDonorByRequester.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SearchDonorByRequester.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else{
            ActivityCompat.requestPermissions(SearchDonorByRequester.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)) {
        getCurrentLocation();
        }else{
          //  Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationManager locationManager=(LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER
        )){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                     lat = location.getLatitude();
                     lon = location.getLongitude();
                    CurrentLocation.lat=lat;
                    CurrentLocation.lon=lon;
                    Geocoder geocoder = new Geocoder(SearchDonorByRequester.this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   // Toast.makeText(SearchDonorByRequester.this,addresses.get(0).getSubAdminArea() , Toast.LENGTH_SHORT).show();
                }
                else{

                    LocationRequest locationRequest=new LocationRequest()
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                            .setInterval(10000)
                            .setFastestInterval(1000)
                            .setNumUpdates(1);
                    LocationCallback locationCallback=new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            Location location1=locationResult.getLastLocation();
                           lat= location1.getLatitude();
                           lon=location1.getLongitude();
                            CurrentLocation.lat=lat;
                            CurrentLocation.lon=lon;
                            Geocoder geocoder = new Geocoder(SearchDonorByRequester.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                           // Toast.makeText(SearchDonorByRequester.this,addresses.get(0).getSubAdminArea() , Toast.LENGTH_SHORT).show();
                        }
                    };
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                }


            }
        });
        }else{
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }

}


    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}