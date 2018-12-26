package com.example.aadyam.mi.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.fragment.Cylinder;
import com.example.aadyam.mi.fragment.General;
import com.example.aadyam.mi.fragment.PersonalInfo;
import com.example.aadyam.mi.fragment.Regulator;
import com.example.aadyam.mi.fragment.Rubberhose;
import com.example.aadyam.mi.fragment.Stove;
import com.example.aadyam.mi.fragment.UploadPhoto;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.rest.ApiInterface;


import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends FragmentActivity
{

    private static final String TAG = SurveyActivity.class.getSimpleName();


    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;

    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    public static final String VIDEO_EXTENSION = "mp4";

    private static String imageStoragePath;



    private TabLayout tabLayout;
    private ViewPager viewPager1;
    Toolbar toolbar1;

    DatabaseHelperUser databaseHelperUser;
    Allotment allotment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);













     //TODO code present up is new
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);

        setActionBar(toolbar1);
        databaseHelperUser=new DatabaseHelperUser(this);
        allotment=new Allotment();
        //CylinderRecyclerView=findViewById(R.id.)
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        setupViewPager(viewPager1);

        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager1);

        List<AllotmentList> list=new ArrayList<>();
        }


    private void setupViewPager(ViewPager viewPager)
    {
        SurveyActivity.ViewPagerAdapter1 adapter = new ViewPagerAdapter1(getSupportFragmentManager());
        adapter.addFragment(new Cylinder(),"Cylinder");
        adapter.addFragment(new Regulator(), "Regulator");
        adapter.addFragment(new Rubberhose(), "Rubber Hose");
        adapter.addFragment(new Stove(), "Stove");
        adapter.addFragment(new General(), "General");
        adapter.addFragment(new PersonalInfo(), "Personal Information");
        adapter.addFragment(new UploadPhoto(), "Upload Photo");

        viewPager.setAdapter(adapter);

    }

    public void onClickSaveCylinderDetails()
    {
        Log.i("QUESTION DISPLAY","In questionDisplay");
        //new DatabaseHelperUser(getBaseContext()).
    }



    class ViewPagerAdapter1 extends FragmentPagerAdapter
    {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        ViewPagerAdapter1(FragmentManager manager)
        {
            super(manager);
        }


        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }


        void addFragment(Fragment fragment, String title)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

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



}

