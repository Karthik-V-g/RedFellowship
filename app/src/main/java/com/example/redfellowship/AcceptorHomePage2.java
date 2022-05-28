package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AcceptorHomePage2 extends AppCompatActivity {
    private Button search,bloodbank,signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptor_home_page2);
        getSupportActionBar().hide();
        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,SearchDonorByRequester.class));
            }
        });

        bloodbank=(Button)findViewById(R.id.bloodbank);
        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,BloodBankDetails.class));
            }
        });

        signout=(Button)findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,LoginRequesterActivity.class));
            }
        });

    }
}