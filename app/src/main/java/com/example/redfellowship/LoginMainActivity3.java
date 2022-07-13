package com.example.redfellowship;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import android.widget.Toast;



public class LoginMainActivity3 extends AppCompatActivity {

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
                Toast.makeText(LoginMainActivity3.this, "Hello "+authAccount.getEmail(), Toast.LENGTH_SHORT).show();

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                    startActivity(new Intent(LoginMainActivity3.this,MainActivity3.class));
                    Toast.makeText(LoginMainActivity3.this, "Please create account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_main3);
        findViewById(R.id.HuaweiIdSignOutButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        findViewById(R.id.HuaweiIdCancelAuthButton1).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(LoginMainActivity3.this,MainActivity3.class));
                Toast.makeText(LoginMainActivity3.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
               // showLog("signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
                Toast.makeText(LoginMainActivity3.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(LoginMainActivity3.this, "Cancel Authorization Succesfull ", Toast.LENGTH_SHORT).show();
                //showLog("cancelAuthorization success");
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "cancelAuthorization failure：" + e.getClass().getSimpleName());
                Toast.makeText(LoginMainActivity3.this, "Cancel Authorization Failed ", Toast.LENGTH_SHORT).show();
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
                    startActivity(new Intent(LoginMainActivity3.this,MainActivity3.class));
                    Toast.makeText(LoginMainActivity3.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                    //showLog("signOut Success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.i(TAG, "signOut fail");
                    Toast.makeText(LoginMainActivity3.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
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