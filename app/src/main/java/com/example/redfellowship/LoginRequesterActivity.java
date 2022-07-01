package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.service.AccountAuthService;
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

import java.util.Objects;

public class LoginRequesterActivity extends AppCompatActivity {

    private TextView tvSignUp;
    private Button b;

    private ImageView img;
    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParam;
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    private static final String TAG = "Account";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_requester);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        tvSignUp = findViewById(R.id.tvSignUp);
        b=(Button)findViewById(R.id.btnLogin);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(LoginRequesterActivity.this, AcceptorHomePage2.class));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginRequesterActivity.this,SignUpRequesterActivity2.class));

            }
        });

        img = findViewById(R.id.HuaweiIdAuthButton1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silentSignInByHwId();

            }
        });
    }

    private void silentSignInByHwId() {

        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();

        mAuthService = AccountAuthManager.getService(this, mAuthParam);


        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {

                // showLog("silent sign in success");
                dealWithResultOfSignIn(authAccount);
                startActivity(new Intent(LoginRequesterActivity.this,HuaweiLoginProfileDetails.class));
                Toast.makeText(LoginRequesterActivity.this, "Login Succesfull ", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginRequesterActivity.this, "Hello "+authAccount.getEmail(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginRequesterActivity.this, "Hello "+authAccount.getGivenName(), Toast.LENGTH_SHORT).show();
                final String email=authAccount.getEmail().toString();
                final String name=authAccount.getGivenName().toString();

                Intent i=new Intent(LoginRequesterActivity.this,HuaweiLoginProfileDetails.class);
                i.putExtra("temail",email);
                i.putExtra("tname",name);
                startActivity(i);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                    Toast.makeText(LoginRequesterActivity.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void dealWithResultOfSignIn(AuthAccount authAccount) {

        Log.i(TAG, "display name:" + authAccount.getDisplayName());
        Log.i(TAG, "photo uri string:" + authAccount.getAvatarUriString());
        Log.i(TAG, "photo uri:" + authAccount.getAvatarUri());
        Log.i(TAG, "email:" + authAccount.getEmail());
        Log.i(TAG, "openid:" + authAccount.getOpenId());
        Log.i(TAG, "unionid:" + authAccount.getUnionId());

        //showLog("display name:" + authAccount.getDisplayName() + "photo uri string:" + authAccount.getAvatarUriString() +
        //      "email:" + authAccount.getEmail() + "openid:" + authAccount.getOpenId() + "unionid:" + authAccount.getUnionId());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {
           // Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                //showLog("sign in success");

                AuthAccount authAccount = authAccountTask.getResult();
                dealWithResultOfSignIn(authAccount);
                startActivity(new Intent(LoginRequesterActivity.this,HuaweiLoginProfileDetails.class));
                Toast.makeText(LoginRequesterActivity.this, "Login Succesfull ", Toast.LENGTH_SHORT).show();

                //Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            } else {

                //showLog("sign in failed : " + ((ApiException) authAccountTask.getException()).getStatusCode());
               // Log.e(TAG, "sign in failed : " + ((ApiException) authAccountTask.getException()).getStatusCode());
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginRequesterActivity.this,MainActivity.class));
    }
}