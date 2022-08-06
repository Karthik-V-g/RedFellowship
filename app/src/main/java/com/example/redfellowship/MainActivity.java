package com.example.redfellowship;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.redfellowship.utils.CurrentLocation;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CardView loginButtonDonorRequester;

    private CardView loginButtonBloodBank;
    /*******************enable location**************************/
    private static final String TAG = "MainActivity";
    private LocationRequest locationRequest;
    private static final int REQUEST_CHECK_SETTINGS = 10001;
    /*******************enable location**************************/

    Double lat, lon;
    String District;
    FusedLocationProviderClient fusedLocationProviderClient;

/*****************************************enable location**************************/
    @Override
    protected void onStart() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    /**************get location***************************************************************************/
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        getCurrentLocation();

                    } else{
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                    /**************get location***************************************************************************/
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(MainActivity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();



                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
        super.onStart();
    }
    /*******************enable location**************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }



        loginButtonDonorRequester =(CardView) findViewById(R.id.btnLoginRequester);
        loginButtonBloodBank =(CardView) findViewById(R.id.btnLoginBloodBank);

        loginButtonDonorRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginRequesterActivity.class));

            }
        });


    }

    /*******************enable location**************************/
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**************get location***************************************************************************/
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }
        /**************get location***************************************************************************/

        if (requestCode == REQUEST_CHECK_SETTINGS) {

            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(this, "GPS is tured on", Toast.LENGTH_SHORT).show();

                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "GPS required to be tured on", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*******************enable location**************************/

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
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
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
                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
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

    Boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(doubleBackToExitPressedOnce){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        else{
            Toast.makeText(this, "Please click again BACK to exit", Toast.LENGTH_SHORT).show();
            doubleBackToExitPressedOnce = true;


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}