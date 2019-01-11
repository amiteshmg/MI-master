package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.androidquery.AQuery;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.session.AlertDialogManager;
import com.example.aadyam.mi.activity.session.SessionManager;
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

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.AQuery;

/*
import com.aquery.AQuery;
import com.aquery.*;
*/



public class MainActivity extends FragmentActivity
{

    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle drawerToggle;
AQuery aQuery=new AQuery(this);

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //AQuery aQuery=new AQuery(this);


    Toolbar toolbar;
    ProgressDialog progressDialog;
    DatabaseHelperUser databaseHelperUser;


    int UserId,ConsumerCount;
    long StaffRefNo;
    Allotment allotment;
    List<Allotment> allotmentLists;
    private String number;

    // Activity request codes
    public  static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME="MI Images";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    private FragmentRefreshListener fragmentRefreshListener;
    // Session Manager Class
    SessionManager session;
    SwipeRefreshLayout swipeRefreshLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    Date c;
    NavigationView navigationView;
    //QuestionAsync questionAsync;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle(R.string.dashboard);

        tabLayout = findViewById(R.id.tabs);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        progressDialog=new ProgressDialog(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        databaseHelperUser=new DatabaseHelperUser(getApplicationContext());
        mDrawerLayout=findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
//        toolbar.setBackgroundDrawable();
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
/*
//        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
*/
        navigationView = findViewById(R.id.navigation_view);
        c=Calendar.getInstance().getTime();

        databaseHelperUser.getQuestion();

        Log.i("DATE", String.valueOf(c));

        setActionBar(toolbar);

       /* drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0)
        {
            public void onDrawerClosed(View view)
            {
                toolbar.setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View drawerView)
            {
                toolbar.setTitle(R.string.app_name);
            }
        };

        mDrawerLayout.setDrawerListener(drawerToggle);*/

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
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

                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });

        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);

        viewPager = findViewById(R.id.viewpager);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
            }
        });*/


       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh()
           {
             /*   databaseHelperUser.getAllotment();
                Fragment fragment=getFragment();
                //Fragment fragment1=getFragment(viewPager,0);
               Objects.requireNonNull(viewPager.getAdapter()).notifyDataSetChanged();

               final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
               assert fragment != null;

               ft.detach(fragment).attach(fragment).addToBackStack(null).commit();
               //ft.addToBackStack(null);

               */
               swipeRefreshLayout.setRefreshing(false);


           }
       });

        setupViewPager(viewPager);


        tabLayout.setupWithViewPager(viewPager);

    }





    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }






    @Override
    protected void onRestart()
    {
        super.onRestart();
    }



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }



    @Override
    protected void onStart()
    {
        super.onStart();
    }


    //to add fragments to ViewPager
    private void setupViewPager(ViewPager viewPager)
    {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_today(), "Today's Inspection");
        adapter.addFragment(new Fragment_total(), "Total Inspection");
        viewPager.setAdapter(adapter);
    }

    private Fragment getFragment()
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
       /* if(tabLayout.getSelectedTabPosition()==1)
        {
            return adapter.getItem(0);
        }

        else if(tabLayout.getSelectedTabPosition()==2)
            return adapter.getItem(1);
*/
       /* switch (position)
        {
            case 0:
              return adapter.getItem(position);


            case 1:
                return adapter.getItem(position);
             //   adapter.getItem(position).refresh();

        }*/
        return adapter.getItem(1);
        //return null;
    }


    //adapt the viewpager to the tabLayout
    class ViewPagerAdapter extends FragmentPagerAdapter
    {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
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

            }
            return null;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("StaticFieldLeak")
    public class MyTask extends AsyncTask
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
            public void onResponse(@NonNull Call<Distributor> call, @NonNull Response<Distributor> response) {


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

    }

    public interface FragmentRefreshListener{
        void onRefresh();
    }



}
