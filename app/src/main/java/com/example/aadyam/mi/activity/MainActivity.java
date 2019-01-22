package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.androidquery.AQuery;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.interfaces.DataUpdateListener;
import com.example.aadyam.mi.session.AlertDialogManager;
import com.example.aadyam.mi.session.SessionManager;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.fragment.Fragment_today;
import com.example.aadyam.mi.fragment.Fragment_total;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.Distributor;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
import com.aquery.AQuery;
import com.aquery.*;
*/



public class MainActivity extends FragmentActivity
{
    private DrawerLayout mDrawerLayout;
    private List<DataUpdateListener> mListeners;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //AQuery aQuery=new AQuery(this);
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private DatabaseHelperUser databaseHelperUser;

    int UserId,ConsumerCount;
    long StaffRefNo;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
     public static final String GALLERY_DIRECTORY_NAME="MI Images";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    private SessionManager session;
  //  SwipeRefreshLayout swipeRefreshLayout;
  private TabLayout tabLayout;
    private ViewPager viewPager;
    private Date c;
    private NavigationView navigationView;
    //QuestionAsync questionAsync;


    public MainActivity()
    {
        mListeners = new ArrayList<>();
    }

    public synchronized void registerDataUpdateListener(DataUpdateListener listener)
    {
        mListeners.add(listener);
    }

    public synchronized void unregisterDataUpdateListener(DataUpdateListener listener)
    {
        mListeners.remove(listener);
    }


    private synchronized void dataUpdated()
    {
        for (DataUpdateListener listener : mListeners)
        {
            listener.onDataUpdate();
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        //swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Please wait..");
        session = new SessionManager(getApplicationContext());
        databaseHelperUser=new DatabaseHelperUser(getApplicationContext(),this);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        navigationView = findViewById(R.id.navigation_view);
        c=Calendar.getInstance().getTime();
        databaseHelperUser.getQuestion();


        Log.i("DATE", String.valueOf(c));

        setActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                    {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        int id=menuItem.getItemId();

                        switch (id)
                        {
                            case R.id.logout:
                                SharedPreferences sharedPreferences=getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
                                session.logoutUser();
                                break;

                            case R.id.nav_settings:
                                Intent i=new Intent(MainActivity.this,SettingsActivity.class);
                                startActivity(i);

                            case R.id.sync_entries:
                                syncFragments();

                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });


        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);

        viewPager = findViewById(R.id.viewpager);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener()
        {

            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });



     /*  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
       {
           @Override
           public void onRefresh()
           {
                syncFragments();
                swipeRefreshLayout.setRefreshing(false);
           }
       });
*/
       setupViewPager(viewPager);

       tabLayout.setupWithViewPager(viewPager);
    }



    private void syncFragments()
    {
        if(new MyGlobals(getApplicationContext()).isNetworkConnected())
        {
            dataUpdated();
            databaseHelperUser.getAllotment();

        }

        else
            {
                Toast.makeText(this, "No Internet Connection! ", Toast.LENGTH_SHORT).show();
            }
        //viewPager.getAdapter().notifyDataSetChanged();
        //viewPager.getAdapter().notifyDataSetChanged();


        //viewPager.getAdapter().notifyDataSetChanged();

        /*try
        {
            wait(3000);
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        */

        // viewPager.getAdapter().notifyDataSetChanged();

        /*Fragment fragment=getFragment();

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        assert fragment != null;
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.remove(fragment).add(fragment, null).disallowAddToBackStack();
        ft.commit();*/

       // progressDialog.dismiss();

    }

    //to add fragments to ViewPager

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_today(), "Today's Inspection");
        adapter.addFragment(new Fragment_total(), "Total Inspection");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


 /*   private Fragment getFragment()
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.getItem(adapter.getItemPosition(adapter));

        return adapter.getItem(adapter.getItemPosition(adapter));
    }*/


    //adapt the viewpager to the tabLayout
    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position)
        {
            return super.instantiateItem(container, position);
        }


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private ArrayList<Integer> countData;

        ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }


        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0 : return new Fragment_today();
                case 1 : return new Fragment_total();
                default: return null;
            }
        }



        @Override
        public int getItemPosition(@NonNull Object object)
        {
            if (object instanceof Fragment_total)
            {
                ((Fragment_total) object).onDataUpdate();
            }

            else if(object instanceof  Fragment_today)
            {
                ((Fragment_today) object).onDataUpdate();
            }

            //don't return POSITION_NONE, avoid fragment recreation.
            return super.getItemPosition(object);
            //return POSITION_NONE;
        }



        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }


        void addFragment(Fragment fragment, String title)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mFragmentList.add(fragment);
            fragmentTransaction.commit();
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitleList.get(position);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /* @SuppressLint("StaticFieldLeak")*/
  /*  public class MyTask extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] objects)
        {
            return null;
        }
    }


    //function to fetch Distributor Allotment details from the Web Server
    public void getDistributorDetails()
    {
        ApiInterface apiInterface;
        apiInterface=ApiClient.getClient().create(ApiInterface.class);

        //TODO :take number from login page
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
        Call<Distributor> call = apiInterface.getDistributorDetails(sharedPreferences.getString("number","0"));

        call.enqueue(new Callback<Distributor>()
        {

            @Override
            public void onResponse(@NonNull Call<Distributor> call, @NonNull Response<Distributor> response)
            {
                assert response.body() != null;
                UserId = response.body().getDistributorList().get(0).getDistributorId();
                StaffRefNo = response.body().getDistributorList().get(0).getStaffRefNo();
                ConsumerCount = 0;
                Toast.makeText(MainActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(@NonNull Call<Distributor> call, @NonNull Throwable t)
            {
                Log.d("ERROR",t.getMessage());
                Toast.makeText(MainActivity.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
