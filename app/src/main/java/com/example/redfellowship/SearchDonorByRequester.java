package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class SearchDonorByRequester extends AppCompatActivity  {

boolean first=true;
TabLayout tabLayout;
ViewPager2 viewPager2;
PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donor_by_requester);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        /*-------------------------------------------------------------------------------------------*/

        Spinner spinnerbg=(Spinner) findViewById(R.id.btngetBloodGroup);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Blood_Group, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbg.setAdapter(adapter1);
        spinnerbg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(first){
                   first=false;
               }else {
                   if (parent.getItemAtPosition(position).equals("Blood Type")) {
                       Toast.makeText(parent.getContext(), "Please select appropriate option!", Toast.LENGTH_SHORT).show();
                   } else {
                       String item = parent.getItemAtPosition(position).toString();
                       Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                   }
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*-------------------------------------------------------------------------------------------*/

        Spinner spinnerdistrict=findViewById(R.id.btngetDistrict);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.District, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdistrict.setAdapter(adapter2);
        spinnerdistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first){
                    first=false;
                }else {
                    if (parent.getItemAtPosition(position).equals("District")) {
                        Toast.makeText(parent.getContext(), "Please select appropriate option!", Toast.LENGTH_SHORT).show();
                    } else {
                        String item = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*-------------------------------------------------------------------------------------------*/

        Spinner spinnerdistance=findViewById(R.id.btngetDistance);
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.Distance, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdistance.setAdapter(adapter3);
        spinnerdistance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first){
                    first=false;
                }else {
                    if (parent.getItemAtPosition(position).equals("Distance")) {
                        Toast.makeText(parent.getContext(), "Please select appropriate option!", Toast.LENGTH_SHORT).show();
                    } else {
                        String item = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*-------------------------------------------------------------------------------------------*/

                  tabLayout=findViewById(R.id.tablayout);
                  viewPager2=findViewById(R.id.viewpager2a);

        FragmentManager fm=getSupportFragmentManager();
        pagerAdapter=new PagerAdapter(fm,getLifecycle());
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































    }


}