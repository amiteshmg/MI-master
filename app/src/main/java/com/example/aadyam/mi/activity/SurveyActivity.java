package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.fragment.Cylinder;
import com.example.aadyam.mi.fragment.General;
import com.example.aadyam.mi.fragment.PersonalInfo;
import com.example.aadyam.mi.fragment.Regulator;
import com.example.aadyam.mi.fragment.Rubberhose;
import com.example.aadyam.mi.fragment.Stove;
import com.example.aadyam.mi.fragment.Unsafe;
import com.example.aadyam.mi.fragment.UploadPhoto;
import com.example.aadyam.mi.model.Allotment;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends FragmentActivity
{
    private TabLayout tabLayout;
    private TextView displayName;
    private TextView displayId;
    private ViewPager viewPager1;
    private Toolbar toolbar1;

    private DatabaseHelperUser databaseHelperUser;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        toolbar1 = findViewById(R.id.toolbar1);
        displayName=findViewById(R.id.display_name_tv);
        displayId=findViewById(R.id.display_id_tv);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.PREFS_NAME,MODE_PRIVATE);

        displayName.setText(sharedPreferences.getString(Constants.CONSUMER_NAME,null));
        displayId.setText(sharedPreferences.getString(Constants.CONSUMER_NO,null));

        setActionBar(toolbar1);

        databaseHelperUser=new DatabaseHelperUser(this);
        viewPager1 = findViewById(R.id.viewpager1);
        setupViewPager(viewPager1,getIntent().getExtras().getInt(Constants.QUESTION_CATEGORY_FLAG));

        tabLayout = findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager1);

    }




    public void setCurrentItem(int item, boolean smoothScroll)
    {
        viewPager1.setCurrentItem(item, smoothScroll);
    }




    private void setupViewPager(ViewPager viewPager,int CODE)
    {
        SurveyActivity.ViewPagerAdapter1 adapter = new ViewPagerAdapter1(getSupportFragmentManager());
        switch (CODE)
        {

            case 1:
            adapter.addFragment(new Cylinder(), "Cylinder");
            adapter.addFragment(new Regulator(), "Regulator");
            adapter.addFragment(new Rubberhose(), "Rubber Hose");
            adapter.addFragment(new Stove(), "Stove");
            adapter.addFragment(new General(), "General");
            adapter.addFragment(new PersonalInfo(), "Personal Information");
            adapter.addFragment(new UploadPhoto(), "Upload Photo");
            break;

            case 2:
                adapter.addFragment(new Unsafe(), "Unsafe Questions");
                adapter.addFragment(new UploadPhoto(), "Upload Photo");
                break;
        }

        viewPager.setAdapter(adapter);
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

       /* public void notifyItemChanged(Object oldItem, Object newItem)
       {
            if (mItems != null) {
                for (ItemInfo itemInfo : mItems) {
                    if (itemInfo.object.equals(oldItem)) {
                        itemInfo.object = newItem;
                    }
                }
            }
            invalidate();
        }*/


        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitleList.get(position);
        }
    }

}

