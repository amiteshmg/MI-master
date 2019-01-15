package com.example.aadyam.mi.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.interfaces.DataUpdateListener;

import java.util.ArrayList;


public class Fragment_total extends Fragment implements DataUpdateListener
{
    TextView allotted_count_tv,unsafe_count_tv,denied_count_tv,not_available_count_tv,reallotted_unsafe_count_tv,reallotted_denied_count_tv,reallotted_not_available_count_tv,total_count_tv,against_unsafe_count_tv,against_denied_count_tv,against_not_available_count_tv;

    LinearLayout allotted_pending_layout,unsafe_layout,Denied_layout,Not_available_layout,reallotted_unsafe_layout,reallotted_denied_layout,reallotted_not_available_layout,total_layout,against_unsafe_layout,against_denied_layout,against_not_available_layout;

    ProgressDialog progressDialog;
    MyGlobals myGlobals;
    DatabaseHelperUser databaseHelperUser;


    //boolean allowRefresh;
   // private ArrayList<Integer> countData;

    public Fragment_total()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        Log.d(Constants.TAG, "onCreateView : ");
        return inflater.inflate(R.layout.fragment_fragment_total, container, false);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(Constants.TAG, "onResume : ");
        /*   View view=getView();
        countData=myGlobals.getAllotmentEntriesCount();
        setCountTextViews(view);*/

        /*  if (allowRefresh)
        {
            allowRefresh = false;
            DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
            databaseHelperUser.getAllotment();
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "onStart: ");
        /*if(countData!=null)
        countData=myGlobals.getAllotmentEntriesCount();*/
        //setCountTextViews();


    }


//    public void setAllowRefresh()
//    {
//        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
//        databaseHelperUser.getAllotment();
//        assert getFragmentManager() != null;
//        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Constants.TAG, "onDetach: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy: ");
        ((MainActivity) getActivity()).unregisterDataUpdateListener(this);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d(Constants.TAG, "onDestroyView: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "onStop: ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.TAG, "onSaveInstanceState: ");
    }

    @Override
    public void setInitialSavedState(@Nullable SavedState state) {
        super.setInitialSavedState(state);
        Log.d(Constants.TAG, "setInitialSavedState : ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(Constants.TAG, "onAttach: ");
        ((MainActivity) getActivity()).registerDataUpdateListener(this);

        /*countData=new ArrayList<>();
        countData=new MyGlobals(getContext()).getAllotmentEntriesCount();*/
        //setCountTextViews();
    }







    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        Log.d(Constants.TAG, "onViewCreated : ");

        myGlobals=new MyGlobals(getContext());
        //allowRefresh=true;
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait.....");

      /*  countData=new ArrayList<>();
        countData=myGlobals.getAllotmentEntriesCount();*/

        setCountTextViews(view);

       /* allotted_count_tv.setText(""+countData.get(0));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_UNSAFE).size());
        unsafe_count_tv.setText(""+countData.get(1));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_DENIED).size());
        denied_count_tv.setText(""+countData.get(2));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_NOT_AVAILABLE).size());
        not_available_count_tv.setText(""+countData.get(3));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_UNSAFE).size());
        reallotted_unsafe_count_tv.setText(""+countData.get(4));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_DENIED).size());
        reallotted_denied_count_tv.setText(""+countData.get(5));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_NOT_AVAILABLE).size());
        reallotted_not_available_count_tv.setText(""+countData.get(6));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_TOTAL).size());
        total_count_tv.setText(""+countData.get(7));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_UNSAFE).size());
        against_unsafe_count_tv.setText(""+countData.get(8));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_DENIED).size());
        against_denied_count_tv.setText(""+countData.get(9));

//        countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_NOT_AVAILABLE).size());
        against_not_available_count_tv.setText(""+countData.get(10));
*/

        allotted_pending_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_ALLOTTED_PENDING);
                intent.putExtra(Constants.FRAG_TYPE,0);
                startActivity(intent);
            }
        });


        unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_UNSAFE);
                startActivity(intent);
            }
        });


        Denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_DENIED);
                startActivity(intent);
            }
        });


        Not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_NOT_AVAILABLE);
                startActivity(intent);

            }
        });


        reallotted_unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_REALLOTTED_UNSAFE);
                startActivity(intent);

            }
        });


        reallotted_denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_REALLOTTED_DENIED);
                startActivity(intent);
            }
        });




        reallotted_not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_REALLOTTED_NOT_AVAILABLE);
                startActivity(intent);
            }
        });



        total_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_TOTAL);
                startActivity(intent);
            }
        });


        against_unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_AGAINST_UNSAFE);
                startActivity(intent);
            }
        });



        against_denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_AGAINST_DENIED);
                startActivity(intent);

            }
        });


        against_not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE,Constants.TOTAL_AGAINST_NOT_AVAILABLE);
                startActivity(intent);
            }
        });

    }


    private void setCountTextViews(View view)
    {

        databaseHelperUser=new DatabaseHelperUser(getContext());
        //countData=new ArrayList<>();
        ArrayList<Integer> countData = databaseHelperUser.getAllotmentEntriesCount();
        allotted_pending_layout=view.findViewById(R.id.allotted_layout);
        unsafe_layout=view.findViewById(R.id.unsafe_layout);
        Denied_layout=view.findViewById(R.id.denied_layout);
        Not_available_layout=view.findViewById(R.id.not_available_layout);
        reallotted_unsafe_layout=view.findViewById(R.id.reallotted_not_available_layout);
        reallotted_denied_layout=view.findViewById(R.id.reallotted_denied_layout);
        reallotted_not_available_layout=view.findViewById(R.id.reallotted_not_available_layout);
        total_layout=view.findViewById(R.id.total_layout);
        against_unsafe_layout=view.findViewById(R.id.against_unsafe_layout);
        against_denied_layout=view.findViewById(R.id.against_denied_layout);
        against_not_available_layout=view.findViewById(R.id.against_not_available_layout);
        //initializeLayouts(view);
        allotted_count_tv = view.findViewById(R.id.allotted_count_text);
        unsafe_count_tv = view.findViewById(R.id.unsafe_count_text);
        denied_count_tv = view.findViewById(R.id.denied_count_text);
        not_available_count_tv = view.findViewById(R.id.not_available_count_text);
        reallotted_unsafe_count_tv = view.findViewById(R.id.reallotted_unsafe_count_text);
        reallotted_denied_count_tv = view.findViewById(R.id.reallotted_denied_count_text);
        reallotted_not_available_count_tv = view.findViewById(R.id.reallotted_not_available_count_text);
        total_count_tv = view.findViewById(R.id.total_count_text);
        against_unsafe_count_tv = view.findViewById(R.id.against_unsafe_count_text);
        against_denied_count_tv = view.findViewById(R.id.against_denied_count_text);
        against_not_available_count_tv = view.findViewById(R.id.against_not_available_count_text);
        //countData=new ArrayList<>();
       // countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_ALLOTTED_PENDING).size());
        allotted_count_tv.setText(""+ countData.get(0));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_UNSAFE).size());
        unsafe_count_tv.setText(""+ countData.get(1));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_DENIED).size());
        denied_count_tv.setText(""+ countData.get(2));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_NOT_AVAILABLE).size());
        not_available_count_tv.setText(""+ countData.get(3));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_UNSAFE).size());
        reallotted_unsafe_count_tv.setText(""+ countData.get(4));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_DENIED).size());
        reallotted_denied_count_tv.setText(""+ countData.get(5));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_NOT_AVAILABLE).size());
        reallotted_not_available_count_tv.setText(""+ countData.get(6));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_TOTAL).size());
        total_count_tv.setText(""+ countData.get(7));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_UNSAFE).size());
        against_unsafe_count_tv.setText(""+ countData.get(8));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_DENIED).size());
        against_denied_count_tv.setText(""+ countData.get(9));

        //countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_NOT_AVAILABLE).size());
        against_not_available_count_tv.setText(""+ countData.get(10));

}

   /* @Override
    public void update(ArrayList<Integer> countData)
    {
        this.countData=countData;
        //setCountTextViews();
    }*/


    @Override
    public void onDataUpdate()
    {
       // countData=new ArrayList<>();
        //MyGlobals myGlobals=new MyGlobals(getContext());
        //countData=myGlobals.getAllotmentEntriesCount();
        setCountTextViews(getView());
/*
        databaseHelperUser=new DatabaseHelperUser(getContext());
        databaseHelperUser.getAllotment();
*/
//        ((MainActivity) getActivity()).dataUpdated();
        //Toast.makeText(getContext(), "Fragment Total updated!"+countData.get(3), Toast.LENGTH_SHORT).show();
    }
}



