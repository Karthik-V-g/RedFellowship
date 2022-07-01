package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

import java.util.Objects;

public class HuaweiLoginProfileDetails extends AppCompatActivity {
EditText mail,name;
    private Button ButtonGoToLoginRequester;
    private CheckBox donor;
    private EditText a,b;
    private View v1,v2,v3;
    private RadioButton genderradioButton;
    private RadioGroup radioGroup;
    private Button signout;

    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParam;
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    private static final String TAG = "Account";

    @Override
    protected void onStart() {
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();

        mAuthService = AccountAuthManager.getService(this, mAuthParam);

        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {

                // showLog("silent sign in success");


            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                    startActivity(new Intent(HuaweiLoginProfileDetails.this,LoginRequesterActivity.class));
                    Toast.makeText(HuaweiLoginProfileDetails.this, "Please select email during authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huawei_login_profile_details);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));


        Intent i=getIntent();

        String vemail=i.getStringExtra("temail");
        String vname=i.getStringExtra("tname");


        mail=(EditText) findViewById(R.id.etUpdName);
        name=(EditText) findViewById(R.id.etUpdGmail);


        mail.setText(vemail);
        name.setText(vname);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        ButtonGoToLoginRequester = findViewById(R.id.btnARProfileUpdate);
        ButtonGoToLoginRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(HuaweiLoginProfileDetails.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HuaweiLoginProfileDetails.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();}
                startActivity(new Intent(HuaweiLoginProfileDetails.this,AcceptorHomePage2.class));
            }
        });

        donor=findViewById(R.id.checkBox);
        a=findViewById(R.id.etUpdBloodType);
        b=findViewById(R.id.etUpdLastDonation);
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


        signout=(Button)findViewById(R.id.btnSignOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    private void signOut() {
        if (mAuthService == null) {
            return;
        }
        Task<Void> signOutTask = mAuthService.signOut();
        signOutTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "signOut Success");
                startActivity(new Intent(HuaweiLoginProfileDetails.this,LoginRequesterActivity.class));
                Toast.makeText(HuaweiLoginProfileDetails.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                // showLog("signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
                Toast.makeText(HuaweiLoginProfileDetails.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
                //  showLog("signOut fail");
            }
        });
    }



    boolean doubleBackToExitPressedOnce=false;
    public void onBackPressed() {


        if(doubleBackToExitPressedOnce){

            if (mAuthService == null) {
                return;
            }
            Task<Void> signOutTask = mAuthService.signOut();
            signOutTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.i(TAG, "signOut Success");
                    startActivity(new Intent(HuaweiLoginProfileDetails.this,LoginRequesterActivity.class));
                    Toast.makeText(HuaweiLoginProfileDetails.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                    //showLog("signOut Success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.i(TAG, "signOut fail");
                    Toast.makeText(HuaweiLoginProfileDetails.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
                    // showLog("signOut fail");
                }
            });

        }
        else{

            Toast.makeText(this, "Please click again BACK to Signout", Toast.LENGTH_SHORT).show();
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