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
import android.widget.LinearLayout;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Stove extends Fragment {

    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;
    LinearLayout stoveLayout;


    public Stove()
    {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stove, container, false);
    }


    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // recyclerView = (RecyclerView)view.findViewById(R.id.stove_recycler_view);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stoveLayout=view.findViewById(R.id.stove_linear_layout);
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList=new ArrayList<QuestionList>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_stove);
        MyGlobals myGlobals=new MyGlobals(getContext());
        myGlobals.dynamicQuestion(stoveLayout,getContext(),view,questionList);


       // recyclerView.setAdapter(new QuestionAdapter(questionList,getContext()));
    }
}
