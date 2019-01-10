package com.example.aadyam.mi.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.activity.LoginActivity;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.Distributor;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.getIntentOld;


public class StaticAdapter extends RecyclerView.Adapter<StaticAdapter.MyViewHolder>
{
    private Context context;
    DatabaseHelperUser databaseHelperUser;

    //TODO Going null

    private List<AllotmentList> allotmentList;

    public StaticAdapter(List<AllotmentList> AllotmentList, Context context) {
        this.context = context;
        this.allotmentList = AllotmentList;
        this.databaseHelperUser = new DatabaseHelperUser(context);
    }


    public void setAllotmentList(List<AllotmentList> allotmentList) {
        this.allotmentList = allotmentList;
    }

    public List<AllotmentList> getAllotmentList() {
        return allotmentList;
    }


    //TODO: Fetch Dummy data for now into the application and store in DB and fetch it again

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView listLayout;
        TextView serial_no, consumer_no, contact_no, name, user_address, distributor_address;
       // Button not_available, denied, inspect;
        LinearLayout call_layout;
        AlertDialog.Builder alertDialog;
        TextView inspectionDate;


        MyViewHolder(View view) {
            super(view);
            listLayout = itemView.findViewById(R.id.card_static_view_layout);
            serial_no = itemView.findViewById(R.id.serial_no_tv);
            consumer_no = itemView.findViewById(R.id.static_consumer_no_tv);
            contact_no = itemView.findViewById(R.id.static_contact_no_tv);
            name = itemView.findViewById(R.id.static_user_name_tv);
            user_address = itemView.findViewById(R.id.static_address_tv);
            distributor_address = itemView.findViewById(R.id.static_distributor_tv);
            inspectionDate=itemView.findViewById(R.id.inspection_date_tv);
            call_layout = itemView.findViewById(R.id.static_call_layout);
           // alertDialog = new AlertDialog.Builder(itemView.getContext());
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_static_view, parent, false);

        return new MyViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        //used for loading data into each entry
        final int pos;
        pos = position + 1;



        holder.serial_no.setText("" + pos);

        holder.distributor_address.setText(allotmentList.get(position).getAreaName());
        holder.user_address.setText(allotmentList.get(position).getAddress());
        holder.consumer_no.setText(allotmentList.get(position).getConsumerNo().toString());
        holder.name.setText(allotmentList.get(position).getConsumerName());
        holder.contact_no.setText(allotmentList.get(position).getMobileNo().toString());

       holder.inspectionDate.setText(allotmentList.get(position).getLastInspDate());

        holder.call_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.alertDialog.setMessage("Are you sure you want to CALL " + allotmentList.get(position).getConsumerName() + " on Number - " + allotmentList.get(position).getMobileNo() + "?");

                holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + allotmentList.get(position).getMobileNo().toString()));
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                });

                holder.alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });

                holder.alertDialog.show();
            }
        });



    }




    @Override
    public int getItemCount ()
    {
        return allotmentList.size();
    }

}
