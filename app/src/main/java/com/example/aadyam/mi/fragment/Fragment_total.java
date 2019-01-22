package com.example.aadyam.mi.fragment;

import android.annotation.SuppressLint;
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

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.interfaces.DataUpdateListener;

import java.util.ArrayList;


@SuppressWarnings({"ConstantConditions", "MoveFieldAssignmentToInitializer"})
public class Fragment_total extends Fragment implements DataUpdateListener
{
    private TextView allotted_count_tv;
    private TextView unsafe_count_tv;
    private TextView denied_count_tv;
    private TextView not_available_count_tv;
    private TextView reallotted_unsafe_count_tv;
    private TextView reallotted_denied_count_tv;
    private TextView reallotted_not_available_count_tv;
    private TextView total_count_tv;
    private TextView against_unsafe_count_tv;
    private TextView against_denied_count_tv;
    private TextView against_not_available_count_tv;

    private LinearLayout allotted_pending_layout;
    private LinearLayout unsafe_layout;
    private LinearLayout Denied_layout;
    private LinearLayout Not_available_layout;
    private LinearLayout reallotted_unsafe_layout;
    private LinearLayout reallotted_denied_layout;
    private LinearLayout reallotted_not_available_layout;
    private LinearLayout total_layout;
    private LinearLayout against_unsafe_layout;
    private LinearLayout against_denied_layout;
    private LinearLayout against_not_available_layout;

    private ProgressDialog progressDialog;
    private MyGlobals myGlobals;
    private DatabaseHelperUser databaseHelperUser;
    private Intent intent;


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
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(Constants.TAG, "onStart: ");
    }




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

    }







    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        Log.d(Constants.TAG, "onViewCreated : ");

        myGlobals=new MyGlobals(getContext());
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait.....");
        databaseHelperUser=new DatabaseHelperUser(getContext());

        databaseHelperUser=new DatabaseHelperUser(getContext());
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

        setCountTextViews(databaseHelperUser.getAllotmentEntriesCount(2));

        intent=new Intent(getActivity(),InspectionDisplayActivity.class);

        allotted_pending_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),allotted_count_tv,Constants.TOTAL_ALLOTTED_PENDING,2);
            }
        });



        unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),unsafe_count_tv,Constants.TOTAL_UNSAFE,2);
            }
        });


        Denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new MyGlobals(getContext()).changeIntent(getActivity(),denied_count_tv,Constants.TOTAL_DENIED,2);

            }
        });


        Not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new MyGlobals(getContext()).changeIntent(getActivity(),not_available_count_tv,Constants.TOTAL_NOT_AVAILABLE,2);
            }
        });


        reallotted_unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_unsafe_count_tv,Constants.TOTAL_REALLOTTED_UNSAFE,2);
            }
        });


        reallotted_denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_denied_count_tv,Constants.TOTAL_REALLOTTED_DENIED,2);
            }
        });




        reallotted_not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_not_available_count_tv,Constants.TOTAL_REALLOTTED_NOT_AVAILABLE,2);
            }
        });



        total_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),total_count_tv,Constants.TOTAL_TOTAL,2);
            }
        });


        against_unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_unsafe_count_tv,Constants.TOTAL_AGAINST_UNSAFE,2);
            }
        });



        against_denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_denied_count_tv,Constants.TOTAL_AGAINST_DENIED,2);

            }
        });


        against_not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_not_available_count_tv,Constants.TOTAL_AGAINST_NOT_AVAILABLE,2);

            }
        });

    }


    @SuppressLint({"CutPasteId", "SetTextI18n"})
    private void setCountTextViews(ArrayList<Integer> countData)
    {
        allotted_count_tv.setText(""+ countData.get(0));
        unsafe_count_tv.setText(""+ countData.get(1));
        denied_count_tv.setText(""+ countData.get(2));
        not_available_count_tv.setText(""+ countData.get(3));
        reallotted_unsafe_count_tv.setText(""+ countData.get(4));
        reallotted_denied_count_tv.setText(""+ countData.get(5));
        reallotted_not_available_count_tv.setText(""+ countData.get(6));
        total_count_tv.setText(""+ countData.get(7));
        against_unsafe_count_tv.setText(""+ countData.get(8));
        against_denied_count_tv.setText(""+ countData.get(9));
        against_not_available_count_tv.setText(""+ countData.get(10));
}



    @Override
    public void onDataUpdate()
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
        ArrayList<Integer>countData=databaseHelperUser.getAllotmentEntriesCount(2);
        setCountTextViews(countData);
    }
}



