package com.example.redfellowship.MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redfellowship.Model.RowModelSearchDonor;
import com.example.redfellowship.R;
import com.example.redfellowship.SearchDonor2;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterSearchDonor extends RecyclerView.Adapter<AdapterSearchDonor.MyHolder> {
    Context context;

    public AdapterSearchDonor(Context context, List<RowModelSearchDonor> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    List<RowModelSearchDonor> modelList;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.display_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchDonor.MyHolder holder, int position) {
        int image=modelList.get(position).getsdimage();
        String sdname=modelList.get(position).getsdname();
        String sdbloodtype=modelList.get(position).getsdbloodtype();
        String sdaddress=modelList.get(position).getsdaddress();

        holder.image.setImageResource(image);
        //Toast.makeText(context,image,Toast.LENGTH_LONG).show();
        holder.name.setText(sdname);
        holder.bloodtype.setText(sdbloodtype);
        holder.address.setText(sdaddress);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView name,bloodtype,address;
        CircleImageView image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.sbbname);
            bloodtype=itemView.findViewById(R.id.sbbbloodtype);
            address=itemView.findViewById(R.id.sbbaddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAbsoluteAdapterPosition();
            //Toast.makeText(context,"position"+position,Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(context, SearchDonor2.class);
            intent.putExtra("sdname",modelList.get(position).getsdname());
            intent.putExtra("sdbloodtype",modelList.get(position).getsdbloodtype());
            intent.putExtra("sdage",modelList.get(position).getsdage());
            intent.putExtra("sdlastdonation",modelList.get(position).getsdlastdonation());
            intent.putExtra("sdaddress",modelList.get(position).getsdaddress());
            intent.putExtra("sdlatitude",modelList.get(position).getsdlatitude());
            intent.putExtra("sdlongitude",modelList.get(position).getsdlongitude());
            int image=modelList.get(position).getsdimage();
            String imagestr=Integer.toString(image);
            intent.putExtra("sdimage",imagestr);
            context.startActivity(intent);


        }
    }
}
