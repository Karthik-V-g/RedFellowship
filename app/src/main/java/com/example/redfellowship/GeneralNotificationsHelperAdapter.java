package com.example.redfellowship;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GeneralNotificationsHelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListpurpose;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListtiming;
    public GeneralNotificationsHelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListpurpose,ArrayList arrayListcontact,ArrayList arrayListaddress, ArrayList arrayListtiming){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListpurpose=arrayListpurpose;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;
        this.arrayListtiming=arrayListtiming;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_general_notifications,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        viewHolderClass.tv1.setText(DonorStatusDetails_NotificationsData.EventNames[position]);
        viewHolderClass.tv2.setText(DonorStatusDetails_NotificationsData.EventContact[position]);
        viewHolderClass.tv3.setText(DonorStatusDetails_NotificationsData.EventLocation[position]);
        viewHolderClass.tv4.setText(DonorStatusDetails_NotificationsData.EventTiming[position]);
        viewHolderClass.tv5.setText(DonorStatusDetails_NotificationsData.EventPurpose[position]);
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
            tv1=(TextView)itemView.findViewById(R.id.tv1);
            tv2=(TextView)itemView.findViewById(R.id.tv3);
            tv3=(TextView)itemView.findViewById(R.id.tv5);
            tv4=(TextView)itemView.findViewById(R.id.Timing6);
            tv5=(TextView)itemView.findViewById(R.id.Purpose6);
        }
    }
}

