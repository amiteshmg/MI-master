package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.aadyam.mi.model.DeniedInspection;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;
import com.example.aadyam.mi.service.InspectionDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class InspectionDisplayActivity extends AppCompatActivity implements DataUpdateListener,AllotmentListAdapterListener
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

    private Handler handler = new Handler();

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
        clickCode=getIntent().getIntExtra(Constants.CLICK_CODE,0);
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
                    //allotmentAdapter.notifyDataSetChanged();
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
                    recyclerView.setAdapter(new StaticAdapter(list, getApplicationContext(),this));
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

    @Override
    public void onItemClicked(int position, View v,AllotmentList allotmentList)
    {
      if(v.getId()==R.id.denied_button)
      {
          AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
          alertDialog.setMessage("Are you sure you want to submit the entry as DENIED ? ");

          alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
          {
              @Override
              public void onClick(DialogInterface dialog, int which)
              {
               /*   ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
                  progressDialog.setMessage("Please wait....");
                  progressDialog.setCancelable(false);
                  progressDialog.show();
*/
               /*   handler.postDelayed(new Runnable()
                  {
                      public void run()
                      {
                          doStuff(allotmentList);
                      }
                  }, 5000);

                  recyclerView.setAdapter(allotmentAdapter);
                  progressDialog.dismiss();*/
                  doStuff(allotmentList,0);
                  refreshAdapter(typeFlag,clickCode);
                  //refreshAdapter();
                  //recyclerView.setAdapter(allotmentAdapter);

              }
          });

          alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
          {
              @Override
              public void onClick(DialogInterface dialog, int which)
              {
                dialog.dismiss();
              }
          });

          alertDialog.show();
      }


      else if(v.getId() == R.id.not_available_button)
      {
          AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
          alertDialog.setMessage("Are you sure you want to submit the entry as NOT AVAILABLE ? ");

          alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
          {
              @Override
              public void onClick(DialogInterface dialog, int which)
              {
                 /* ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
                  progressDialog.setMessage("Please wait....");
                  progressDialog.setCancelable(false);
                  progressDialog.show();*/

             /*     handler.postDelayed(new Runnable()
                  {
                      public void run()
                      {
                          doStuff(allotmentList);
                      }
                  }, 5000);*/

                  doStuff(allotmentList,1);
                  refreshAdapter(typeFlag,clickCode);
                  //recyclerView.setAdapter(allotmentAdapter);
                  //progressDialog.dismiss();
              }
          });

          alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

              }
          });

          alertDialog.show();
      }
    }


    private void doStuff(AllotmentList allotmentList,int deniedNotAvailableFlag)
    {
     /*   setDenied(allotmentList.getAllotmentId().toString(), 0);
        databaseHelperUser.getAllotment();
        allotmentAdapter.notifyDataSetChanged();*/
        setDenied(allotmentList.getAllotmentId().toString(), deniedNotAvailableFlag);

    }


    private void setDenied(String AllotmentId, int flag)
    {
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if (flag == 0)
        {
            Call<DeniedInspection> call = apiInterface.denyInspection(AllotmentId, 1, 0);
            call.enqueue(new Callback<DeniedInspection>()
            {
                @SuppressLint("CommitPrefEdits")
                @Override
                public void onResponse(@NonNull Call<DeniedInspection> call, @NonNull Response<DeniedInspection> response)
                {
                    Toast.makeText(getApplicationContext(), response.body().getInspectionDeniedResult(), Toast.LENGTH_SHORT).show();
                }


                @Override
                public void onFailure(@NonNull Call<DeniedInspection> call, @NonNull Throwable t)
                {
                    Log.d(Constants.TAG, "set Denied onFailure() " + t.getMessage());
                }
            });
        }


        else if (flag == 1)
        {
            Call<DeniedInspection> call = apiInterface.denyInspection(AllotmentId, 0, 1);

            call.enqueue(new Callback<DeniedInspection>()
            {
                @SuppressLint("CommitPrefEdits")
                @Override
                public void onResponse(@NonNull Call<DeniedInspection> call, @NonNull Response<DeniedInspection> response)
                {
                    Toast.makeText(getApplicationContext(), response.body().getInspectionDeniedResult(), Toast.LENGTH_SHORT).show();
                }


                @Override
                public void onFailure(@NonNull Call<DeniedInspection> call, @NonNull Throwable t)
                {
                    Log.d(Constants.TAG, "set Denied onFailure() " + t.getMessage());
                }
            });
        }
    }


    public void refreshAdapter(int typeFlag,int BLOCK_CODE)
    {
        databaseHelperUser.getAllotment();
        list=databaseHelperUser.getAllotmentEntries(typeFlag,BLOCK_CODE);
        //allotmentAdapter.notifyDataSetChanged();
        //startService(new Intent(this, InspectionDataService.class));
        //recyclerView.setAdapter(allotmentAdapter);
        recyclerView.setAdapter(new AllotmentAdapter(list, getApplicationContext(),this));
        //allotmentAdapter.notifyDataSetChanged();
        //recyclerView.setAdapter(allotmentAdapter);
    }


    @Override
    public void onDataUpdate()
    {

    }
}
