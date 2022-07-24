package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;

public class BloodBankDetails2 extends AppCompatActivity {
TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details2);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        Intent intent=getIntent();
        String bbname=intent.getStringExtra("bbname");
        String bbcontact=intent.getStringExtra("bbcontact");
        String bbaddress=intent.getStringExtra("bbaddress");
       // Toast.makeText(this, "bbname"+bbname, Toast.LENGTH_SHORT).show();

        tv1=findViewById(R.id.BBName2);
        tv2=findViewById(R.id.sbbname);
        tv3=findViewById(R.id.sdage);

        tv1.setText(bbname);
        tv2.setText(bbcontact);
        tv3.setText(bbaddress);
    }
}