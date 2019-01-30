package com.example.aadyam.mi.Global;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aquery.AQuery;
import com.aquery.listener.QueryNetworkListener;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.InspectionDisplayActivity;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.QuestionList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyGlobals
{
    private Context mContext;
    private String[] answer,questionDescription,categoryID,questionID,informationAnswer;
    private LinearLayout.LayoutParams params;
    private int size;
    private SharedPreferences sharedPreferences;
    private String hoseExpiryDate;


    // constructor
    public MyGlobals(Context context)
    {
        this.mContext = context;
    }


    public boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni == null)
        {
            // There are no active networks.
            return false;
        }

        else
            return true;
    }





   /* public String readJSONFromAsset(String FileName)
    {
        String json;
        try
        {
            InputStream is = mContext.getResources().getAssets().open(FileName+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
*/




/*
    public AlertDialog.Builder dialogCreator(Context context,String msg,String positiveButtonLabel)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(context);

        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return  builder;
    }*/





    /*public List<AllotmentList> getAllotment(int CLICK_CODE)
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(mContext);
        List<AllotmentList> list = databaseHelperUser.getAllotmentEntries(CLICK_CODE);
        return list;
    }
*/


/*public ArrayList<Integer> getAllotmentEntriesCount()
{
    DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(mContext);
    ArrayList<Integer> countData=new ArrayList<>();

    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_ALLOTTED_PENDING).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_UNSAFE).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_DENIED).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_NOT_AVAILABLE).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_UNSAFE).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_DENIED).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_REALLOTTED_NOT_AVAILABLE).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_TOTAL).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_UNSAFE).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_DENIED).size());
    countData.add(databaseHelperUser.getAllotmentEntries(Constants.TOTAL_AGAINST_NOT_AVAILABLE).size());

    return  countData;
}*/

    //method to abstract allotmentList from the retrofit body and pass to addQuestion method one by one
  /*  public int getAllotmentCount()
    {
        final int[] size = new int[1];
        ApiInterface apiInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        //AllotmentList?UserId=8193&StaffRefNo=11710819300000002&ConsumerCount=0
        Call<Allotment> call = apiInterface.getAllotmentList();

        call.enqueue(new Callback<Allotment>()
        {
            @Override
            public void onResponse(@NonNull Call<Allotment> call, @NonNull Response<Allotment> response) {

                List<AllotmentList> allotmentLists;
                allotmentLists = new ArrayList<>();

                System.out.println(" DEMO " + response.body().getAllotmentListResult().get(1).getAddress());


                assert response.body() != null;

                 size[0] = response.body().getAllotmentListResult().size();

            }


            @Override
            public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t)
            {
                //Toast.makeText(, "onFailure : Internal fail:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR", t.getMessage());
                // Toast.makeText(InspectionDisplayActivity.this, "NO "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        return size[0];
    }


*/



   /* public void getJSON()
    {
        JSONObject student1 = new JSONObject();
        try
        {
            student1.put("id", "3");
            student1.put("name", "NAME OF STUDENT");
            student1.put("year", "3rd");
            student1.put("curriculum", "Arts");
            student1.put("birthday", "5/5/1993");
        }


        catch (JSONException e)
        {
            e.printStackTrace();
        }


        JSONObject student2 = new JSONObject();
        try
        {
            student2.put("id", "2");
            student2.put("name", "NAME OF STUDENT2");
            student2.put("year", "4rd");
            student2.put("curriculum", "scicence");
            student2.put("birthday", "5/5/1993");
            }

            catch (JSONException e)
            {
            e.printStackTrace();
            }


        JSONArray jsonArray = new JSONArray();

        jsonArray.put(student1);
        jsonArray.put(student2);

        JSONObject studentsObj = new JSONObject();

        try
        {
            studentsObj.put("Students", jsonArray);
        }


        catch (JSONException e)
        {
            e.printStackTrace();
        }


        String jsonStr = studentsObj.toString();
        System.out.println("jsonString: "+jsonStr);
    }
*/




    //Convert pixels to density independent pixels
    public int pxToDp(int px,Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }




    //Convert density independent pixels to pixels
    private int dpToPx(int dp, Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }






  /*  @SuppressLint({"ResourceType", "NewApi"})
    public void dynamicUserInformation(LinearLayout fragmentLinearLayout, final Context context, View v,final List<PersonalInfoList> personalInfoList) throws JSONException
    {

        //personalInfoList = new Gson().fromJson(readJSONFromAsset("questionData"), new TypeToken<List<PersonalInfoList>>(){}.getType());

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int size= personalInfoList.size();

        informationAnswer = new String[size];
        informationCategoryID=new int[size];
        informationDescription=new String[size];
        informationId=new int[size];


        for (int i=0;i<personalInfoList.size();i++)
        {

            final LinearLayout informationHolderLinearLayout = new LinearLayout(context);

            int px=dpToPx(10,context);
            informationHolderLinearLayout.setLayoutParams(params);

            if(personalInfoList.get(i).getFieldType().equals("I"))
            {
                informationHolderLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            }

            else
                {
                    informationHolderLinearLayout.setOrientation(LinearLayout.VERTICAL);
                }


            informationHolderLinearLayout.setPadding(px,px,px,px);
            informationHolderLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.rectangle));

            final TextView information = new TextView(context);
            information.setText(personalInfoList.get(i).getDescription());
            //adding question txt to questionHolderLayout
            informationHolderLinearLayout.addView(information);


            if(personalInfoList.get(i).getFieldType().equals("E"))
            {
                final EditText editText=new EditText(context);


                if(personalInfoList.get(i).getSubFieldType().equals("date"))
                {
                    popUpDatePicker(context,editText);
                }

                else if(personalInfoList.get(i).getSubFieldType().equals("phone"))
                {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }

                else if(personalInfoList.get(i).getSubFieldType().equals("numberDecimal"))
                {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }

            //editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            informationHolderLinearLayout.addView(editText);
            final int finalJ1 = i;

            editText.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {


                }


                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {


                }

                @Override
                public void afterTextChanged(Editable s)
                {

                *//*
                 * Active : true
                 * CategoryId : 1
                 * Description : Mobile number
                 * FieldData : null
                 * FieldType : E
                 * SubFieldType : phone
                 * InformationId : 1
                 * isCompulsory : 0
                 *//*

                informationAnswer[finalJ1] = editText.getText().toString();
                informationId[finalJ1] = personalInfoList.get(finalJ1).getInformationId();
                informationCategoryID[finalJ1] = personalInfoList.get(finalJ1).getCategoryId();
                informationDescription[finalJ1] = personalInfoList.get(finalJ1).getDescription();

                }
        });

        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        editText.setOnFocusChangeListener(ofcListener);
    }


    else if (personalInfoList.get(i).getFieldType().equals("S"))
    {
        final Spinner spinner=new Spinner(context);

        String str = personalInfoList.get(i).getFieldData();
        final List<String> spinnerList = Arrays.asList(str.split(","));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        informationHolderLinearLayout.addView(spinner);

        final int finalJ2 = i;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if(position==0)
                {
                    informationAnswer[finalJ2]=null;
                }


                else
                {
                    informationAnswer[finalJ2] = spinner.getSelectedItem().toString();
                    informationId[finalJ2] = personalInfoList.get(finalJ2).getInformationId();
                    informationCategoryID[finalJ2] = personalInfoList.get(finalJ2).getCategoryId();
                    informationDescription[finalJ2] = personalInfoList.get(finalJ2).getDescription();
                    //Toast.makeText(context, "" + answer[finalJ2], Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }



    else if (personalInfoList.get(i).getFieldType().equals("C"))
    {
        final PersonalInfoList personalInfoList1=personalInfoList.get(i);
        String str = personalInfoList1.getFieldData();

        List<String> checkboxList = Arrays.asList(str.split(","));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, checkboxList);

        final CheckBox[] checkBox=new CheckBox[checkboxList.size()];


                for (final int j = 0; i < checkboxList.size(); i++)
                {
                checkBox[j] = new CheckBox(context);
                checkBox[j].setText(checkboxList.get(j));
                informationAnswer[j]=null;
                informationHolderLinearLayout.addView(checkBox[j]);


                checkBox[j].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    informationAnswer[j]+= checkBox[j].getText().toString();
                    informationId[j] = personalInfoList.get(j).getInformationId();
                    informationCategoryID[j] = personalInfoList.get(j).getCategoryId();
                    informationDescription[j] = personalInfoList.get(j).getDescription();
                }
            });

        }

    }


    else if (personalInfoList.get(i).getFieldType().equals("I"))
    {
        //create a button similarly as above,and add it to the layout
        ImageView imageView=new ImageView(context);
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.vector_add_image));

        informationHolderLinearLayout.addView(imageView);


    }

    fragmentLinearLayout.addView(informationHolderLinearLayout);

}

        Button save=new Button(context);
        Button next =new Button(context);
        Button prev=new Button(context);

        int pad=dpToPx(5,context);
        next.setWidth(dpToPx(50,context));

        next.setHeight(dpToPx(30,context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            next.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        }

        next.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        next.setPadding(pad,pad,pad,pad);
        next.setText(R.string.next);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            save.setBackground(context.getResources().getDrawable(R.drawable.blue_rectangle));
        }

        save.setWidth(60);
        save.setHeight(30);
        save.setTextColor(context.getResources().getColor(R.color.white));
        save.setText(R.string.save);

        prev.setHeight(dpToPx(30,context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            prev.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        }
        prev.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        prev.setPadding(pad,pad,pad,pad);
        prev.setText(R.string.prev);


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personalInfoList.get(0).getCategoryId()>0)
                    ((SurveyActivity)context).setCurrentItem(personalInfoList.get(0).getCategoryId()-1, true);
            }
        });


        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(context);

                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

                String areaName = sharedPreferences.getString(Constants.AREA_NAME, "0");
                String consumerName = sharedPreferences.getString(Constants.CONSUMER_NAME, "0");
                String consumerNo = sharedPreferences.getString(Constants.CONSUMER_NO, "0");
                String uniqueConsumerId = sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO, "0");
                String allottedId = sharedPreferences.getString(Constants.ALLOTED_ID, "0");
                String allotmentDate = sharedPreferences.getString(Constants.ALLOTMENT_DATE, "0");
                String isCompleted = sharedPreferences.getString(Constants.IS_COMPLETED, "0");

                //int count=0;

              *//*  for(int i=0;i<personalInfoList.size();i++)
                {

                    if(answer[i]!=null)
                    {
                        count++;
                    }

                }*//*

                String mobileNo;

                    for(int j=0;j<personalInfoList.size();j++)
                    {

                        //databaseHelperUser.putAnswerEntryInDatabase(answer[j], questionID[j], questionDescription[j], categoryID[j], allotmentDate, areaName, consumerName, consumerNo, isCompleted, uniqueConsumerId, allottedId);
                        if(personalInfoList.get(j).getInformationId()==1)
                        {
                            mobileNo=personalInfoList.get(j).getMobileNo();
                        }

                       // databaseHelperUser.putInformationEntryInDatabase(informationAnswer[j], informationCategoryID[j], informationDescription[j], informationCategoryID[j], allotmentDate, areaName, consumerName, consumerNo, isCompleted, uniqueConsumerId, allottedId);

                    }

                    Toast.makeText(context, "Saved SuccessFully", Toast.LENGTH_SHORT).show();
                    ((SurveyActivity)context).setCurrentItem(7, true);




                    Toast.makeText(context, "Answer all questions ", Toast.LENGTH_SHORT).show();

            }

        });



        LinearLayout buttonLayout=new LinearLayout(context);
        buttonLayout.setPadding(pad,pad,pad,pad);

        buttonLayout.setLayoutParams(params);
        buttonLayout.addView(prev);
        buttonLayout.addView(save);
        buttonLayout.addView(next);

        fragmentLinearLayout.addView(buttonLayout);

    }


    public List<PersonalInfoList> getPersonalInfoList(String Filename)
    {
        List<PersonalInfoList> list;
        list=new Gson().fromJson(readJSONFromAsset(Filename), new TypeToken<List<PersonalInfoList>>(){}.getType());

        return list;
    }
*/

















//--------------------------------------DYNAMIC QUESTION------------------------------------------------

    //Universal function to display all questions and save the same to Database
    @SuppressLint({"NewApi", "ApplySharedPref"})
    public void dynamicQuestion(LinearLayout fragmentLinearLayout, final Context context, final List<QuestionList> questionList,boolean unsafeQuestionFlag)
    {

        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);

         answer = new String[questionList.size()];
         questionID=new String[questionList.size()];
         categoryID=new String[questionList.size()];
         questionDescription=new String[questionList.size()];
         int size= questionList.size();
        
         params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
         params.gravity=Gravity.CENTER_HORIZONTAL;

         /*final SharedPreferences.Editor edit = sharedPreferences.edit();
         edit.putInt(Constants.ARRAY_LENGTH, questionList.size());
*/
         for (int j = 0; j <size; j++)
        {
            int px=dpToPx(10,context);
            params.setMargins(px,px,px,px);
            LinearLayout questionHolderLinearLayout = new LinearLayout(context);

            questionHolderLinearLayout.setLayoutParams(params);
            questionHolderLinearLayout.setOrientation(LinearLayout.VERTICAL);
            questionHolderLinearLayout.setPadding(px,px,px,px);
            questionHolderLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.rectangle));

            final TextView question = new TextView(context);
            question.setText(questionList.get(j).getDescription());

            //adding question txt to questionHolderLayout
            questionHolderLinearLayout.addView(question);

            //radioGroup condition
            switch (questionList.get(j).getFieldType())
            {
                case "C":
                    final AppCompatRadioButton[] rb = new AppCompatRadioButton[2];

                    final RadioGroup rg = new RadioGroup(context); //create the RadioGroup

                    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL

                    String[] options = context.getResources().getStringArray(R.array.radio_options_yes_no);

                    for (int i = 0; i < options.length; i++) {
                        rb[i] = new AppCompatRadioButton(context);
                        rb[i].setText(options[i]);
                        rb[i].setId(i + 100);
                        rb[i].setHighlightColor(context.getResources().getColor(R.color.colorPrimary));
                        rg.addView(rb[i]);
                    }

                    if (questionList.get(j).getQuestionId().matches("3|16|24")) {
                        rb[1].setHighlightColor(context.getResources().getColor(R.color.red));
                        rb[1].setTextColor(context.getResources().getColor(R.color.red));
                    }


                    //for yes as unsafe option
                    else if (questionList.get(j).getQuestionId().matches("21|23|30|32")) {
                        rb[0].setHighlightColor(context.getResources().getColor(R.color.red));
                        rb[0].setTextColor(context.getResources().getColor(R.color.red));
                    }

                    questionHolderLinearLayout.addView(rg);

                    //unsafe questions category
                    //for No as unsafe option
                    final int finalJ = j;

                    // no radio buttons are checked
                    rg.setOnCheckedChangeListener((group, checkedId) -> {
                        int radioButtonID = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(radioButtonID);
                        int idx = rg.indexOfChild(radioButton);
                        AppCompatRadioButton r = (AppCompatRadioButton) rg.getChildAt(idx);

                        if (questionList.get(finalJ).getQuestionId().matches("3|16|24")) {
                            if (r.getText().toString().equalsIgnoreCase("no")) {
                                question.setTextColor(context.getResources().getColor(R.color.red));
                                r.setHighlightColor(context.getResources().getColor(R.color.red));
                                sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ).getQuestionId(), 1).commit();
                            } else {
                                question.setTextColor(Color.BLACK);
                                sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ).getQuestionId(), 0).commit();
                                //r.setHighlightColor(context.getResources().getColor(R.color.dark_blue));
                            }
                        } else if (questionList.get(finalJ).getQuestionId().matches("21|23|30|32")) {
                            if (r.getText().toString().equalsIgnoreCase("yes")) {
                                question.setTextColor(context.getResources().getColor(R.color.red));
                                r.setHighlightColor(context.getResources().getColor(R.color.red));
                                //r;
                                sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ).getQuestionId(), 1).commit();
                            } else {
                                question.setTextColor(Color.BLACK);
                                sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ).getQuestionId(), 0).commit();
                            }
                        }

                        answer[finalJ] = r.getText().toString();
                        questionID[finalJ] = questionList.get(finalJ).getQuestionId();
                        categoryID[finalJ] = questionList.get(finalJ).getCategoryId();
                        questionDescription[finalJ] = questionList.get(finalJ).getDescription();
                    });
                    break;


                //textFields
                case "T":
                    final EditText editText = new EditText(context);
                    if (questionList.get(j).getQuestionId().equals("35")) {
                        popUpDatePicker(context, editText);
                    }

                    if (questionList.get(j).getQuestionId().equals("17")) {
                        if (hoseExpiryDate != null) {
                            editText.setText(hoseExpiryDate);
                            editText.setFocusable(false);
                        } else {
                            popUpDatePicker(context, editText);
                        }
                    }

                    questionHolderLinearLayout.addView(editText);
                    final int finalJ1 = j;

                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            answer[finalJ1] = editText.getText().toString();
                            questionID[finalJ1] = questionList.get(finalJ1).getQuestionId();
                            categoryID[finalJ1] = questionList.get(finalJ1).getCategoryId();
                            questionDescription[finalJ1] = questionList.get(finalJ1).getDescription();
                        }
                    });

                    View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
                    editText.setOnFocusChangeListener(ofcListener);
                    break;

                //listViews
                case "D":

                    final Spinner spinner = new Spinner(context);
                    String str = context.getResources().getString(R.string.spinner_default_item) + questionList.get(j).getFieldData();
                    final List<String> spinnerList = Arrays.asList(str.split(","));

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(adapter);
                    questionHolderLinearLayout.addView(spinner);

                    final int finalJ2 = j;


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @SuppressLint("ApplySharedPref")
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            if (questionList.get(finalJ2).getQuestionId().equals("13")) {
                                if (spinner.getSelectedItem().toString().equalsIgnoreCase("rubber")) {
                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                    alertDialog.setMessage("Do you want to change the Rubber Hose?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(context);
                                            final EditText input = new EditText(context);
                                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.MATCH_PARENT);
                                            input.setLayoutParams(lp);
                                            new MyGlobals(context).popUpDatePicker(context, input);
                                            alertDialog1.setView(input).setTitle("Suraksha Hose Expiry date").setMessage("New Suraksha hose expiry date").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    hoseExpiryDate = input.getText().toString();
                                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                                    answer[finalJ2] = spinner.getSelectedItem().toString();
                                                    questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                                    categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                                    questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                                    sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ2).getQuestionId(), 0).commit();
                                                    Toast.makeText(context, "" + input.getText().toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            }).show();

                                        }

                                    }).setNegativeButton("No", (dialog, which) -> {
                                        spinner.setBackgroundColor(context.getResources().getColor(R.color.red));
                                        answer[finalJ2] = spinner.getSelectedItem().toString();
                                        questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                        categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                        questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                        sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ2).getQuestionId(), 1).commit();
                                        dialog.dismiss();
                                    }).show();
                                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("Select one")) {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = null;
                                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("Suraksha Hose")) {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = spinner.getSelectedItem().toString();
                                    questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                    categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                    questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                    sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ2).getQuestionId(), 0).commit();
                                }


                            } else if (questionList.get(finalJ2).getQuestionId().equals("10")) {
                                if (/*position==0*/spinner.getSelectedItem().toString().matches("select one|Select one|Select One|SELECT ONE")) {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = null;
                                }
//
                                else if (spinner.getSelectedItem().toString().matches("hpcl|Hpcl|HPCL")) {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = spinner.getSelectedItem().toString();
                                    questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                    categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                    questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                    sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ2).getQuestionId(), 0).commit();
                                } else {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.red));
                                    answer[finalJ2] = spinner.getSelectedItem().toString();
                                    questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                    categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                    questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                    sharedPreferences.edit().putInt(Constants.ANSWER + questionList.get(finalJ2).getQuestionId(), 1).commit();
                                }
                            } else {

                                if (position == 0)
                                {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = null;
                                } else {
                                    spinner.setBackgroundColor(context.getResources().getColor(R.color.white));
                                    answer[finalJ2] = spinner.getSelectedItem().toString();
                                    questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                                    categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                                    questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                                }
                            }
                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                        {

                        }
                    });
                    break;
            }

            fragmentLinearLayout.addView(questionHolderLinearLayout);

        }



        Button save=new Button(context);
        Button next =new Button(context);
        Button prev=new Button(context);


        int pad=dpToPx(5,context);
        next.setWidth(dpToPx(50,context));

        next.setHeight(dpToPx(30,context));
        next.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        next.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        next.setPadding(pad,pad,pad,pad);
        next.setText(R.string.next);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        save.setBackground(context.getResources().getDrawable(R.drawable.blue_rectangle));
        save.setLayoutParams(lp);
        save.setTextColor(context.getResources().getColor(R.color.white));
        save.setText(R.string.saveAndProceed);
        save.setPadding(pad,pad,pad,pad);


        prev.setHeight(dpToPx(30,context));
        prev.setLayoutParams(lp);
        prev.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        prev.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        prev.setPadding(pad,pad,pad,pad);
        prev.setText(R.string.prev);

        if(!unsafeQuestionFlag)
        {

            prev.setOnClickListener(v -> {
                if (Integer.parseInt(questionList.get(0).getCategoryId()) > 0) {
                    int fragPrev = Integer.parseInt(questionList.get(0).getCategoryId());
                    ((SurveyActivity) context).setCurrentItem(fragPrev - 2, true);
                }
            });


            save.setOnClickListener(v -> {
                DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(context);
                SharedPreferences sharedPreferences1 = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
                String areaName = sharedPreferences1.getString(Constants.AREA_NAME, "0");
                String consumerName = sharedPreferences1.getString(Constants.CONSUMER_NAME, "0");
                String consumerNo = sharedPreferences1.getString(Constants.CONSUMER_NO, "0");
                String uniqueConsumerId = sharedPreferences1.getString(Constants.UNIQUE_CONSUMER_NO, "0");
                String allottedId = sharedPreferences1.getString(Constants.ALLOTED_ID, "0");
                String allotmentDate = sharedPreferences1.getString(Constants.ALLOTMENT_DATE, "0");
                String isCompleted = sharedPreferences1.getString(Constants.IS_COMPLETED, "0");

                int count = 0;

                for (int i = 0; i < size; i++) {
                    if (answer[i] != null) {
                        count++;
                    }
                }

                sharedPreferences1.edit().putInt(Constants.CYLINDER_SAVE, 0).commit();

                if (count == size)
                {
                    int fragmentType = Integer.parseInt(questionList.get(0).getCategoryId());

                    databaseHelperUser.putAnswerEntryInDatabase(false,answer, questionID, questionDescription, categoryID, allotmentDate, areaName, consumerName, consumerNo, isCompleted, uniqueConsumerId, allottedId);
                    databaseHelperUser.setFragmentStatusSaved(allottedId, Integer.parseInt(questionList.get(0).getCategoryId()));

                    Toast.makeText(context, "Saved SuccessFully", Toast.LENGTH_SHORT).show();

                    sharedPreferences.edit().putBoolean(Constants.UNSAFE_FLAG,false).commit();

                    ((SurveyActivity) context).setCurrentItem(Integer.parseInt(questionList.get(0).getCategoryId()), true);

                }

                else
                    {
                        Toast.makeText(context, "Answer all questions ", Toast.LENGTH_SHORT).show();
                    }
            });


            next.setOnClickListener(v -> ((SurveyActivity) context).setCurrentItem(Integer.parseInt(questionList.get(0).getCategoryId()), true));
        }


        else
            {
                prev.setOnClickListener(v -> {

                });


                    save.setOnClickListener(v -> {
                    DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(context);
                    String areaName = sharedPreferences.getString(Constants.AREA_NAME, "0");
                    String consumerName = sharedPreferences.getString(Constants.CONSUMER_NAME, "0");
                    String consumerNo = sharedPreferences.getString(Constants.CONSUMER_NO, "0");
                    String uniqueConsumerId = sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO, "0");
                    String allottedId = sharedPreferences.getString(Constants.ALLOTED_ID, "0");
                    String allotmentDate = sharedPreferences.getString(Constants.ALLOTMENT_DATE, "0");
                    String isCompleted = sharedPreferences.getString(Constants.IS_COMPLETED, "0");
                            int[] unsafeIdArray=new int[questionList.size()];
                            int [] unsafeValuesArray=new int[questionList.size()];

                            int unsafeCount = 0;
                            int count=0;

                            for(int i=0;i<size;i++)
                            {

                                unsafeIdArray[i]=Integer.parseInt(questionList.get(i).getQuestionId());
                                unsafeValuesArray[i]= sharedPreferences.getInt(Constants.ANSWER+unsafeIdArray[i],0);

                                if(sharedPreferences.getInt(Constants.ANSWER+unsafeIdArray[i],0)==0 && answer[i]!=null)
                                {
                                    unsafeCount++;
                                }

                                if (answer[i] != null)
                                {
                                    count++;
                                }

                            }




                           /* for(int i=0;i<unsafeIdArray.length;i++)
                            {
                            */




                  /*  int count = 0;

                    for (int i = 0; i < questionList.size(); i++) {

                    }*/

                    sharedPreferences.edit().putInt(Constants.CYLINDER_SAVE, 0).commit();


                    if (unsafeCount == size)
                    {
                        databaseHelperUser.putAnswerEntryInDatabase(true,answer, questionID, questionDescription, categoryID, allotmentDate, areaName, consumerName, consumerNo, isCompleted, uniqueConsumerId, allottedId);
                        Toast.makeText(context, "Saved SuccessFully", Toast.LENGTH_SHORT).show();

                        sharedPreferences.edit().putBoolean(Constants.UNSAFE_FLAG,true).commit();

                        ((SurveyActivity) context).setCurrentItem(1, true);
                        //int safeAnswerCount=0;

                        /* for (int j = 0; j < questionList.size(); j++)
                        {
                            if(questionID[j].matches("21|23|30|32") && answer[j].matches("no|No|NO") ||questionID[j].matches("3|16|24") && answer[j].matches("yes|Yes|YES"))
                            {
                                             safeAnswerCount++;
                            }
                        }

                        if(safeAnswerCount==questionList.size())
                        {
                            Toast.makeText(context, "Saved SuccessFully", Toast.LENGTH_SHORT).show();
                            ((SurveyActivity) context).setCurrentItem(1, true);

                        }*/
                    }

                    else if(count!=size)
                    {
                        Toast.makeText(context, "Answer all questions ", Toast.LENGTH_SHORT).show();
                    }


                        else
                            {
                                Toast.makeText(context, "Still Unsafe! Cannot Save", Toast.LENGTH_SHORT).show();
                            }
                });


                next.setOnClickListener(v -> ((SurveyActivity) context).setCurrentItem(Integer.parseInt(questionList.get(0).getCategoryId()), true));
        }

        LinearLayout buttonLayout=new LinearLayout(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity=Gravity.CENTER;

        buttonLayout.setPadding(pad,pad,pad,pad);
        buttonLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        buttonLayout.setLayoutParams(params);
        if(questionList.get(0).getCategoryId()=="1" && !unsafeQuestionFlag)
        buttonLayout.addView(prev);
        buttonLayout.addView(save);
        //buttonLayout.addView(next);

        fragmentLinearLayout.addView(buttonLayout);

    }



    public void popUpDatePicker(final Context context, final EditText editText)
    {
        editText.setFocusable(false);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) ->
        {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            editText.setText(sdf.format(myCalendar.getTime()));
        };


        editText.setOnClickListener(v -> {
            new DatePickerDialog(context, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }




    public void setIsInMobileFlag(String allotted_id)
    {
        AQuery aQuery = new AQuery(mContext);
        String url = Constants.InspCompletedFlagInMobile + "AllotmentId=" +allotted_id+   "&" + "IsCompleteFlag=" + "1";
        Date currentTime = Calendar.getInstance().getTime();
        String date = currentTime.toString();

        aQuery.ajax(url).get().response(new QueryNetworkListener()
        {
            @Override
            public void response(String s, Throwable throwable)
            {
                Toast.makeText(mContext, "AJAX response"+s, Toast.LENGTH_SHORT).show();
            }
        });
    }




    public String getCurrentDate()
    {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
       return dateFormat.format(date);

    }

    public void changeIntent(FragmentActivity activity,TextView textView, int LAYOUT_TYPE_BLOCK, int FRAG_CODE)
    {

        if(!textView.getText().toString().equals("0"))
        {
            Intent intent=new Intent(activity, InspectionDisplayActivity.class);
            intent.putExtra(Constants.CLICK_CODE, LAYOUT_TYPE_BLOCK);
            intent.putExtra(Constants.FRAG_TYPE,FRAG_CODE);
            activity.startActivity(intent);
        }

        else
        {
            Toast.makeText(activity, "No entries!", Toast.LENGTH_SHORT).show();
        }
    }


    private class MyFocusChangeListener implements View.OnFocusChangeListener
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            if(!hasFocus)
            {
                InputMethodManager imm =  (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

}








