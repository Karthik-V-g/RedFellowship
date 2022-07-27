package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
    private CardView search,bloodbank,donation_tips,profile,notificatons,health_tips;
    private View accepted_requests;

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
        getSupportActionBar().setElevation(0);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,SearchDonorByRequester.class));
            }
        });

        bloodbank=findViewById(R.id.bloodbank);
        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,BloodBankDetails.class));
            }
        });

        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,ProfileActivity.class));
            }
        });

        notificatons=findViewById(R.id.info);
        notificatons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(AcceptorHomePage2.this);
                builder.setTitle("Why should you donate blood?");
                builder.setMessage(R.string.des);
                builder.setBackground(getResources().getDrawable(R.drawable.layout_design,null));
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

            }
        });

        donation_tips=findViewById(R.id.Donation_Tips_CV);
        donation_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,Donators_Guide_Activity.class));
            }
        });

        health_tips=findViewById(R.id.Health_Tips_CV);
        health_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceptorHomePage2.this,Health_Tips_Activity.class));
            }
        });

        accepted_requests=findViewById(R.id.request_accepted_notification);
        accepted_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AcceptorHomePage2.this, NotificationsActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(AcceptorHomePage2.this, NotificationsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
/*
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

*/
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