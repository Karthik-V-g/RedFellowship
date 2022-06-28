package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButtonRequester;
    private Button loginButtonDonor;
    private Button loginButtonBloodBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loginButtonRequester = findViewById(R.id.btnLoginRequester);
        loginButtonDonor = findViewById(R.id.btnLoginDonor);
        loginButtonBloodBank = findViewById(R.id.btnLoginBloodBank);

        loginButtonRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginRequesterActivity.class));

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }
}