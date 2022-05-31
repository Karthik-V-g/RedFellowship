package com.example.redfellowship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.redfellowship.Model.RowModel;
import com.example.redfellowship.MyAdapter.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BloodBankDetails extends AppCompatActivity {
RecyclerView recyclerView;
Adapter myAdapter;
List<RowModel>modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        recyclerView=findViewById(R.id.recyclerview2);
        modelList=new ArrayList<>();

        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        /*modelList.add(new RowModel("bb1","8767656765","qwertyuojhhhh"));
        modelList.add(new RowModel("bb2","8767656765","qwertyuojhhhh"));
        modelList.add(new RowModel("bb3","8767656765","qwertyuojhhhh"));*/

        for(int i=0;i<10;i++)
        {
            RowModel rowModel=new RowModel("Blood bank 1","8767656765","aaa,bbb,ccc");
            modelList.add(rowModel);
        }

        myAdapter=new Adapter(this,modelList);
        recyclerView.setAdapter(myAdapter);

    }
}