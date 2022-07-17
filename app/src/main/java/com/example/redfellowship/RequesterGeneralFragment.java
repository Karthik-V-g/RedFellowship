package com.example.redfellowship;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RequesterGeneralFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList arrayListname;
    ArrayList arrayListpurpose;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListtiming;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requester_general, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);

        arrayListname = new ArrayList();
        arrayListpurpose = new ArrayList();
        arrayListcontact = new ArrayList();
        arrayListaddress = new ArrayList();
        arrayListtiming = new ArrayList();

        for(int i=0; i<DonorStatusDetails_NotificationsData.EventNames.length; i++)
        {
            arrayListname.add(DonorStatusDetails_NotificationsData.EventNames);
            arrayListpurpose.add(DonorStatusDetails_NotificationsData.EventPurpose);
            arrayListcontact.add(DonorStatusDetails_NotificationsData.EventContact);
            arrayListaddress.add(DonorStatusDetails_NotificationsData.EventLocation);
            arrayListtiming.add(DonorStatusDetails_NotificationsData.EventTiming);
        }

        GeneralNotificationsHelperAdapter GeneralhelperAdapter = new GeneralNotificationsHelperAdapter(getContext(), arrayListname, arrayListpurpose, arrayListcontact, arrayListaddress, arrayListtiming);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(GeneralhelperAdapter);

        return view;
    }

}






