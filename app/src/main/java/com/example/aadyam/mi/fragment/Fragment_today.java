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
import android.widget.Toast;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.interfaces.DataUpdateListener;

import java.util.ArrayList;


public class Fragment_today extends Fragment implements DataUpdateListener
{
    TextView allotted_count_tv, unsafe_count_tv, denied_count_tv, not_available_count_tv, reallotted_unsafe_count_tv, reallotted_denied_count_tv, reallotted_not_available_count_tv, total_count_tv, against_unsafe_count_tv, against_denied_count_tv, against_not_available_count_tv;
    LinearLayout allotted_pending_layout, unsafe_layout, Denied_layout, Not_available_layout, reallotted_unsafe_layout, reallotted_denied_layout, reallotted_not_available_layout, total_layout, against_unsafe_layout, against_denied_layout, against_not_available_layout;
    ProgressDialog progressDialog;
    View view1;
    //public ArrayList<Integer> countData;
    DatabaseHelperUser databaseHelperUser;
    Intent intent;

    public Fragment_today()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_today, container, false);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }



    @Override
    public void onStart()
    {
        super.onStart();
    }


    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        databaseHelperUser=new DatabaseHelperUser(getContext());

        //allowRefresh=true;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait.....");
        final ArrayList<Integer> countData;
        countData=databaseHelperUser.getAllotmentEntriesCount(1);
        allotted_pending_layout = view.findViewById(R.id.today_allotted_layout);
        unsafe_layout = view.findViewById(R.id.today_unsafe_layout);

        Denied_layout = view.findViewById(R.id.today_denied_layout);
        Not_available_layout = view.findViewById(R.id.today_not_available_layout);

        reallotted_unsafe_layout = view.findViewById(R.id.today_reallotted_not_available_layout);
        reallotted_denied_layout = view.findViewById(R.id.today_reallotted_denied_layout);

        reallotted_not_available_layout = view.findViewById(R.id.today_reallotted_not_available_layout);
        total_layout = view.findViewById(R.id.today_total_layout);

        against_unsafe_layout = view.findViewById(R.id.today_against_unsafe_layout);
        against_denied_layout = view.findViewById(R.id.today_against_denied_layout);
        against_not_available_layout = view.findViewById(R.id.today_against_not_available_layout);

        allotted_count_tv = view.findViewById(R.id.today_allotted_count_text);
        unsafe_count_tv = view.findViewById(R.id.today_unsafe_count_text);
        denied_count_tv = view.findViewById(R.id.today_denied_count_text);
        not_available_count_tv = view.findViewById(R.id.today_not_available_count_text);
        reallotted_unsafe_count_tv = view.findViewById(R.id.today_reallotted_unsafe_count_text);
        reallotted_denied_count_tv = view.findViewById(R.id.today_reallotted_denied_count_text);
        reallotted_not_available_count_tv = view.findViewById(R.id.today_reallotted_not_available_count_text);
        total_count_tv = view.findViewById(R.id.today_total_count_text);
        against_unsafe_count_tv = view.findViewById(R.id.today_against_unsafe_count_text);
        against_denied_count_tv = view.findViewById(R.id.today_against_denied_count_text);
        against_not_available_count_tv = view.findViewById(R.id.today_against_not_available_count_text);

        setCountTextViews(countData);

        intent = new Intent(getActivity(), InspectionDisplayActivity.class);

        allotted_pending_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!allotted_count_tv.getText().toString().equals("0"))
                {
//                    Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                    intent.putExtra(Constants.FRAG_TYPE,1);
                    intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_ALLOTTED_PENDING);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "No entries!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        unsafe_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!unsafe_count_tv.getText().toString().equals("0"))
                {
//                    Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                    intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_UNSAFE);
                    intent.putExtra(Constants.FRAG_TYPE,1);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "No entries!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!denied_count_tv.getText().toString().equals("0"))
                {
//                    Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                    intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_DENIED);
                    intent.putExtra(Constants.FRAG_TYPE,1);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "No entries!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),not_available_count_tv,Constants.TOTAL_NOT_AVAILABLE,1);
            }
        });


        reallotted_unsafe_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_unsafe_count_tv,Constants.TOTAL_REALLOTTED_UNSAFE,1);
            }
        });


        reallotted_denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_denied_count_tv,Constants.TOTAL_REALLOTTED_DENIED,1);
            }
        });


        reallotted_not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),reallotted_not_available_count_tv,Constants.TOTAL_REALLOTTED_NOT_AVAILABLE,1);
            }
        });


        total_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),total_count_tv,Constants.TOTAL_TOTAL,1);
            }
        });


        against_unsafe_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_unsafe_count_tv,Constants.TOTAL_AGAINST_UNSAFE,1);
            }
        });


        against_denied_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_denied_count_tv,Constants.TOTAL_AGAINST_DENIED,1);
            }
        });


        against_not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyGlobals(getContext()).changeIntent(getActivity(),against_not_available_count_tv,Constants.TOTAL_AGAINST_NOT_AVAILABLE,1);
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


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).unregisterDataUpdateListener(this);

    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) getActivity()).registerDataUpdateListener(this);
    }

    @Override
    public void onDataUpdate()
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
        ArrayList<Integer>countData=databaseHelperUser.getAllotmentEntriesCount(1);
        setCountTextViews(countData);
    }
}