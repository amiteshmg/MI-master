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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aadyam.mi.Database.DatabaseHelperUser;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.model.AllotmentList;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.getIntentOld;


public class AllotmentAdapter extends RecyclerView.Adapter<AllotmentAdapter.MyViewHolder> {
    private Context context;


    //TODO Going null

    private List<AllotmentList> allotmentList;

    public AllotmentAdapter(List<AllotmentList> AllotmentList, Context context)
    {
        this.context=context;
        this.allotmentList=AllotmentList;

    }



    //TODO: Fetch Dummy data for now into the application and store in DB and fetch it again

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView listLayout;
        TextView serial_no,consumer_no,contact_no,name,user_address,distributor_address;
        Button not_available,denied,inspect;
        LinearLayout call_layout;
        AlertDialog.Builder alertDialog;


        MyViewHolder(View view) {
            super(view);
            listLayout=itemView.findViewById(R.id.card_funtional_view_layout);
            serial_no=itemView.findViewById(R.id.functional_serial_no_tv);
            consumer_no=itemView.findViewById(R.id.consumer_no_tv);
            contact_no=itemView.findViewById(R.id.contact_no_tv);
            name=itemView.findViewById(R.id.functional_user_name_tv);
            user_address=itemView.findViewById(R.id.functional_address_tv);
            distributor_address=itemView.findViewById(R.id.functinal_distributor_tv);
            not_available=itemView.findViewById(R.id.not_available_button);
            denied=itemView.findViewById(R.id.denied_button);
            inspect=itemView.findViewById(R.id.inspect_button);

            call_layout=itemView.findViewById(R.id.call_layout);
             alertDialog = new AlertDialog.Builder(itemView.getContext());
        }
    }






    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_functional_view, parent, false);

        return new MyViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position)
    {

        //used for loading data into each entry
        holder.denied.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                holder.alertDialog.setMessage("Are you sure you want to submit the entry as DENIED ? ");

                holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new DatabaseHelperUser(context).moveEntryToDenied(allotmentList.get(position).getConsumerNo());
                        notifyItemRemoved(position);

                        //notifyAll();

                        notify();

                        notifyDataSetChanged();
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




        final int pos;
        pos = position+1;

        holder.serial_no.setText(""+pos);

        holder.distributor_address.setText(allotmentList.get(position).getAreaName());
        holder.user_address.setText(allotmentList.get(position).getAddress());
        holder.consumer_no.setText(allotmentList.get(position).getConsumerNo().toString());
        holder.name.setText(allotmentList.get(position).getConsumerName());
        holder.contact_no.setText(allotmentList.get(position).getMobileNo().toString());

        /*final AlertDialog.Builder aleBuilder=new AlertDialog.Builder(context);
        //aleBuilder.setTitle("Call Customer");
        aleBuilder.setMessage("Opening contact number in Dial pad. Continue?");

        //aleBuilder.show();

        aleBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        aleBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {


            }
        });
*/
        holder.call_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // aleBuilder.show();

                holder.alertDialog.setMessage("Are you sure you want to CALL "+allotmentList.get(position).getConsumerName()+" on Number - "+allotmentList.get(position).getMobileNo()+"?");

                holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+allotmentList.get(position).getMobileNo().toString()));
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                });

                holder.alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }

                });

                holder.alertDialog.show();




            }
        });


        //holder.inspect.setOnClickListener(new AllotmentDisplay().onClickInspect(new View.));


        holder.inspect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                new MyGlobals(context).getJSON();

                Intent intent=new Intent(context,SurveyActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra(Constants.CONSUMER_NO,allotmentList.get(position).getConsumerNo());
                intent.putExtra(Constants.AREA_NAME,allotmentList.get(position).getAreaName());
                intent.putExtra(Constants.CONSUMER_NAME,allotmentList.get(position).getConsumerName());
                intent.putExtra(Constants.ALLOTMENT_DATE,allotmentList.get(position).getAllottedDate());
                intent.putExtra(Constants.UNIQUE_CONSUMER_NO,allotmentList.get(position).getUniqueConsumerId());
                intent.putExtra(Constants.IS_COMPLETED,allotmentList.get(position).getIsCompleted());
                intent.putExtra(Constants.ALLOTED_ID,allotmentList.get(position).getAllotmentId());



                context.startActivity(intent);









                String allot_date=allotmentList.get(position).getAllottedDate();
                String AreaName=allotmentList.get(position).getAreaName();
                String ConsumerName=allotmentList.get(position).getConsumerName();
                String ConsumerNo=allotmentList.get(position).getConsumerNo().toString();




                new MyGlobals(context).passOnAllotmentDetailsToAnswers(allot_date,AreaName,ConsumerName,ConsumerNo);


                /* Intent intent=new Intent();
                context.startActivity(intent);*/
            }
        });


    }

    private void openBuilder()
    {

    }


    @Override
    public int getItemCount()
    {
        return allotmentList.size();
    }

}





