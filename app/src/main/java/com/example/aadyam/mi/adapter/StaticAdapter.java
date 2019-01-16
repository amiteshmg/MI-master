package com.example.aadyam.mi.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.interfaces.AllotmentListAdapterListener;
import com.example.aadyam.mi.model.AllotmentList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class StaticAdapter extends RecyclerView.Adapter<StaticAdapter.MyViewHolder> implements Filterable
{
    private Context context;
    DatabaseHelperUser databaseHelperUser;
    private List<AllotmentList> allotmentListFiltered;
    private AllotmentListAdapterListener listener;

    //private List<AllotmentList> allotmentList;

    //TODO Going null

    private List<AllotmentList> allotmentList;

    public StaticAdapter(List<AllotmentList> allotmentList, Context context, AllotmentListAdapterListener listener) {
        this.listener=listener;
        this.context = context;
        this.allotmentList = allotmentList;
        this.allotmentListFiltered=allotmentList;
        this.databaseHelperUser = new DatabaseHelperUser(context);
    }



    @Override
    public Filter getFilter() {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();
                if (charString.isEmpty())
                {
                    allotmentListFiltered = allotmentList;
                }

                else
                {
                    List<AllotmentList> filteredList = new ArrayList<>();

                    for (AllotmentList row : allotmentList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getConsumerName().toLowerCase().contains(charString.toLowerCase()) || row.getMobileNo().toString().contains(charString.toLowerCase()))
                        {
                            Log.d(Constants.TAG, "performFiltering: "+row.getConsumerName());
                            filteredList.add(row);
                        }
                    }
                    allotmentListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = allotmentListFiltered;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults)
            {
                if(filterResults!=null) {
                    allotmentListFiltered = (ArrayList<AllotmentList>) filterResults.values;
                    for (int i = 0; i < allotmentListFiltered.size(); i++)
                        Log.d(Constants.TAG, "publishResults: " + allotmentListFiltered.get(i).getConsumerName());
//                Toast.makeText(context, ""+allotmentListFiltered.get(1).getConsumerName(), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
              /*  if(!allotmentListFiltered.isEmpty())
                {

                }*/
                // notifyDataSetChanged();
            }

        };

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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(allotmentListFiltered.get(getAdapterPosition()));
                }
            });
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
        if(allotmentListFiltered.size()!=0) {
            final DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(context);
            //used for loading data into each entry

            final AllotmentList allotmentList = allotmentListFiltered.get(position);

            final int pos;

            pos = position + 1;


            holder.serial_no.setText("" + pos);

            holder.distributor_address.setText(allotmentList.getAreaName());
            holder.user_address.setText(allotmentList.getAddress());
            holder.consumer_no.setText(allotmentList.getConsumerNo().toString());
            holder.name.setText(allotmentList.getConsumerName());
            holder.contact_no.setText(allotmentList.getMobileNo().toString());

            holder.inspectionDate.setText(allotmentList.getLastInspDate());

            holder.call_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.alertDialog.setMessage("Are you sure you want to CALL " + allotmentList.getConsumerName() + " on Number - " +  allotmentList.getMobileNo() + "?");

                    holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" +  allotmentList.getMobileNo().toString()));
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

    }




    @Override
    public int getItemCount ()
    {
        return allotmentListFiltered.size();
    }


}

