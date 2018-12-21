package com.example.aadyam.mi.Global;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.aadyam.mi.Database.DatabaseHelperUser;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.adapter.AllotmentAdapter;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGlobals
{
    private Context mContext;
    String allot_date;
    String AreaName;
    String ConsumerName;
    String ConsumerNo;

    // constructor
    public MyGlobals(Context context)
    {
        this.mContext = context;
    }

    public String getUserName()
    {
        return "test";
    }

    public boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }


    public List<AllotmentList> getAllotment(int CLICK_CODE)
    {

        List<AllotmentList> list = new DatabaseHelperUser(mContext).getAllotmentEntries(CLICK_CODE);

        return list;

    }


    //method to abstract allotmentList from the retrofit body and pass to addQuestion method one by one
    public int getAllotmentCount()
    {
        final int[] size = new int[1];
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        //AllotmentList?UserId=8193&StaffRefNo=11710819300000002&ConsumerCount=0
        Call<Allotment> call = apiInterface.getAllotmentList();

        call.enqueue(new Callback<Allotment>()
        {
            @Override
            public void onResponse(@NonNull Call<Allotment> call, @NonNull Response<Allotment> response) {

                List<AllotmentList> allotmentLists;
                allotmentLists = new ArrayList<>();


                System.out.println(" DEMO " + response.body().getAllotmentListResult().get(1).getAddress());

                // progressDialog.dismiss();
                assert response.body() != null;

                //int size=response.body().getAllotmentListResult().size();

                 size[0] = response.body().getAllotmentListResult().size();

            }


            @Override
            public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t)
            {
                //Toast.makeText(, "onFailure : Internal error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR", t.getMessage());
                // Toast.makeText(AllotmentDisplay.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        return size[0];
    }








    public void getJSON()
    {
        JSONObject student1 = new JSONObject();
        try {
            student1.put("id", "3");
            student1.put("name", "NAME OF STUDENT");
            student1.put("year", "3rd");
            student1.put("curriculum", "Arts");
            student1.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject student2 = new JSONObject();
        try {
            student2.put("id", "2");
            student2.put("name", "NAME OF STUDENT2");
            student2.put("year", "4rd");
            student2.put("curriculum", "scicence");
            student2.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        JSONArray jsonArray = new JSONArray();

        jsonArray.put(student1);
        jsonArray.put(student2);

        JSONObject studentsObj = new JSONObject();
        try {
            studentsObj.put("Students", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String jsonStr = studentsObj.toString();

        System.out.println("jsonString: "+jsonStr);
    }

    public void passOnAllotmentDetailsToAnswers(String allot_date, String areaName, String consumerName, String consumerNo)
    {
        this.allot_date=allot_date;
        this.AreaName=areaName;
        this.ConsumerName=consumerName;
        this.ConsumerNo=consumerNo;
    }

























   /* private List<AllotmentList> getAllotment()
    {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);

        progressDialog.show();

        if (!new MyGlobals(mContext).isNetworkConnected()) {
            progressDialog.dismiss();
            Toast.makeText(mContext, "No network detected. Switching to offline mode.", Toast.LENGTH_SHORT).show();
            List<AllotmentList> list = new DatabaseHelperUser(mContext).getAllotmentEntries();

            return list;
            // recyclerView.setAdapter(new AllotmentAdapter(list,getApplicationContext()));
        }

        else
            {
            ApiInterface apiInterface;
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

            //AllotmentList?UserId=8193&StaffRefNo=11710819300000002&ConsumerCount=0
            Call<Allotment> call = apiInterface.getAllotmentList();


            call.enqueue(new Callback<Allotment>() {
                @Override
                public void onResponse(@NonNull Call<Allotment> call, @NonNull Response<Allotment> response) {

                    List<AllotmentList> allotmentLists;
                    allotmentLists = new ArrayList<>();


                    progressDialog.dismiss();
                    assert response.body() != null;

                    //int size=response.body().getAllotmentListResult().size();

                    int size = response.body().getAllotmentListResult().size();

                    for (int i = 0; i < size; i++) {
                        AllotmentList a = new AllotmentList();
                        System.out.println("Data : " + response.body().getAllotmentListResult().get(i).getAddress());
                        //TODO extract DATA and apply business logic
                        a.setAreaName(response.body().getAllotmentListResult().get(i).getAreaName());
                        a.setAddress(response.body().getAllotmentListResult().get(i).getAddress());
                        a.setMobileNo(response.body().getAllotmentListResult().get(i).getMobileNo());
                        a.setConsumerName(response.body().getAllotmentListResult().get(i).getConsumerName());
                        a.setConsumerNo(response.body().getAllotmentListResult().get(i).getConsumerNo());
                        a.setAllottedDate(response.body().getAllotmentListResult().get(i).getAllottedDate());
                        allotmentLists.add(a);
                    }

                    //TODO online mode Allotment list not returned

                *//*Intent intent=new Intent(AllotmentDisplay.this,SurveyActivity.class);
                intent.putExtra("AllotmentList", (Serializable) allotmentLists);*//*


                    //recyclerView.setAdapter(new AllotmentAdapter(allotmentLists, getApplicationContext()));
                }

                @Override
                public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t) {
                    Toast.makeText(mContext, "onFailure : Internal error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR", t.getMessage());
                    // Toast.makeText(AllotmentDisplay.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });
        }*/


}