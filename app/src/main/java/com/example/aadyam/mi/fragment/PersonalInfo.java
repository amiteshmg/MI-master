package com.example.aadyam.mi.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.model.QuestionList;

import java.util.Calendar;
import java.util.List;

public class PersonalInfo extends Fragment {

    RecyclerView recyclerView;
    List<QuestionList> questionList;
    DatabaseHelperUser databaseHelperUser;
    private EditText date;

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


      /*  date=view.findViewById(R.id.card_edit_DOB);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            }

        };

        date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
*/
        //recyclerView = (RecyclerView)view.findViewById(R.id.personal_info_recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        databaseHelperUser=new DatabaseHelperUser(getContext());
//        questionList=new ArrayList<QuestionList>();
//        questionList = databaseHelperUser.getQuestionEntries(R.layout.fragment_personal_info);
       // recyclerView.setAdapter(new QuestionAdapter(questionList,R.layout.card_radio,getContext()));
    }



    private void updateDateText()
    {

    }
}
