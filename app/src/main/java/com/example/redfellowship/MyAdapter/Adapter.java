package com.example.redfellowship.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redfellowship.BloodBankDetails2;
import com.example.redfellowship.Model.RowModel;
import com.example.redfellowship.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    Context context;

    public Adapter(Context context, List<RowModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    List<RowModel> modelList;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    String bbname=modelList.get(position).getBbname();
    String bbcontact=modelList.get(position).getBbcontact();
    String bbaddress=modelList.get(position).getBbaddress();

    holder.tv1.setText(bbname);
        holder.tv2.setText(bbcontact);
        holder.tv3.setText(bbaddress);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
           TextView tv1,tv2,tv3;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.BBName);
            tv2=itemView.findViewById(R.id.BBContact);
            tv3=itemView.findViewById(R.id.BBAddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAbsoluteAdapterPosition();
          // Toast.makeText(context,"position"+position,Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(context, BloodBankDetails2.class);
            intent.putExtra("bbname",modelList.get(position).getBbname());
            intent.putExtra("bbcontact",modelList.get(position).getBbcontact());
            intent.putExtra("bbaddress",modelList.get(position).getBbaddress());
            intent.putExtra("bblat",modelList.get(position).getBblat());
            intent.putExtra("bblon",modelList.get(position).getBblon());
            context.startActivity(intent);


        }
    }
}
