package com.example.redfellowship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActualDonorStatusNotificationsHelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListbloodtype;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;

    public ActualDonorStatusNotificationsHelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListbloodtype,ArrayList arrayListcontact,ArrayList arrayListaddress){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListbloodtype=arrayListbloodtype;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_donor_status_notifications,parent,false);
        ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass viewHolderClass=new ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass viewHolderClass=(ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass) holder;
        viewHolderClass.tv1.setText(DonorStatusDetails_NotificationsData.DonorNames[position]);
        viewHolderClass.tv2.setText(DonorStatusDetails_NotificationsData.Bloodtypes[position]);
        viewHolderClass.tv3.setText(DonorStatusDetails_NotificationsData.DonorContact[position]);
        viewHolderClass.tv5.setText(DonorStatusDetails_NotificationsData.DonorAddress[position]);
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
        TextView tv1,tv2,tv3,tv5;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.tv1);
            tv2=(TextView)itemView.findViewById(R.id.tv2);
            tv3=(TextView)itemView.findViewById(R.id.tv3);
            tv5=(TextView)itemView.findViewById(R.id.tv5);
        }
    }
}
