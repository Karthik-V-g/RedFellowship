package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.agconnect.cloud.database.AGConnectCloudDB;
import com.huawei.agconnect.cloud.database.CloudDBZone;
import com.huawei.agconnect.cloud.database.CloudDBZoneConfig;
import com.huawei.agconnect.cloud.database.ListenerHandler;

import java.util.Calendar;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {


    private Button ButtonGoToLoginRequester;
    private CheckBox donor;
    private EditText a, mDateDOB, mDateLastDonation;
    private View l1, l2;
    private RadioButton genderradioButton;
    private RadioGroup radioGroup;

    DatePickerDialog.OnDateSetListener onDOBDateSetListener, onLastDonationDateSetListener;

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        ButtonGoToLoginRequester = findViewById(R.id.btnARProfileUpdate);
        ButtonGoToLoginRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(ProfileActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        donor=findViewById(R.id.checkBox);
        a=findViewById(R.id.etUpd2BloodType);
        l1=findViewById(R.id.layoutBlood);
        l2=findViewById(R.id.layoutLastDonation);


        donor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a.setVisibility(View.VISIBLE);
                    mDateLastDonation.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                } else {
                    a.setVisibility(View.GONE);
                    mDateLastDonation.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                }

            }
        });
        /*----Drop down for District---------------------------------------------------------------*/
        String[] districts = getResources().getStringArray(R.array.District);
        autoCompleteTxt = findViewById(R.id.etUpdDistrict);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, districts);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected district: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        /*-------Drop down for BloodType------------------------------------------------------------------------------------*/
        String[] blood_group =  getResources().getStringArray(R.array.Blood_Group);
        autoCompleteTxt = findViewById(R.id.etUpd2BloodType);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,blood_group);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected Blood Group: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        /*----Date picker for DOB---------------------------------------------------------------*/

        final Calendar calendar = Calendar.getInstance();
        int DOByear = calendar.get(Calendar.YEAR);
        int DOBmonth = calendar.get(Calendar.MONTH);
        int DOBday = calendar.get(Calendar.DAY_OF_MONTH);
        mDateDOB = findViewById(R.id.etUpdDOB);
        mDateDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDOBDateSetListener, DOByear, DOBmonth, DOBday);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDOBDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateDOB.setText(date);
            }
        };

        /*------Date picker for Last Donation Date------------------------------------------------------------------------------------*/
        int LastDonationyear = calendar.get(Calendar.YEAR);
        int LastDonationmonth = calendar.get(Calendar.MONTH);
        int LastDonationday = calendar.get(Calendar.DAY_OF_MONTH);
        mDateLastDonation = findViewById(R.id.etUpdLastDonation);
        mDateLastDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onLastDonationDateSetListener, LastDonationyear, LastDonationmonth, LastDonationday);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onLastDonationDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateLastDonation.setText(date);
            }
        };

        /*-------------------------------------------------------------------------------------------*/

    }
}