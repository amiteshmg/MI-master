package com.example.aadyam.mi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;


public class Cylinder extends Fragment
{
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;
    int size;
    LinearLayout cylinderLayout;

    public Cylinder()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cylinder, container, false);
    }



    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        cylinderLayout=view.findViewById(R.id.cylinder_linear_layout);
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList= new ArrayList<>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_cylinder);
        size=questionList.size();

        MyGlobals myGlobals=new MyGlobals(getContext());
        myGlobals.dynamicQuestion(cylinderLayout, getContext(),view,questionList);

    }


    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume()
    {
        super.onResume();
    }

}



