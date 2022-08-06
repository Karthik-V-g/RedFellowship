package com.example.redfellowship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonorStatusFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList arrayListname;
    ArrayList arrayListbloodtype;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListduedate;
    ArrayList arrayListlatitude;
    ArrayList arrayListlongitude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_donor_status, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);

        arrayListname = new ArrayList();
        arrayListbloodtype = new ArrayList();
        arrayListcontact = new ArrayList();
        arrayListaddress = new ArrayList();
        arrayListduedate = new ArrayList();
        arrayListlatitude=new ArrayList();
        arrayListlongitude=new ArrayList();

        for(int i=0; i<DonorStatusDetails_NotificationsData.DonorNames.length; i++)
        {
            arrayListname.add(DonorStatusDetails_NotificationsData.RequesterNames);
            arrayListbloodtype.add(DonorStatusDetails_NotificationsData.Bloodtypes);
            arrayListcontact.add(DonorStatusDetails_NotificationsData.RequesterContact);
            arrayListaddress.add(DonorStatusDetails_NotificationsData.RequesterAddress);
            arrayListduedate.add(DonorStatusDetails_NotificationsData.RequesterDueDate);
            arrayListlatitude.add(DonorStatusDetails_NotificationsData.RequesterLatitude);
            arrayListlongitude.add(DonorStatusDetails_NotificationsData.RequesterLongitude);

        }
        ActualDonorStatusNotificationsHelperAdapter helperAdapter = new ActualDonorStatusNotificationsHelperAdapter(getContext(), arrayListname, arrayListbloodtype,arrayListcontact, arrayListaddress, arrayListduedate,arrayListlatitude,arrayListlongitude);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);
        return view;
    }
}