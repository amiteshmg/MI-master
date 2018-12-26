package com.example.aadyam.mi.Global;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.QuestionList;
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
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(mContext);
        List<AllotmentList> list = databaseHelperUser.getAllotmentEntries(CLICK_CODE);
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
                // Toast.makeText(InspectionDisplayActivity.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
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

        }

        catch (JSONException e)
        {
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




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void DynamicQuestion(Context context, View v, List<QuestionList> questionList)
    {
        final LinearLayout lm = v.findViewById(R.id.question_hold_layout);

        // create the layout params that will be used to define how your
        // button will be displayed

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);


        CardView.LayoutParams cardParams=new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,100);


        int size= questionList.size();


        //Create four
        for (int j = 0; j < size; j++) {

            LinearLayout linearLayout = new LinearLayout(context);

            CardView cardView=new CardView(context);
            // Create LinearLayout

            cardView.setLayoutParams(cardParams);
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));

            cardView.setRadius(2);
            cardView.setPadding(5,5,5,5);
            cardView.setElevation(3);
            // CardView card = new CardView(getContext());


            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(params);


            //LinearLayout horizontal_layout_questionHolder = new LinearLayout(context);
          //  horizontal_layout_questionHolder.setLayoutParams(params);
          //  horizontal_layout_questionHolder.setOrientation(LinearLayout.HORIZONTAL);


            //horizontal_layout_questionHolder.setLayoutParams(params);
            //horizontal_layout_questionHolder.setWeightSum(1);



            //LinearLayout horizontal_layout_input_Holder=new LinearLayout(context);
            //horizontal_layout_input_Holder.setLayoutParams(params);
            //horizontal_layout_input_Holder.setOrientation(LinearLayout.HORIZONTAL);

            //horizontal_layout_input_Holder.setWeightSum(1);

            // Create TextView
            TextView question = new TextView(context);
            question.setText(questionList.get(j).getDescription());
            linearLayout.addView(question);
            //linearLayout.addView(horizontal_layout_questionHolder,0);


            //radio button
            if (questionList.get(j).getFieldType().equals("C"))
            {
                final RadioButton[] rb = new RadioButton[2];
                RadioGroup rg = new RadioGroup(context); //create the RadioGroup
                rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
                String[] options = context.getResources().getStringArray(R.array.radio_options_yes_no);

                rb[0]=new RadioButton(context);
                rb[0].setText(R.string.yes);
                rb[0].setId(1+100);
                rg.addView(rb[0]);


                rb[1]=new RadioButton(context);
                rb[1].setText(R.string.no);
                rb[1].setId(2+100);
                rg.addView(rb[1]);



                /*for (int i = 0; i < options.length; i++)
                {
                    rb[i] = new RadioButton(context);
                    rb[i].setText(options[i]);
                    rb[i].setId(i + 100);
                    rg.addView(rb[i]);
                }
*/

                linearLayout.addView(rg);


                //linearLayout.addView(horizontal_layout_input_Holder);
                //you add the whole RadioGroup to the layout
                //cardView.addView(linearLayout);

            }



            //textfields
            else if (questionList.get(j).getFieldType().equals("T"))
            {


            }




            //listviews
            else if (questionList.get(j).getFieldType().equals("D"))
            {


            }


            cardView.addView(linearLayout);
            lm.addView( cardView,0);


/*
            // Create Button
            final Button btn = new Button(getContext());
            // Give button an ID
            btn.setId(j+1);
            btn.setText("Add To Cart");
            // set the layoutParams on the button
            btn.setLayoutParams(params);
            final int index = j;
            // Set click listener for button
            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {

                    Log.i("TAG", "index :" + index);

                    Toast.makeText(getContext(),
                            "Clicked Button Index :" + index,
                            Toast.LENGTH_LONG).show();

                }
            });

            //Add button to LinearLayout
            linearLayout.addView(btn);
*/


//Add button to LinearLayout defined in XML



//to get the MainLayout

//Avoid pass null in the root it ignores spaces in the child layout


        }

        Button save=new Button(context);
        Button next =new Button(context);

        save.setWidth(60);
        save.setHeight(30);
        save.setText("Save");


        next.setWidth(60);
        next.setHeight(30);
        next.setText("Next");


        LinearLayout buttonLayout=new LinearLayout(context);
        buttonLayout.setLayoutParams(params);

        lm.addView(buttonLayout,0);



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

                *//*Intent intent=new Intent(InspectionDisplayActivity.this,SurveyActivity.class);
                intent.putExtra("AllotmentList", (Serializable) allotmentLists);*//*


                    //recyclerView.setAdapter(new AllotmentAdapter(allotmentLists, getApplicationContext()));
                }

                @Override
                public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t) {
                    Toast.makeText(mContext, "onFailure : Internal error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR", t.getMessage());
                    // Toast.makeText(InspectionDisplayActivity.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });
        }*/

    }
}