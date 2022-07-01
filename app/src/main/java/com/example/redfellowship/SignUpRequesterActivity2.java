package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class SignUpRequesterActivity2 extends AppCompatActivity {

    private Button ButtonGoToLoginRequester;
    private CheckBox donor;
    private EditText a,b;
    private View v1,v2,v3;
    private RadioButton genderradioButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_requester2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        ButtonGoToLoginRequester = findViewById(R.id.btnGotoLogin);
        ButtonGoToLoginRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(SignUpRequesterActivity2.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUpRequesterActivity2.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                }

               startActivity(new Intent(SignUpRequesterActivity2.this,LoginRequesterActivity.class));

            }
        });

        donor=findViewById(R.id.checkBox);
        a=findViewById(R.id.etRegBloodType);
        b=findViewById(R.id.etRegLastDonation);
        v1=findViewById(R.id.v1);
        v2=findViewById(R.id.v2);
        v3=findViewById(R.id.v3);


        donor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a.setVisibility(View.VISIBLE);  b.setVisibility(View.VISIBLE);
                    v1.setVisibility(View.VISIBLE);   v2.setVisibility(View.VISIBLE);   v3.setVisibility(View.VISIBLE);
                }
                else{
                    a.setVisibility(View.GONE);  b.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);   v2.setVisibility(View.GONE);   v3.setVisibility(View.GONE);
                }

            }
        });


    }
}