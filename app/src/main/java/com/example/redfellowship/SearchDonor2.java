package com.example.redfellowship;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchDonor2 extends AppCompatActivity {
TextView sdname,sdbloodtype,sdage,sdlastdonation,sdaddress;
ImageView map,call;
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
        call=findViewById(R.id.call);
        map=findViewById(R.id.map);

        image.setImageResource(image1);
        sdname.setText(name);
        sdbloodtype.setText(bloodtype);
        sdage.setText(age);
        sdlastdonation.setText(lastdonation);
        sdaddress.setText(address);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:1234567895"));
                startActivity(i);

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SearchDonor2.this, RouteMap.class);
                intent1.putExtra("sdlatitude",intent.getStringExtra("sdlatitude"));
                intent1.putExtra("sdlongitude",intent.getStringExtra("sdlongitude"));
                startActivity(intent1);

            }
        });

    }

}