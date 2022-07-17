package com.example.redfellowship;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BloodBankFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList name,bloodtype,address,bloodunits;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blood_bank, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        name=new ArrayList();
        bloodtype=new ArrayList();
        address=new ArrayList();
        bloodunits=new ArrayList();
        for (int i=0;i<Data.names1.length;i++){
            name.add(Data.names1);
            bloodtype.add(Data.BloodTypeBB);
            address.add(Data.Address1);
            bloodunits.add(Data.BloodUnits);
        }
        HelperAdapter2 helperAdapter=new HelperAdapter2(getContext(),name,bloodtype,bloodunits,address);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);
        return view;
    }
}