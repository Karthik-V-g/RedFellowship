package com.example.redfellowship;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DonorFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList name,contact,address;
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
        return view;
    }
}