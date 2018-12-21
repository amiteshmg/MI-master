package com.example.aadyam.mi.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aadyam.mi.Database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.model.QuestionList;

import java.util.List;

public class PersonalInfo extends Fragment {

    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;

    public PersonalInfo() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_personal_info, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recyclerView = (RecyclerView)view.findViewById(R.id.personal_info_recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        databaseHelperUser=new DatabaseHelperUser(getContext());
//        questionList=new ArrayList<QuestionList>();
//        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_personal_info);
       // recyclerView.setAdapter(new QuestionAdapter(questionList,R.layout.card_radio,getContext()));
    }
}
