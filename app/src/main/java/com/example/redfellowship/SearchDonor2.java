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

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchDonor2 extends AppCompatActivity {
TextView sdname,sdbloodtype,sdage,sdlastdonation,sdaddress;
CircleImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donor2);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        Intent intent=getIntent();
        String name=intent.getStringExtra("sdname");
        String bloodtype=intent.getStringExtra("sdbloodtype");
        String age=intent.getStringExtra("sdage");
        String lastdonation=intent.getStringExtra("sdlastdonation");
        String address=intent.getStringExtra("sdaddress");
        int image1=Integer.parseInt(intent.getStringExtra("sdimage"));
        //Toast.makeText(this, "image"+image1, Toast.LENGTH_SHORT).show();

        image=findViewById(R.id.image);
        sdname=findViewById(R.id.sbbname);
        sdbloodtype=findViewById(R.id.sbbbloodtype);
        sdage=findViewById(R.id.sdage);
        sdlastdonation=findViewById(R.id.sdlastdonation);
        sdaddress=findViewById(R.id.sbbaddress);

        image.setImageResource(image1);
        sdname.setText(name);
        sdbloodtype.setText(bloodtype);
        sdage.setText(age);
        sdlastdonation.setText(lastdonation);
        sdaddress.setText(address);

    }
}