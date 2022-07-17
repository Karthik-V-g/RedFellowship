package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.redfellowship.Model.RowModel;
import com.example.redfellowship.MyAdapter.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BloodBankDetails extends AppCompatActivity {
    boolean first=true;
    RecyclerView recyclerView;
    Adapter myAdapter;
    List<RowModel> modelList;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        /*-------------------------------------------------------------------------------------------*/
        String[] districts =  getResources().getStringArray(R.array.District);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,districts);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected district: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        /*-------------------------------------------------------------------------------------------*/
        recyclerView=findViewById(R.id.recyclerview2);
        modelList=new ArrayList<>();

        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        modelList.add(new RowModel("Lions Club Blood Bank","9967656765","Brough Road, Bazar Street, Salem"));
        modelList.add(new RowModel("Uyir Agaval Donations","67123456765","South Street, Manickam Road, Tiruppur"));
        modelList.add(new RowModel("Blood Alert Foundations","7734340234","Anna Nagar, Chennai"));

        for(int i=0;i<2;i++)
        {
            RowModel rowModel=new RowModel("Sura Erode bank","8767656765","RKV Road, PL Palayam, Erode");
            modelList.add(rowModel);
        }

        myAdapter=new Adapter(this,modelList);
        recyclerView.setAdapter(myAdapter);

        /*-------------------------------------------------------------------------------------------*/
    }
}