package com.example.redfellowship.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redfellowship.Model.RowModelSearchBloodBank;
import com.example.redfellowship.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterSearchBloodBank extends RecyclerView.Adapter<AdapterSearchBloodBank.MyHolder> {
    Context context;

    public AdapterSearchBloodBank(Context context, List<RowModelSearchBloodBank> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    List<RowModelSearchBloodBank> modelList;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.display_item_blood_bank,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchBloodBank.MyHolder holder, int position) {

        String sbbname=modelList.get(position).getsbbname();
        String sbbbloodtype=modelList.get(position).getsbbbloodtype();
        String sbbunits=modelList.get(position).getsbbunits();
        String sbbaddress=modelList.get(position).getsbbaddress();

        //Toast.makeText(context,image,Toast.LENGTH_LONG).show();
        holder.name.setText(sbbname);
        holder.bloodtype.setText(sbbbloodtype);
        holder.units.setText(sbbunits);
        holder.address.setText(sbbaddress);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener
        {
            TextView name,bloodtype,units,address;
            CircleImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sbbname);
            bloodtype=itemView.findViewById(R.id.sbbbloodtype);
            units=itemView.findViewById(R.id.sbbunits);
            address=itemView.findViewById(R.id.sbbaddress);
            itemView.setOnClickListener(this);

        }


            public void onClick(View v) {
            int position=getAbsoluteAdapterPosition();
            Toast.makeText(context,"position"+position,Toast.LENGTH_SHORT).show();
          /* Intent intent =new Intent(context, SearchDonor2.class);
            intent.putExtra("sdname",modelList.get(position).getsdname());
            intent.putExtra("sdbloodtype",modelList.get(position).getsdbloodtype());
            context.startActivity(intent);*/




        }
    }
}
