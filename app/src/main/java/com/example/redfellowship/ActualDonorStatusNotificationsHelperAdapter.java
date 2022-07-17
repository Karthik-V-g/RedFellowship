package com.example.redfellowship;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActualDonorStatusNotificationsHelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListbloodtype;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    ArrayList arrayListduedate;
    AlertDialog.Builder builder;

    public ActualDonorStatusNotificationsHelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListbloodtype,ArrayList arrayListcontact,ArrayList arrayListaddress, ArrayList arrayListduedate){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListbloodtype=arrayListbloodtype;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;
        this.arrayListduedate=arrayListduedate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_donor_status_notifications,parent,false);
        ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass viewHolderClass=new ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass(view);
        return viewHolderClass;
    }

    public void onClickDialog(View view){

        builder = new AlertDialog.Builder(view.getRootView().getContext());
        builder.setTitle("Blood Request Acceptance");
        builder.setMessage(R.string.check_box_message);
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context,"Thank you for your noble service!",Toast.LENGTH_LONG).show();
                Button btn = view.findViewById(R.id.btnBloodRequestAcceptDecline);
                btn.setBackgroundResource(R.drawable.green_roundedcornerborder);
                btn.setTextColor(Color.parseColor("#03c04a"));
                btn.setText("Accepted");
            }
        })
                .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Hope you can give the gift of life next time!", Toast.LENGTH_LONG).show();
                        Button btn = view.findViewById(R.id.btnBloodRequestAcceptDecline);
                        btn.setBackgroundResource(R.drawable.red_roundedcornerborder);
                        btn.setTextColor(Color.parseColor("#ef5350"));
                        btn.setText("Declined");
                    }
                });

        builder.show();

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass viewHolderClass=(ActualDonorStatusNotificationsHelperAdapter.ViewHolderClass) holder;
        viewHolderClass.tv1.setText(DonorStatusDetails_NotificationsData.RequesterNames[position]);
        viewHolderClass.tv2.setText(DonorStatusDetails_NotificationsData.Bloodtypes[position]);
        //viewHolderClass.tv3.setText(DonorStatusDetails_NotificationsData.RequesterContact[position]);
        viewHolderClass.tv4.setText(DonorStatusDetails_NotificationsData.RequesterDueDate[position]);
        viewHolderClass.tv5.setText(DonorStatusDetails_NotificationsData.RequesterAddress[position]);

        viewHolderClass.btnBloodRequestAcceptDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDialog(view);
            }
        });

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
        TextView tv1,tv2,tv3,tv5,tv4;
        Button btnBloodRequestAcceptDecline;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.etReq_name);
            tv2=(TextView)itemView.findViewById(R.id.etReq_bloodtype);
            //tv3=(TextView)itemView.findViewById(R.id.etReq_phone);
            tv4=(TextView)itemView.findViewById(R.id.etReq_date);
            tv5=(TextView)itemView.findViewById(R.id.etReq_address);
            btnBloodRequestAcceptDecline = (Button) itemView.findViewById(R.id.btnBloodRequestAcceptDecline);
        }
    }
}
