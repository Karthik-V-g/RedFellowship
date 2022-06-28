package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AcceptorHomePage2 extends AppCompatActivity {
    private Button search,bloodbank,signout,profile,notificatons,cancelauthorization;


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
                    startActivity(new Intent(AcceptorHomePage2.this,LoginRequesterActivity.class));
                    Toast.makeText(AcceptorHomePage2.this, "Please select email during authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });

        super.onStart();
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptor_home_page2);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
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

        profile=(Button)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,ProfileActivity.class));
            }
        });

       notificatons=(Button)findViewById(R.id.notifications);
        notificatons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,NotificationsActivity.class));
            }
        });

        signout=(Button)findViewById(R.id.HuaweiIdSignOutButton1);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        cancelauthorization=(Button)findViewById(R.id.HuaweiIdCancelAuthButton1);
        cancelauthorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAuthorization();
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
                startActivity(new Intent(AcceptorHomePage2.this,LoginRequesterActivity.class));
                Toast.makeText(AcceptorHomePage2.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                // showLog("signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
                Toast.makeText(AcceptorHomePage2.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
                //  showLog("signOut fail");
            }
        });
    }

    private void cancelAuthorization() {
        if (mAuthService == null) {
            return;
        }
        Task<Void> task = mAuthService.cancelAuthorization();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "cancelAuthorization success");

                Toast.makeText(AcceptorHomePage2.this, "Cancel Authorization Succesfull ", Toast.LENGTH_SHORT).show();
                //showLog("cancelAuthorization success");
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "cancelAuthorization failure：" + e.getClass().getSimpleName());
                Toast.makeText(AcceptorHomePage2.this, "Cancel Authorization Failed ", Toast.LENGTH_SHORT).show();
                // showLog("cancelAuthorization failure：" + e.getClass().getSimpleName());
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
                    startActivity(new Intent(AcceptorHomePage2.this,LoginRequesterActivity.class));
                    Toast.makeText(AcceptorHomePage2.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                    //showLog("signOut Success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.i(TAG, "signOut fail");
                    Toast.makeText(AcceptorHomePage2.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
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