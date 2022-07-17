package com.example.redfellowship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter2 extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListbloodtype;
    ArrayList arrayListbloodunits;
    ArrayList arrayListaddress;
    public HelperAdapter2(Context context,ArrayList arrayListname,ArrayList arrayListbloodtype,ArrayList arrayListbloodunits,ArrayList arrayListaddress){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListbloodtype=arrayListbloodtype;
        this.arrayListbloodunits=arrayListbloodunits;
        this.arrayListaddress=arrayListaddress;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item_blood_bank,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        viewHolderClass.tv1.setText(Data.names1[position]);
        viewHolderClass.tv2.setText(Data.BloodTypeBB[position]);
        viewHolderClass.tv3.setText(Data.Address1[position]);
        viewHolderClass.tv4.setText(Data.BloodUnits[position]);
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
            tv1=(TextView)itemView.findViewById(R.id.et_name);
            tv2=(TextView)itemView.findViewById(R.id.et_blood_typebb);
            tv3=(TextView)itemView.findViewById(R.id.et_address);
            tv4=(TextView) itemView.findViewById(R.id.et_blood_units);
        }
    }
}
