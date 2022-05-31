
package com.example.redfellowship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonorStatusNotificationsHelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListstatus;
    public DonorStatusNotificationsHelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListcontact,ArrayList arrayListaddress, ArrayList arrayListstatus){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;
        this.arrayListstatus=arrayListstatus;
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
        viewHolderClass.tv2.setText(DonorStatusDetails_NotificationsData.DonorContact[position]);
        viewHolderClass.tv3.setText(DonorStatusDetails_NotificationsData.DonorAddress[position]);
        viewHolderClass.tv4.setText(DonorStatusDetails_NotificationsData.DonorStatus[position]);
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
        TextView tv1,tv2,tv3,tv4;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.Name6);
            tv2=(TextView)itemView.findViewById(R.id.Contact6);
            tv3=(TextView)itemView.findViewById(R.id.Address6);
            tv4=(TextView)itemView.findViewById(R.id.Status5);
        }
    }
}
