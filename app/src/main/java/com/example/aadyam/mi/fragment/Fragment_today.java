package com.example.aadyam.mi.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;


public class Fragment_today extends Fragment {

    TextView allotted_count_tv, unsafe_count_tv, denied_count_tv, not_available_count_tv, reallotted_unsafe_count_tv, reallotted_denied_count_tv, reallotted_not_available_count_tv, total_count_tv, against_unsafe_count_tv, against_denied_count_tv, against_not_available_count_tv;

    LinearLayout allotted_pending_layout, unsafe_layout, Denied_layout, Not_available_layout, reallotted_unsafe_layout, reallotted_denied_layout, reallotted_not_available_layout, total_layout, against_unsafe_layout, against_denied_layout, against_not_available_layout;

    ProgressDialog progressDialog;

    public Fragment_today() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_today, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();


    }



    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //allowRefresh=true;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait.....");

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


        int cnt1 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_ALLOTTED_PENDING).size();
        allotted_count_tv.setText(""+cnt1);


        int cnt2 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_UNSAFE).size();
        unsafe_count_tv.setText(""+cnt2);


        int cnt3 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_DENIED).size();
        denied_count_tv.setText(""+cnt3);



        int cnt4 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_NOT_AVAILABLE).size();
        not_available_count_tv.setText(""+cnt4);


        int cnt5 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_REALLOTTED_UNSAFE).size();
        reallotted_unsafe_count_tv.setText(""+cnt5);


        int cnt6 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_REALLOTTED_DENIED).size();
        reallotted_denied_count_tv.setText(""+cnt6);


        int cnt7 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_REALLOTTED_NOT_AVAILABLE).size();
        reallotted_not_available_count_tv.setText(""+cnt7);


        int cnt8 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_TOTAL).size();
        total_count_tv.setText(""+cnt8);


        int cnt9 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_AGAINST_UNSAFE).size();
        against_unsafe_count_tv.setText(""+cnt9);


        int cnt10 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_AGAINST_DENIED).size();
        against_denied_count_tv.setText(""+cnt10);


        int cnt11 = new MyGlobals(getContext()).getAllotment(Constants.TOTAL_AGAINST_NOT_AVAILABLE).size();
        against_not_available_count_tv.setText(""+cnt11);

//        setCounters();


        //      initializeListCounters(view);
            allotted_pending_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_ALLOTTED_PENDING);
                startActivity(intent);
            }
        });


        unsafe_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_UNSAFE);
                startActivity(intent);
            }
        });


        Denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_DENIED);
                startActivity(intent);
            }
        });


        Not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_NOT_AVAILABLE);
                startActivity(intent);

            }
        });


        reallotted_unsafe_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_REALLOTTED_UNSAFE);
                startActivity(intent);
            }
        });


        reallotted_denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_REALLOTTED_DENIED);
                startActivity(intent);
            }
        });


        reallotted_not_available_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_REALLOTTED_NOT_AVAILABLE);
                intent.putExtra(Constants.FRAG_TYPE,0);
                startActivity(intent);
            }
        });


        total_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_TOTAL);
                startActivity(intent);
            }
        });


        against_unsafe_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_AGAINST_UNSAFE);
                startActivity(intent);
            }
        });


        against_denied_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_AGAINST_DENIED);
                startActivity(intent);

            }
        });


        against_not_available_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InspectionDisplayActivity.class);
                intent.putExtra(Constants.CLICK_CODE, Constants.TOTAL_AGAINST_NOT_AVAILABLE);
                startActivity(intent);
            }
        });
        //  setButtonClicks();


    }
}