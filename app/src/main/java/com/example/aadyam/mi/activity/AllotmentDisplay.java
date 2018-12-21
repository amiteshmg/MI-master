package com.example.aadyam.mi.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.Database.DatabaseHelperUser;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.adapter.AllotmentAdapter;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class AllotmentDisplay extends AppCompatActivity {

    SearchView searchView;
    DatabaseHelperUser databaseHelperUser;
    RecyclerView recyclerView;
    LinearLayout callLayout;
    TextView number ,allotment_title;
    List<AllotmentList> allotments;
    Allotment allotment;
    ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        progressDialog.dismiss();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_display);


        databaseHelperUser=new DatabaseHelperUser(this);

        allotment_title=findViewById(R.id.allotment_title);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait while the list is loading....");


        allotment=new Allotment();

        callLayout=findViewById(R.id.call_layout);
        number=findViewById(R.id.contact_no_tv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllotmentDisplay.this));
        searchView= findViewById(R.id.search_view);


        searchView.setOnSearchClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener()
        {
            @Override
            public boolean onClose()
            {
                return false;
            }
        });



        List<AllotmentList> list;

        //getAllotment();

        list= new MyGlobals(getBaseContext()).getAllotment(getIntent().getIntExtra(Constants.CLICK_CODE,1000));



        switch(getIntent().getIntExtra(Constants.CLICK_CODE,1000))
        {


            case Constants.TOTAL_ALLOTTED_PENDING:
                allotment_title.setText("Total Allottment pending");
                break;


            case Constants.TOTAL_UNSAFE:
                allotment_title.setText("Total Unsafe");
                break;

            case Constants.TOTAL_DENIED:
                allotment_title.setText("Total Denied");
                break;

            case Constants.TOTAL_NOT_AVAILABLE:
                allotment_title.setText("Total not available");
                break;



        }


        recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));
    }


    private void getAllotment()
    {
        List<AllotmentList> list = new ArrayList<>();

        list=new MyGlobals(getBaseContext()).getAllotment(Constants.TOTAL_ALLOTTED_PENDING);


        recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));

    }
      /*  }


        else {
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


                    recyclerView.setAdapter(new AllotmentAdapter(allotmentLists, getApplicationContext()));
                }

                @Override
                public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t) {
                    Toast.makeText(AllotmentDisplay.this, "onFailure : Internal error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR", t.getMessage());

                }
            });
        }*/




}
