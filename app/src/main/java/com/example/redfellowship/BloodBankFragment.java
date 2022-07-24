package com.example.redfellowship;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redfellowship.Model.RowModelSearchBloodBank;
import com.example.redfellowship.Model.RowModelSearchDonor;
import com.example.redfellowship.MyAdapter.AdapterSearchBloodBank;
import com.example.redfellowship.MyAdapter.AdapterSearchDonor;

import java.util.ArrayList;
import java.util.List;

public class BloodBankFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterSearchBloodBank myAdapter;
    List<RowModelSearchBloodBank> modelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blood_bank, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        modelList=new ArrayList<>();

        recyclerView.setLayoutManager((new LinearLayoutManager(getContext())));
        modelList.add(new RowModelSearchBloodBank("Blood bank 1","A+","15","AHE Street,XYZ Road, Erode"));
        modelList.add(new RowModelSearchBloodBank("Blood bank 2","A+","24","123-Vision Street, Chennai"));
        modelList.add(new RowModelSearchBloodBank("Blood bank 3","A+", "7","Globe Garden, KK ROad, Coimbatore"));

        for(int i=0;i<2;i++)
        {
         /*   RowModel rowModel=new RowModel("Sura Erode bank","8767656765","RKV Road, PL Palayam, Erode");
            modelList.add(rowModel);*/
            modelList.add(new RowModelSearchBloodBank("Blood bank 3","A+", "7","Globe Garden, KK ROad, Coimbatore"));
        }

        myAdapter=new AdapterSearchBloodBank(getContext(),modelList);
        recyclerView.setAdapter(myAdapter);

        return view;
    }
}