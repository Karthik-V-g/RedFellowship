package com.example.redfellowship;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

import java.util.Calendar;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {


    private Button ButtonGoToLoginRequester,btn,signout;
    private CheckBox donor;
    private EditText a, mDateDOB, mDateLastDonation;
    private View l1, l2;
    private RadioButton genderradioButton;
    private RadioGroup radioGroup;
    private ImageView img1,img2;
    private  final int GALLERY_REQ_CODE=1000;
    DatePickerDialog.OnDateSetListener onDOBDateSetListener, onLastDonationDateSetListener;

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

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
                    startActivity(new Intent(ProfileActivity.this,LoginRequesterActivity.class));
                    Toast.makeText(ProfileActivity.this, "Please select email during authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });

        super.onStart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        ButtonGoToLoginRequester = findViewById(R.id.btnARProfileUpdate);
        ButtonGoToLoginRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(ProfileActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, genderradioButton.getText(), Toast.LENGTH_SHORT).show();
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

        donor=findViewById(R.id.checkBox);
        a=findViewById(R.id.etUpd2BloodType);
        l1=findViewById(R.id.layoutBlood);
        l2=findViewById(R.id.layoutLastDonation);


        donor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a.setVisibility(View.VISIBLE);
                    mDateLastDonation.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                } else {
                    a.setVisibility(View.GONE);
                    mDateLastDonation.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                }

            }
        });
        /*----Drop down for District---------------------------------------------------------------*/
        String[] districts = getResources().getStringArray(R.array.District);
        autoCompleteTxt = findViewById(R.id.etUpdDistrict);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, districts);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected district: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        /*-------Drop down for BloodType------------------------------------------------------------------------------------*/
        String[] blood_group =  getResources().getStringArray(R.array.Blood_Group);
        autoCompleteTxt = findViewById(R.id.etUpd2BloodType);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,blood_group);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected Blood Group: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        /*----Date picker for DOB---------------------------------------------------------------*/

        final Calendar calendar = Calendar.getInstance();
        int DOByear = calendar.get(Calendar.YEAR);
        int DOBmonth = calendar.get(Calendar.MONTH);
        int DOBday = calendar.get(Calendar.DAY_OF_MONTH);
        mDateDOB = findViewById(R.id.etUpdDOB);
        mDateDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDOBDateSetListener, DOByear, DOBmonth, DOBday);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDOBDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateDOB.setText(date);
            }
        };

        /*------Date picker for Last Donation Date------------------------------------------------------------------------------------*/
        int LastDonationyear = calendar.get(Calendar.YEAR);
        int LastDonationmonth = calendar.get(Calendar.MONTH);
        int LastDonationday = calendar.get(Calendar.DAY_OF_MONTH);
        mDateLastDonation = findViewById(R.id.etUpdLastDonation);
        mDateLastDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onLastDonationDateSetListener, LastDonationyear, LastDonationmonth, LastDonationday);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onLastDonationDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateLastDonation.setText(date);
            }
        };

        /*-------------------------------------------------------------------------------------------*/

        img1=findViewById(R.id.image);
        img2=findViewById(R.id.imageView2);
        btn=findViewById(R.id.btnPicture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //Toast.makeText(getApplicationContext(),"1", Toast.LENGTH_SHORT).show();
                startActivityForResult(iGallery,GALLERY_REQ_CODE);

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
                startActivity(new Intent(ProfileActivity.this,LoginRequesterActivity.class));
                Toast.makeText(ProfileActivity.this, "Signout Succesfull ", Toast.LENGTH_SHORT).show();
                // showLog("signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
                Toast.makeText(ProfileActivity.this, "Signout Failed ", Toast.LENGTH_SHORT).show();
                //  showLog("signOut fail");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                img2.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
                img1.setImageURI(data.getData());
            }
        }
    }
}