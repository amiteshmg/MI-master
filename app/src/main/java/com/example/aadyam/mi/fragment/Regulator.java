package com.example.aadyam.mi.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.adapter.QuestionAdapter;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;


public class Regulator extends Fragment {

    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;
    Button save;

    public Regulator() {
        // Required empty public constructor

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regulator, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        save=view.findViewById(R.id.button_save);
        //recyclerView = (RecyclerView)view.findViewById(R.id.regulator_recycler_view);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList=new ArrayList<QuestionList>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_regulator);
        //recyclerView.setAdapter(new QuestionAdapter(questionList,getContext()));
        //recyclerView.setAdapter(new QuestionAdapter(questionObject.getQuestionnaireListResult(),R.layout.card_radio, getContext()));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });





    }
}
