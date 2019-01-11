package com.example.aadyam.mi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.adapter.AllotmentAdapter;
import com.example.aadyam.mi.adapter.StaticAdapter;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class InspectionDisplayActivity extends AppCompatActivity
{
    SearchView searchView;
    DatabaseHelperUser databaseHelperUser;
    RecyclerView recyclerView;
    LinearLayout callLayout;
    TextView number ,allotment_title;
    List<AllotmentList> allotments;
    Allotment allotment;

    AllotmentAdapter allotmentAdapter;
    StaticAdapter staticAdapter;
    List<AllotmentList> list;
    int clickCode,fragType;


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
       /* Fragment_total fragment_total=new Fragment_total();
        fragment_total.refreshFragment();*/
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        clickCode=getIntent().getIntExtra(Constants.CLICK_CODE,1000);
        fragType=getIntent().getIntExtra(Constants.FRAG_TYPE,0);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_display);
        databaseHelperUser=new DatabaseHelperUser(this);
        allotment_title=findViewById(R.id.allotment_title);
        allotment=new Allotment();
        callLayout=findViewById(R.id.call_layout);
        number=findViewById(R.id.contact_no_tv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(InspectionDisplayActivity.this));
      //  searchView= findViewById(R.id.search_view);



      /*  searchView.setOnSearchClickListener(new View.OnClickListener()
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
        });*/


        //allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());
        List<AllotmentList> list = new ArrayList<>();




        switch(clickCode)
        {
            case Constants.TOTAL_ALLOTTED_PENDING:

                allotment_title.setText("Total Allottment pending");
                list= databaseHelperUser.getAllotmentEntries(Constants.TOTAL_ALLOTTED_PENDING);
                allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());
                recyclerView.setAdapter(allotmentAdapter);
                recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));

                break;


            case Constants.TOTAL_UNSAFE:
                allotment_title.setText("Total Unsafe");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_UNSAFE);
                staticAdapter=new StaticAdapter(list,getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_DENIED:
                allotment_title.setText("Total Denied");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_DENIED);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));

                break;

            case Constants.TOTAL_NOT_AVAILABLE:
                allotment_title.setText("Total not available");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_NOT_AVAILABLE);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_REALLOTTED_UNSAFE:
                allotment_title.setText("Total Re-allotted unsafe");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_UNSAFE);
                allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());
                recyclerView.setAdapter(allotmentAdapter);
                recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_REALLOTTED_DENIED:
                allotment_title.setText("Total reallotted denied");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_DENIED);
                allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());
                recyclerView.setAdapter(allotmentAdapter);
                recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_REALLOTTED_NOT_AVAILABLE:
                allotment_title.setText("Total reallotted not available");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_NOT_AVAILABLE);
                allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());
                recyclerView.setAdapter(allotmentAdapter);
                recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_TOTAL:
                allotment_title.setText("Total");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_TOTAL);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_AGAINST_UNSAFE:
                allotment_title.setText("Total against unsafe");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_UNSAFE);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));
                break;


            case Constants.TOTAL_AGAINST_DENIED:
                allotment_title.setText("Total not available");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_DENIED);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));
                break;

            case Constants.TOTAL_AGAINST_NOT_AVAILABLE:
                allotment_title.setText("Total against not available");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_NOT_AVAILABLE);
                staticAdapter=new StaticAdapter(list, getApplicationContext());
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext()));
                break;
        }


      /*  recyclerView.refreshDrawableState();
          allotmentAdapter.notifyDataSetChanged();

      */

    }



}
