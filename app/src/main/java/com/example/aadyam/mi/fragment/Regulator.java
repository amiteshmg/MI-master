package com.example.aadyam.mi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;


public class Regulator extends Fragment {

    RecyclerView recyclerView;
    private List<QuestionList> questionList;
    private DatabaseHelperUser databaseHelperUser;
    Button save;
    private LinearLayout regulatorLayout;

    public Regulator() {
        // Required empty public constructor

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regulator, container, false);
    }


    @SuppressLint("NewApi")

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        regulatorLayout=view.findViewById(R.id.regulator_linear_layout);
        //recyclerView = (RecyclerView)view.findViewById(R.id.regulator_recycler_view);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList=new ArrayList<QuestionList>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_regulator);
        MyGlobals myGlobals=new MyGlobals(getContext());
        myGlobals.dynamicQuestion(regulatorLayout,getContext(),questionList,false);
        //recyclerView.setAdapter(new QuestionAdapter(questionList,getContext()));
        //recyclerView.setAdapter(new QuestionAdapter(questionObject.getQuestionnaireListResult(),R.layout.card_radio, getContext()));






    }
}
