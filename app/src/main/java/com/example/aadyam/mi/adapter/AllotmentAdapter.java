package com.example.aadyam.mi.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.DeniedInspection;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class AllotmentAdapter extends RecyclerView.Adapter<AllotmentAdapter.MyViewHolder> implements Filterable
{
    private Context context;
    //private DatabaseHelperUser databaseHelperUser;
    private List<AllotmentList> allotmentListFiltered;
    private AllotmentListAdapterListener listener;

    private List<AllotmentList> allotmentList;

    public AllotmentAdapter(List<AllotmentList> allotmentList, Context context,AllotmentListAdapterListener listener) {
        this.context = context;
        this.allotmentList = allotmentList;
        this.listener = listener;
        this.allotmentListFiltered = allotmentList;
        //this.databaseHelperUser = new DatabaseHelperUser(context);
       /* notifyDataSetChanged();*/
    }



    @Override
    public Filter getFilter()
    {
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


    /*@Override
    public void onClick(View v) {
        clickListener.onItemClicked(getItemClicked(););
    }*/


            //TODO: Fetch Dummy data for now into the application and store in DB and fetch it again

            class MyViewHolder extends RecyclerView.ViewHolder
            {
                CardView listLayout;
                TextView serial_no, consumer_no, contact_no, name, user_address, distributor_address;
                Button not_available, denied, inspect;
                LinearLayout call_layout;
                AlertDialog.Builder alertDialog;


                MyViewHolder(View view)
                {
                    super(view);
                    listLayout = itemView.findViewById(R.id.card_funtional_view_layout);
                    serial_no = itemView.findViewById(R.id.functional_serial_no_tv);
                    consumer_no = itemView.findViewById(R.id.consumer_no_tv);
                    contact_no = itemView.findViewById(R.id.contact_no_tv);
                    name = itemView.findViewById(R.id.functional_user_name_tv);
                    user_address = itemView.findViewById(R.id.functional_address_tv);
                    distributor_address = itemView.findViewById(R.id.functinal_distributor_tv);
                    not_available = itemView.findViewById(R.id.not_available_button);
                    denied = itemView.findViewById(R.id.denied_button);
                    inspect = itemView.findViewById(R.id.inspect_button);
                    call_layout = itemView.findViewById(R.id.call_layout);
                    alertDialog = new AlertDialog.Builder(itemView.getContext());
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
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_functional_view, parent, false);

                return new MyViewHolder(itemView);
            }


            @SuppressLint("SetTextI18n")
            @Override public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) throws ArrayIndexOutOfBoundsException
            {

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
                    holder.call_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            holder.alertDialog.setMessage("Are you sure you want to CALL " + allotmentList.getConsumerName() + " on Number - " + allotmentListFiltered.get(position).getMobileNo() + "?");

                            holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + allotmentList.getMobileNo().toString()));
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


                    holder.denied.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            holder.alertDialog.setMessage("Are you sure you want to submit the entry as DENIED ? ");

                            holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    databaseHelperUser.moveEntryToDenied(allotmentList.getConsumerNo());
                                    notifyDataSetChanged();
                                    // setAllotmentList(allotmentList);
                                    //new AllotmentAdapter(getAllotmentList(),context);
                                    setDenied(allotmentList.getAllotmentId().toString(), 0);
                                    // notifyItemChanged(position);
                                    //notifyItemRemoved(position);
                                    //notifyItemRemoved(position);
                                    //notifyDataSetChanged();
                                    //((InspectionDisplayActivity) context).finish();
                                    //InspectionDisplayActivity inspectionDisplayActivity=new InspectionDisplayActivity();
                                    //inspectionDisplayActivity.refreshAdapter();

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


                    holder.not_available.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            holder.alertDialog.setMessage("Are you sure you want to submit the entry as NOT AVAILABLE ? ");

                            holder.alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    databaseHelperUser.moveEntryToNotAvailable(allotmentList.getConsumerNo());
                                    notifyDataSetChanged();
                                    setDenied(allotmentList.getAllotmentId().toString(), 1);

                                    //        InspectionDisplayActivity inspectionDisplayActivity=new InspectionDisplayActivity();
                                    // inspectionDisplayActivity.refreshAdapter();

                                    ((InspectionDisplayActivity) context).overridePendingTransition(R.anim.slide_out_down, R.anim.slide_out_down);

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


                    holder.inspect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new MyGlobals(context).getJSON();
                            Intent intent = new Intent(context, SurveyActivity.class);
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            Random rand = new Random();

                            // Generate random integers in range 0 to 999
                            int rand_int1 = rand.nextInt(50000);
                            int rand_int2 = rand.nextInt(50000);


                            String allot_date = allotmentList.getAllottedDate();
                            String AreaName = allotmentList.getAreaName();
                            String ConsumerName = allotmentList.getConsumerName();
                            String ConsumerNo = allotmentList.getConsumerNo().toString();
                            String UniqueId = allotmentList.getUniqueConsumerId().toString();
                            String IsCompleted = allotmentList.getIsCompleted().toString();
                            String allotmentId = allotmentList.getAllotmentId().toString();

                            editor.putInt(Constants.INSPECTION_ID, rand_int1);
                            editor.putString(Constants.ALLOTMENT_DATE, allot_date);
                            editor.putString(Constants.AREA_NAME, AreaName);
                            editor.putString(Constants.CONSUMER_NAME, ConsumerName);
                            editor.putString(Constants.CONSUMER_NO, ConsumerNo);
                            editor.putString(Constants.UNIQUE_CONSUMER_NO, UniqueId);
                            editor.putString(Constants.IS_COMPLETED, IsCompleted);
                            editor.putString(Constants.ALLOTED_ID, allotmentId);
                            editor.commit();
         /*     intent.putExtra(Constants.CONSUMER_NO, ConsumerNo);
                intent.putExtra(Constants.AREA_NAME, AreaName);
                intent.putExtra(Constants.CONSUMER_NAME, ConsumerName);
                intent.putExtra(Constants.ALLOTMENT_DATE, allot_date);
                intent.putExtra(Constants.UNIQUE_CONSUMER_NO, UniqueId);
                intent.putExtra(Constants.IS_COMPLETED, IsCompleted);
                intent.putExtra(Constants.ALLOTED_ID, allotmentId);
*/
                            context.startActivity(intent);
                            //  new MyGlobals(context).passOnAllotmentDetailsToAnswers(allot_date, AreaName, ConsumerName, ConsumerNo);

                        }

                    });
                }




                }



            private void setDenied(String AllotmentId, int flag)
            {

                ApiInterface apiInterface;

                apiInterface = ApiClient.getClient().create(ApiInterface.class);

                //TODO :take number from login page

                if (flag == 0) {
                    Call<DeniedInspection> call = apiInterface.denyInspection(AllotmentId, 1, 0);

                    call.enqueue(new Callback<DeniedInspection>() {

                        @SuppressLint("CommitPrefEdits")
                        @Override
                        public void onResponse(@NonNull Call<DeniedInspection> call, @NonNull Response<DeniedInspection> response) {
                            Toast.makeText(context, response.body().getInspectionDeniedResult(), Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onFailure(@NonNull Call<DeniedInspection> call, @NonNull Throwable t) {
                            Log.d(Constants.TAG, "set Denied onFailure() " + t.getMessage());

                        }
                    });
                } else if (flag == 1) {
                    Call<DeniedInspection> call = apiInterface.denyInspection(AllotmentId, 0, 1);

                    call.enqueue(new Callback<DeniedInspection>() {

                        @SuppressLint("CommitPrefEdits")
                        @Override
                        public void onResponse(@NonNull Call<DeniedInspection> call, @NonNull Response<DeniedInspection> response) {
                            Toast.makeText(context, response.body().getInspectionDeniedResult(), Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onFailure(@NonNull Call<DeniedInspection> call, @NonNull Throwable t)
                        {
                            Log.d(Constants.TAG, "set Denied onFailure() " + t.getMessage());
                        }

                    });

                }

            }


            @Override
            public int getItemCount()
            {
                return allotmentListFiltered.size();
            }

            public interface AllotmentListAdapterListener
            {
                void onContactSelected(AllotmentList allotmentList);
            }
        }




