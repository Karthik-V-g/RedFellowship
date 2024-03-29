package com.example.redfellowship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequesterStatusFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList arrayListname;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListstatus;
    ArrayList arrayListbloodtype;
    ArrayList arrayListlatitude;
    ArrayList arrayListlongitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requester_status, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);

        arrayListname = new ArrayList();
        arrayListcontact = new ArrayList();
        arrayListaddress = new ArrayList();
        arrayListstatus = new ArrayList();
        arrayListbloodtype = new ArrayList();
        arrayListlatitude=new ArrayList();
        arrayListlongitude=new ArrayList();

        for(int i=0; i<DonorStatusDetails_NotificationsData.DonorNames.length; i++)
        {
            arrayListname.add(DonorStatusDetails_NotificationsData.DonorNames);
            arrayListcontact.add(DonorStatusDetails_NotificationsData.DonorContact);
            arrayListaddress.add(DonorStatusDetails_NotificationsData.DonorAddress);
            arrayListstatus.add(DonorStatusDetails_NotificationsData.DonorStatus);
            arrayListbloodtype.add(DonorStatusDetails_NotificationsData.Bloodtypes);
            arrayListlatitude.add(DonorStatusDetails_NotificationsData.DonorLatitude);
            arrayListaddress.add(DonorStatusDetails_NotificationsData.DonorLongitude);
        }
        DonorStatusNotificationsHelperAdapter helperAdapter = new DonorStatusNotificationsHelperAdapter(getContext(), arrayListname, arrayListcontact, arrayListaddress, arrayListstatus, arrayListbloodtype,arrayListlatitude,arrayListlongitude);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return view;
    }
}



