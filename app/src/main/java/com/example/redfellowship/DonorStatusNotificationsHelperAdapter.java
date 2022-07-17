package com.example.redfellowship;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonorStatusNotificationsHelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListstatus;
    ArrayList arrayListbloodtype;
    public DonorStatusNotificationsHelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListcontact,ArrayList arrayListaddress, ArrayList arrayListstatus, ArrayList arrayListbloodtype){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;
        this.arrayListstatus=arrayListstatus;
        this.arrayListbloodtype=arrayListbloodtype;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_notifications,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        viewHolderClass.tv1.setText(DonorStatusDetails_NotificationsData.DonorNames[position]);
        //viewHolderClass.tv2.setText(DonorStatusDetails_NotificationsData.DonorContact[position]);
        viewHolderClass.tv3.setText(DonorStatusDetails_NotificationsData.DonorAddress[position]);
        viewHolderClass.tv4.setText(DonorStatusDetails_NotificationsData.DonorStatus[position]);
        viewHolderClass.tv5.setText(DonorStatusDetails_NotificationsData.Bloodtypes[position]);
        if(DonorStatusDetails_NotificationsData.DonorStatus[position].equals("Available"))
        {
            viewHolderClass.tv4.setBackgroundResource(R.drawable.green_roundedcornerborder);
            viewHolderClass.tv4.setTextColor(Color.parseColor("#03c04a"));
        }
        else{
            viewHolderClass.tv4.setBackgroundResource(R.drawable.red_roundedcornerborder);
            viewHolderClass.tv4.setTextColor(Color.parseColor("#ef5350"));
        }

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"Item selected",Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayListname.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3,tv4,tv5;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.etDonor_name);
            //tv2=(TextView)itemView.findViewById(R.id.etDonor_phone);
            tv3=(TextView)itemView.findViewById(R.id.etDonor_address);
            tv4=(TextView)itemView.findViewById(R.id.etDonor_status);
            tv5=(TextView)itemView.findViewById(R.id.etDonor_bloodtype);

        }

    }
}
