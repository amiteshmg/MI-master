package com.example.aadyam.mi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;


public class Cylinder extends Fragment
{

    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;
    RadioGroup radioGroup;
    int size;
    Button save,submit;
    Button next;
    TabLayout tabLayout;
    LinearLayout cylinderLayout;
    LinearLayout buttonLayout;
    private SurveyActivity surveyActivity;

    public Cylinder()
    {
        // Required empty public constructor
        //question=new Question();
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

        radioGroup=view.findViewById(R.id.radioGroup);
        //buttonLayout=view.findViewById(R.id.cylinder_button_holder);
        cylinderLayout=view.findViewById(R.id.cylinder_linear_layout);
        //tabLayout=view.findViewById(R.id.tabs1);

        //TODO: GET Radio button value from recycler view and save it to database from putAnswer

        //recyclerView = (RecyclerView)view.findViewById(R.id.cylinder_recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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


   /* @Override
    public void onClick(View button) {
        surveyActivity = new SurveyActivity();
        surveyActivity.setCurrentItem(2, true);
    }*/

}



