package com.example.aadyam.mi.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aadyam.mi.Database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.adapter.QuestionAdapter;
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


    public Cylinder()
    {
        // Required empty public constructor
        //question=new Question();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cylinder, container, false);
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        radioGroup=view.findViewById(R.id.radioGroup);
        tabLayout=view.findViewById(R.id.tabs1);

        //TODO: GET Radio button value from recycler view and save it to database from putAnswer

        recyclerView = (RecyclerView)view.findViewById(R.id.cylinder_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList= new ArrayList<>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_cylinder);
        size=questionList.size();
        recyclerView.setAdapter(new QuestionAdapter(questionList,getContext()));
        save=view.findViewById(R.id.button_save);
        next=view.findViewById(R.id.button_next);


        //Log.i("CylinderData :",Item);
        //Toast.makeText(getContext(), "Cylinder - "+consumerName, Toast.LENGTH_SHORT).show();




/*
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                TabLayout.Tab tab = tabLayout.getTabAt(2);
                assert tab != null;
                tab.select();
            }
        });*/


        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                saveAnswers();

                new SurveyActivity().onClickSaveCylinderDetails();
                //new DatabaseHelperUser(getContext()).addAnswerEntryInDatabase(Constants.CYLINDER_FRAG_CODE,map);


                //new QuestionAdapter().updateAnswer(Constants.CYLINDER_FRAG_CODE);

               // databaseHelperUser.putAnswers();
                //Toast.makeText(getContext(), "saved Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }









    private void saveAnswers()
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
        //int questionId,String questionDescription,String answer,String areaName,String consumerName, String consumerNo,String UniqueConumerId,String AllottedId,String category,String fieldType





        for(int i=0;i<size;i++)
        {
            String consumerName = getActivity().getIntent().getExtras().getString(Constants.CONSUMER_NAME);

            String ConsumerNo = getActivity().getIntent().getExtras().getString(Constants.CONSUMER_NO);

            String UniqueConsumerNo = getActivity().getIntent().getExtras().getString(Constants.UNIQUE_CONSUMER_NO);

            String AllottedDate =  getActivity().getIntent().getExtras().getString(Constants.ALLOTMENT_DATE);

            String AllottedId = getActivity().getIntent().getExtras().getString(Constants.ALLOTED_ID);

            String AreaName = getActivity().getIntent().getExtras().getString(Constants.AREA_NAME);

            //String answer =getActivity().getIntent().getExtras().getString(Constants.ANSWER);

            //String questionId,String questionDescription,String answer,String category,String fieldType


            databaseHelperUser.putAnswers(AreaName,consumerName,ConsumerNo,UniqueConsumerNo,AllottedId);


        }

        Toast.makeText(getContext(), "saved successfully!", Toast.LENGTH_SHORT).show();


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



