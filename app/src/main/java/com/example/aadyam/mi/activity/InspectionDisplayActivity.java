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
import android.widget.TextView;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.adapter.AllotmentAdapter;
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
    List<AllotmentList> list;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

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

        allotment=new Allotment();
        callLayout=findViewById(R.id.call_layout);
        number=findViewById(R.id.contact_no_tv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(InspectionDisplayActivity.this));
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

        allotmentAdapter=new AllotmentAdapter(list, getApplicationContext());

        List<AllotmentList> list = new ArrayList<>();






        switch(getIntent().getIntExtra(Constants.CLICK_CODE,1000))
        {


            case Constants.TOTAL_ALLOTTED_PENDING:
                allotment_title.setText("Total Allottment pending");


            //  databaseHelperUser.getAllotmentEntries(CLICK_CODE);
                list= databaseHelperUser.getAllotmentEntries(Constants.TOTAL_ALLOTTED_PENDING);
                break;


            case Constants.TOTAL_UNSAFE:
                allotment_title.setText("Total Unsafe");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_UNSAFE);

                //list=new MyGlobals(getBaseContext()).getAllotment(Constants.TOTAL_UNSAFE);
                break;

            case Constants.TOTAL_DENIED:
                allotment_title.setText("Total Denied");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_DENIED);

                break;

            case Constants.TOTAL_NOT_AVAILABLE:
                allotment_title.setText("Total not available");
                list=databaseHelperUser.getAllotmentEntries(Constants.TOTAL_NOT_AVAILABLE);
                break;

        }

        recyclerView.setAdapter(allotmentAdapter);
        recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext()));

      /*  recyclerView.refreshDrawableState();
        allotmentAdapter.notifyDataSetChanged();*/
    }

}
