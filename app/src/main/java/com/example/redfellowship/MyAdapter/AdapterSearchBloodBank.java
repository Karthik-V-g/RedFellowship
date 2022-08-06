package com.example.redfellowship.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redfellowship.Model.RowModelSearchBloodBank;
import com.example.redfellowship.R;
import com.example.redfellowship.RouteMap;

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

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {
            TextView name,bloodtype,units,address;
            CircleImageView image;
            ImageView map,call;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sbbname);
            bloodtype=itemView.findViewById(R.id.sbbbloodtype);
            units=itemView.findViewById(R.id.sbbunits);
            address=itemView.findViewById(R.id.sbbaddress);
            map=itemView.findViewById(R.id.btnLocation);
            call=itemView.findViewById(R.id.call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:1234567895"));
                    context.startActivity(i);

                }
            });
            itemView.setOnClickListener(this);

        }
            @Override
            public void onClick(View v) {
                int position=getAbsoluteAdapterPosition();
                // Toast.makeText(context,"position"+position,Toast.LENGTH_SHORT).show();
                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent1=new Intent(context, RouteMap.class);
                    intent1.putExtra("sdlatitude", modelList.get(position).getsbblat());
                    intent1.putExtra("sdlongitude",modelList.get(position).getsbblon());
                    context.startActivity(intent1);

                    }
                });

            }

    }
}
