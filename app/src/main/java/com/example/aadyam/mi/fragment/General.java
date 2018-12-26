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

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.adapter.QuestionAdapter;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */


public class General extends Fragment
{
    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;

    public General()
    {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      /*  recyclerView = (RecyclerView)view.findViewById(R.id.general_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList=new ArrayList<QuestionList>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_general);
//        recyclerView.setAdapter(new QuestionAdapter(questionList,getContext()));
    }
}
