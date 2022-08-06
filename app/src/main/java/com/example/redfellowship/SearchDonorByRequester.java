package com.example.redfellowship;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.redfellowship.utils.MapUtils;
import com.google.android.material.tabs.TabLayout;
import com.huawei.hms.maps.MapsInitializer;

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
    /********************enable map*********************************************************/
    private static final String[] RUNTIME_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET};

    private static final int REQUEST_CODE = 100;
    /********************enable map*********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /********************enable map*********************************************************/
        if (!hasPermissions(SearchDonorByRequester.this, RUNTIME_PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, RUNTIME_PERMISSIONS, REQUEST_CODE);
        }
        MapsInitializer.initialize(this);
        MapsInitializer.setApiKey(MapUtils.API_KEY);
        /********************enable map*********************************************************/
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

    }



    /********************enable map*********************************************************/
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
    /********************enable map*********************************************************/

}