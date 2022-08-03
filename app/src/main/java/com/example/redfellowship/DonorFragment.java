package com.example.redfellowship;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redfellowship.Model.RowModelSearchDonor;
import com.example.redfellowship.MyAdapter.AdapterSearchDonor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DonorFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterSearchDonor myAdapter;
    List<RowModelSearchDonor> modelList;
    AutoCompleteTextView bloodGroup;
    ArrayAdapter<String> adapterItems;

    Button open;

    private SimpleDateFormat mSimpleDateFormat;
    private Calendar mCalendar;
    EditText dateTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_donor, container, false);

        recyclerView=view.findViewById(R.id.recyclerview);
        modelList=new ArrayList<>();

        recyclerView.setLayoutManager((new LinearLayoutManager(getContext())));
        modelList.add(new RowModelSearchDonor( "Lisa","A+","26","02/03/2022","AHE Street,XYZ Road, Erode",R.drawable.face3,"11.3410","77.7172"));
        modelList.add(new RowModelSearchDonor("Max","A+","30","13/05/2021","123-Vision Street, Chennai",R.drawable.face1,"13.0827","80.2707"));
        modelList.add(new RowModelSearchDonor(  "Ben", "A+","25","21/01/202","Globe Garden, KK Road, Coimbatore",R.drawable.face2,"11.0168","76.9558"));

        for(int i=0;i<2;i++)
        {
         /*   RowModel rowModel=new RowModel("Sura Erode bank","8767656765","RKV Road, PL Palayam, Erode");
            modelList.add(rowModel);*/
            modelList.add(new RowModelSearchDonor(  "Ben", "A+","25","21/01/202","Globe Garden, KK Road, Coimbatore",R.drawable.face2,"11.0168","76.9558"));
        }

        myAdapter=new AdapterSearchDonor(getContext(),modelList);
        recyclerView.setAdapter(myAdapter);
      /*-----------------------------------------------------------------------------------------------------------*/
        String[] blood_group_list =  getResources().getStringArray(R.array.Blood_Group);
        open=view.findViewById(R.id.sendmsgtoall);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText aname,phno,date,bloodg,bloodu,aaddress;
                Button cancel,send;
                Dialog d=new Dialog(((SearchDonorByRequester)getActivity()),R.style.BackgroungImageStyle);
                LayoutInflater layoutInflater=((SearchDonorByRequester)getActivity()).getLayoutInflater();
                View custom_dialogue=layoutInflater.inflate(R.layout.custom_layout,null);
                aname=custom_dialogue.findViewById(R.id.etUpdName);
                phno=custom_dialogue.findViewById(R.id.etUpdPhone);
                bloodu=custom_dialogue.findViewById(R.id.etUpdUnit);
                aaddress=custom_dialogue.findViewById(R.id.etUpdAddress);
                cancel=custom_dialogue.findViewById(R.id.btnCancel);
                send=custom_dialogue.findViewById(R.id.btnAccept);

                mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm a", Locale.getDefault());
                dateTime = (EditText) custom_dialogue.findViewById(R.id.etUpdDate);
                dateTime.setOnClickListener(textListener);
                onCreateDialogue(custom_dialogue, blood_group_list);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(((SearchDonorByRequester)getActivity()), aname.getText().toString()+" "+phno.getText().toString()+" "+ bloodu.getText().toString()+" "+ aaddress.getText().toString(),Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                    }
                });
                d.setContentView(custom_dialogue);
                d.setTitle("Request");
                d.show();
            }


        });
        return view;
    }

    protected void onCreateDialogue(View custom_dialogue, String[] blood_group_list){

        bloodGroup = custom_dialogue.findViewById(R.id.etUpdBloodg);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_item,blood_group_list);
        bloodGroup.setAdapter(adapterItems);

        bloodGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),"Blood Group: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        dateTime = (EditText)custom_dialogue.findViewById(R.id.etUpdDate);


    }

    /* Define the onClickListener, and start the DatePickerDialog with users current time */
    private final View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCalendar = Calendar.getInstance();
            new DatePickerDialog(getActivity(), mDateDataSet, mCalendar.get(Calendar.YEAR),
                    mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    };

    /* After user decided on a date, store those in our calendar variable and then start the TimePickerDialog immediately */
    private final DatePickerDialog.OnDateSetListener mDateDataSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(getActivity(), mTimeDataSet, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false).show();
        }
    };

    /* After user decided on a time, save them into our calendar instance, and now parse what our calendar has into the TextView */
    private final TimePickerDialog.OnTimeSetListener mTimeDataSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            dateTime.setText(mSimpleDateFormat.format(mCalendar.getTime()));
          //  Toast.makeText((SearchDonorByRequester)getActivity(),"Date/time: "+mSimpleDateFormat.format(mCalendar.getTime()),Toast.LENGTH_SHORT).show();
        }
    };


}