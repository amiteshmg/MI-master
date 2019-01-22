package com.example.aadyam.mi.activity;

import android.app.SearchManager;
import android.content.Context;

import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.adapter.AllotmentAdapter;
import com.example.aadyam.mi.adapter.StaticAdapter;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.interfaces.AllotmentListAdapterListener;
import com.example.aadyam.mi.interfaces.DataUpdateListener;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class InspectionDisplayActivity extends AppCompatActivity implements AllotmentListAdapterListener, DataUpdateListener
{
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
    SearchView searchView;
    private int typeFlag;
    Toolbar toolbar;

    @Override
    public void onBackPressed()
    {
        /*if (!searchView.isIconified()) {
            searchView.setIconified(true);

            return;
        }*/

        super.onBackPressed();

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
        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        databaseHelperUser=new DatabaseHelperUser(this);
        allotment=new Allotment();
        callLayout=findViewById(R.id.call_layout);
        number=findViewById(R.id.contact_no_tv);
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        list = new ArrayList<>();
        typeFlag= getIntent().getExtras().getInt(Constants.FRAG_TYPE);


        switch(clickCode)
        {
            case Constants.TOTAL_ALLOTTED_PENDING:
                    getSupportActionBar().setTitle("Total Allottment pending");
                    list= databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_ALLOTTED_PENDING);
                    allotmentAdapter=new AllotmentAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(allotmentAdapter);
                    recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
                    allotmentAdapter.notifyDataSetChanged();
                    break;


            case Constants.TOTAL_UNSAFE:
                    getSupportActionBar().setTitle("Total Unsafe");
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_UNSAFE);
                    staticAdapter=new StaticAdapter(list,getApplicationContext(),this);
                    recyclerView.setAdapter(staticAdapter);
                    recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_DENIED:
                    getSupportActionBar().setTitle("Total Denied");
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_DENIED);
                    staticAdapter=new StaticAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(staticAdapter);
                    recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_NOT_AVAILABLE:
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_NOT_AVAILABLE);
                    getSupportActionBar().setTitle("Total Available");
                    staticAdapter = new StaticAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(staticAdapter);
                    recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_REALLOTTED_UNSAFE:
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_REALLOTTED_UNSAFE);
                    getSupportActionBar().setTitle("Total Re-allotted Unsafe");
                    allotmentAdapter = new AllotmentAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(allotmentAdapter);
                    recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_REALLOTTED_DENIED:
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_REALLOTTED_DENIED);
                    getSupportActionBar().setTitle("Total Re-allotted Denied");
                    allotmentAdapter = new AllotmentAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(allotmentAdapter);
                    recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_REALLOTTED_NOT_AVAILABLE:
                    getSupportActionBar().setTitle("Total Re-allotted Not Available");
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_REALLOTTED_NOT_AVAILABLE);
                    allotmentAdapter=new AllotmentAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(allotmentAdapter);
                    recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_TOTAL:
                    getSupportActionBar().setTitle("Total");
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_TOTAL);
                    staticAdapter=new StaticAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(staticAdapter);
                    recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_AGAINST_UNSAFE:
                    getSupportActionBar().setTitle("Total Against Unsafe");
                    list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_AGAINST_UNSAFE);
                    staticAdapter=new StaticAdapter(list, getApplicationContext(),this);
                    recyclerView.setAdapter(staticAdapter);
                    recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
                    break;


            case Constants.TOTAL_AGAINST_DENIED:
                getSupportActionBar().setTitle("Total Against Denied");
                list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_AGAINST_DENIED);
                staticAdapter=new StaticAdapter(list, getApplicationContext(),this);
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                break;


            case Constants.TOTAL_AGAINST_NOT_AVAILABLE:
                getSupportActionBar().setTitle("Total Against Not Available");
                list=databaseHelperUser.getAllotmentEntries(typeFlag,Constants.TOTAL_AGAINST_NOT_AVAILABLE);
                staticAdapter=new StaticAdapter(list, getApplicationContext(),this);
                recyclerView.setAdapter(staticAdapter);
                recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
                break;
        }

    }



    private void initiateRecycler(int typeFlag, List<AllotmentList> list, String total_allotment_pending, Object object, RecyclerView recyclerView)
    {

    }


    //method to adapt the changing allotment entries entered in the searchView
    public void searchFilter(String query)
    {
        if(typeFlag ==1)
        {
            allotmentAdapter.getFilter().filter(query);
            if (allotmentAdapter.getItemCount() == 0)
                recyclerView.setVisibility(View.INVISIBLE);
            else
                {

                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(allotmentAdapter);
                }
        }

        else
            {
            staticAdapter.getFilter().filter(query);

            if (staticAdapter.getItemCount() == 0)
                recyclerView.setVisibility(View.INVISIBLE);

            else
                {
                 recyclerView.setVisibility(View.VISIBLE);
                 recyclerView.setAdapter(staticAdapter);
                }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search_view).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(InspectionDisplayActivity.this,R.drawable.search_icon));

        //set color to searchview
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // filter recycler view when query submitted
                searchFilter(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String query)
            {
                // filter recycler view when text is changed
                searchFilter(query);
                return false;
            }

        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search_view)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*   private void whiteNotificationBar(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
*/

    @Override
    public void onContactSelected(AllotmentList allotmentList)
    {
        Toast.makeText(this, "Selected!"+allotmentList.getConsumerName(), Toast.LENGTH_SHORT).show();
    }


    public void refreshAdapter()
    {
        allotmentAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(allotmentAdapter);
    }

    @Override
    public void onDataUpdate()
    {

    }
}
