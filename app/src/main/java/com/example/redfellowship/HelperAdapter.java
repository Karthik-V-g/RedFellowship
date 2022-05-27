package com.example.redfellowship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListname;
    ArrayList arrayListcontact;
    ArrayList arrayListaddress;
    public HelperAdapter(Context context,ArrayList arrayListname,ArrayList arrayListcontact,ArrayList arrayListaddress){
        this.context=context;
        this.arrayListname=arrayListname;
        this.arrayListcontact=arrayListcontact;
        this.arrayListaddress=arrayListaddress;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
            viewHolderClass.tv1.setText(Data.names[position]);
            viewHolderClass.tv2.setText(Data.Contact[position]);
            viewHolderClass.tv3.setText(Data.Address[position]);
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
        TextView tv1,tv2,tv3;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.Name);
            tv2=(TextView)itemView.findViewById(R.id.Contact);
            tv3=(TextView)itemView.findViewById(R.id.Address);
        }
    }
}
