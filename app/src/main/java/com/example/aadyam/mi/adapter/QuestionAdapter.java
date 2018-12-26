package com.example.aadyam.mi.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.model.QuestionList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<QuestionList> questionList;
    public Context context;
    public int size=0;





    private static int count=0;
    private String[] answer=new String[1];

    public QuestionAdapter()
    {

    }

    public QuestionAdapter(Context context)
    {
        this.context=context;
    }

    public QuestionAdapter(List<QuestionList> questionList, Context context)
    {
        this.questionList = questionList;
        this.context = context;
    }

    public class MyRadioViewHolder extends RecyclerView.ViewHolder
    {
        TextView radioTextView;
        RadioButton radio_yes,radio_no;
        RadioGroup radioGroup;
        RadioButton checked;



        MyRadioViewHolder(@NonNull View view)
        {
            super(view);

            radioTextView=itemView.findViewById(R.id.radio_question_tv);
            radio_yes=itemView.findViewById(R.id.radio_yes);
            radio_no=itemView.findViewById(R.id.radio_no);
            radioGroup=itemView.findViewById(R.id.radioGroup);



        }
    }

    public class MyEditTextViewHolder extends RecyclerView.ViewHolder
    {
        TextView editTextView;
        EditText editText;

        MyEditTextViewHolder(View view)
        {

            super(view);
            editTextView=itemView.findViewById(R.id.edit_text_question_tv);
            editText=itemView.findViewById(R.id.card_edit_editText);

        }
    }


    public class MySpinnerViewHolder extends RecyclerView.ViewHolder
    {
                Spinner spinner;
                TextView spinTextView;


                MySpinnerViewHolder(@NonNull View view)
                {
                super(view);
                spinner=itemView.findViewById(R.id.spinner);
                spinTextView=itemView.findViewById(R.id.spinner_question_tv);
                }
    }



    @Override
    public int getItemViewType(int position) {



        switch (questionList.get(position).getFieldType())
        {
            case "C":
                return R.layout.card_radio;

            case "T":
                return R.layout.card_edit_text;

            case "D":
                return R.layout.card_spinner;

            default:
                return R.layout.card_edit_text;

        }



}

    //viewType in onCreateViewHolder corresponds to return of getItemViewType
    //first the getItemViewType executes and then the onCreateViewHolder

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //answers=new String[questionList.size()];
        final RecyclerView.ViewHolder viewHolder;
        View view;

        switch(viewType)
        {
            case R.layout.card_radio:
                view=LayoutInflater.from(context).inflate(R.layout.card_radio,parent,false);
                viewHolder=new MyRadioViewHolder(view);
                //can write here code for manipulating this card entry like deleting;
                break;

            case R.layout.card_edit_text:
                view=LayoutInflater.from(context).inflate(R.layout.card_edit_text,parent,false);
                viewHolder=new MyEditTextViewHolder(view);
                //can write here code for manipulating this card entry like deleting;
                break;

            case R.layout.card_spinner:
                view=LayoutInflater.from(context).inflate(R.layout.card_spinner,parent,false);
                viewHolder=new MySpinnerViewHolder(view);
                //can write here code for manipulating this card entry like deleting;
                break;

            case R.layout.card_buttons:
                view=LayoutInflater.from(context).inflate(R.layout.card_buttons,parent,false);
                viewHolder=new MyButtonsViewHolder(view);

                break;

            default:
                view=LayoutInflater.from(context).inflate(R.layout.card_edit_text,parent,false);
                viewHolder=new MyButtonsViewHolder(view);
                //can write here code for manipulating this card entry like deleting;
                break;

        }

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position)
    {
        if(holder instanceof MyRadioViewHolder)
        {

            final QuestionList question=questionList.get(position);
            ((MyRadioViewHolder) holder).radioTextView.setText(question.getDescription());
            ((MyRadioViewHolder) holder).radio_yes.setText(R.string.yes);
            ((MyRadioViewHolder) holder).radio_no.setText(R.string.no);

            final String[] answer = new String[1];

            if(((MyRadioViewHolder) holder).radioGroup.getCheckedRadioButtonId()==-1) {

                ((MyRadioViewHolder) holder).radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int radioButtonID = ((MyRadioViewHolder) holder).radioGroup.getCheckedRadioButtonId();
                        View radioButton = ((MyRadioViewHolder) holder).radioGroup.findViewById(radioButtonID);
                        int idx = ((MyRadioViewHolder) holder).radioGroup.indexOfChild(radioButton);

                        RadioButton r = (RadioButton) ((MyRadioViewHolder) holder).radioGroup.getChildAt(idx);
                        setAnswer(r.getText().toString());
                    }

                });

            }

            else
            {
            Toast.makeText(context, ""+getAnswer(), Toast.LENGTH_SHORT).show();
            }
            /*if(((MyRadioViewHolder) holder).radioGroup.getCheckedRadioButtonId()!=-1)
            {

            }*/



/*

            ((MyRadioViewHolder) holder).radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int radioButtonID = ((MyRadioViewHolder) holder).radioGroup.getCheckedRadioButtonId();
                    View radioButton = ((MyRadioViewHolder) holder).radioGroup.findViewById(radioButtonID);
                    int idx =((MyRadioViewHolder) holder).radioGroup.indexOfChild(radioButton);

                    RadioButton r = (RadioButton) ((MyRadioViewHolder) holder).radioGroup.getChildAt(idx);
                    answer[0] = r.getText().toString();
                    Toast.makeText(context, ""+answer[0], Toast.LENGTH_SHORT).show();
                }
            });
*/


           /* if(((MyRadioViewHolder) holder).radioGroup.getCheckedRadioButtonId()!=-1)
            {
                // Toast.makeText(context, ""+answer[0], Toast.LENGTH_SHORT).show();
                Toast.makeText(context, ""+answer[0], Toast.LENGTH_SHORT).show();
            }*/


            int FRAGMENT_CATEGORY=Integer.parseInt(questionList.get(position).getCategoryId());
            putAnswers(FRAGMENT_CATEGORY, answer[0],questionList.get(position).getQuestionId(),questionList.get(position).getDescription(),questionList.get(position).getCategoryId());


        }


        else if(holder instanceof  MyEditTextViewHolder)
        {
            final QuestionList question=questionList.get(position);
            ((MyEditTextViewHolder) holder).editTextView.setText(question.getDescription());


            ((MyEditTextViewHolder) holder).editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Toast.makeText(context, "OnTextChange : "+((MyEditTextViewHolder) holder).editText.getText().toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Toast.makeText(context, ""+((MyEditTextViewHolder) holder).editText.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            });



        }



        else if(holder instanceof MySpinnerViewHolder)
        {
            final QuestionList question=questionList.get(position);
            String str = question.getFieldData();

            final List<String> spinnerList = Arrays.asList(str.split(","));
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

            ((MySpinnerViewHolder) holder).spinner.setAdapter(adapter);
            ((MySpinnerViewHolder) holder).spinTextView.setText(question.getDescription());



            ((MySpinnerViewHolder) holder).spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {

                    /*if(((MySpinnerViewHolder) holder).spinner.getSelectedItem().toString()!=spinnerList.get(0))
                    {
                        //((MySpinnerViewHolder) holder).spinner.setBackgroundColor(view.getResources().getColor(R.color.red));
                        Toast.makeText(context, "Selected :"+((MySpinnerViewHolder) holder).spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        ((MySpinnerViewHolder) holder).spinner.setBackgroundColor(view.getResources().getColor(R.color.white));
                        Toast.makeText(context, "Selected :"+((MySpinnerViewHolder) holder).spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    }*/
                }



                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage(R.string.spinner_empty_text);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                }
            });



        }




        else if(holder instanceof  MyButtonsViewHolder)
        {
            ((MyButtonsViewHolder) holder).save_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show();
                }
            });




            ((MyButtonsViewHolder) holder).next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();
                }
            });


           // getItemViewType(position-1);
        }
    }

    private void setAnswer(String answer)
    {
        this.answer[0]=answer;
    }

    public String[] getAnswer() {
        return answer;
    }

    @Override
    public int getItemCount()
    {
        if(questionList!=null)
        {
            return questionList.size();
        }

        else
        {
            return 0;
        }
    }






    public void putAnswers(int FRAGMENT_CODE,String answers, String quesId, String quesDescription, String categoryId)
    {
        switch (FRAGMENT_CODE)
        {
                case Constants.CYLINDER_FRAG_CODE:

                DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(context);
                databaseHelperUser.putAnswerEntryInDatabase(FRAGMENT_CODE,answers,quesId,quesDescription,categoryId);
                //String questionId,String questionDescription,String answer,String category,String fieldType
                break;

                case Constants.REGULATOR_FRAG_CODE:



                break;

                case Constants.RUBBER_HOSE_FRAG_CODE:

                break;

                case  Constants.STOVE_FRAG_CODE:

                break;

            case Constants.GENERAL_FRAG_CODE:

                break;

            case Constants.PERSONAL_INFO_FRAG_CODE:

                break;


            case Constants.UPLOAD_PHOTO_FRAG_CODE:

                break;
        }
    }



    private class MyButtonsViewHolder extends RecyclerView.ViewHolder
    {
        Button save_button,next_button;
        MyButtonsViewHolder(View view)
        {

            super(view);
            //save_button=itemView.findViewById(R.id.button_save);
            //next_button=itemView.findViewById(R.id.button_next);




        }
    }


}