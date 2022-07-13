package com.example.redfellowship;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DonorFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList name,contact,address;
    Button open;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_donor, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        name=new ArrayList();
        contact=new ArrayList();
        address=new ArrayList();
        for (int i=0;i<Data.names.length;i++){
            name.add(Data.names);
            contact.add(Data.Contact);
            address.add(Data.Address);
        }
        HelperAdapter helperAdapter=new HelperAdapter(getContext(),name,contact,address);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        open=view.findViewById(R.id.sendmsgtoall);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText aname,phno,date,bloodg,bloodu,aaddress;
                Button cancel,send;
                Dialog d=new Dialog(((SearchDonorByRequester)getActivity()),R.style.BackgroungImageStyle);
                LayoutInflater layoutInflater=((SearchDonorByRequester)getActivity()).getLayoutInflater();
                View custom_dialogue=layoutInflater.inflate(R.layout.custom_layout,null);
                aname=custom_dialogue.findViewById(R.id.etUpdName);
                phno=custom_dialogue.findViewById(R.id.etUpdPhone);
                date=custom_dialogue.findViewById(R.id.etUpdDOB);
                bloodg=custom_dialogue.findViewById(R.id.etUpdBloodg);
                bloodu=custom_dialogue.findViewById(R.id.etUpdUnit);
                aaddress=custom_dialogue.findViewById(R.id.etUpdAddress);
                cancel=custom_dialogue.findViewById(R.id.btnCancel);
                send=custom_dialogue.findViewById(R.id.btnAccept);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(((SearchDonorByRequester)getActivity()), aname.getText().toString()+" "+ bloodg.getText().toString()+" "+ bloodu.getText().toString()+" "+ date.getText().toString()+" "+ phno.getText().toString()+" "+ aaddress.getText().toString(), Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                    }
                });
                d.setContentView(custom_dialogue);
                d.setTitle("Request");
                d.show();
            }


        });




        return view;
    }


}