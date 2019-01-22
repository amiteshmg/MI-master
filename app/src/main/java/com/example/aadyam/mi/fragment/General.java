package com.example.aadyam.mi.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

/**
 * A simple {@link Fragment} subclass.
 */


public class General extends Fragment
{

    private List<QuestionList> questionList;
    private DatabaseHelperUser databaseHelperUser;
    private LinearLayout generalLayout;

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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        generalLayout=view.findViewById(R.id.general_linear_layout);
        databaseHelperUser=new DatabaseHelperUser(getContext());
        questionList=new ArrayList<QuestionList>();
        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_general);
        MyGlobals myGlobals=new MyGlobals(getContext());
        myGlobals.dynamicQuestion(generalLayout,getContext(),view,questionList);
    }
}
