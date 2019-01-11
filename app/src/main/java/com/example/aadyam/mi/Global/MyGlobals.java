package com.example.aadyam.mi.Global;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.CameraUtils;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.activity.SurveyActivity;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.PersonalInfoList;
import com.example.aadyam.mi.model.QuestionList;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.aadyam.mi.Utils.Constants.BITMAP_SAMPLE_SIZE;
import static com.example.aadyam.mi.Utils.Constants.KEY_IMAGE_STORAGE_PATH;
import static com.example.aadyam.mi.Utils.Constants.MEDIA_TYPE_IMAGE;
import static com.example.aadyam.mi.Utils.Constants.imageStoragePath;


public class MyGlobals
{
    private Context mContext;
    private String[] answer,questionDescription,categoryID,questionID,informationAnswer;

    private int[] informationId;
    private int size;
    private int[] informationCategoryID;
    private String[] informationDescription;
    SharedPreferences sharedPreferences;


    // constructor
    public MyGlobals(Context context)
    {
        this.mContext = context;
    }

    public String getUserName()
    {
        return "test";
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





    public String readJSONFromAsset(String FileName)
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
    }





    public List<AllotmentList> getAllotment(int CLICK_CODE)
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(mContext);
        List<AllotmentList> list = databaseHelperUser.getAllotmentEntries(CLICK_CODE);
        return list;
    }






    //method to abstract allotmentList from the retrofit body and pass to addQuestion method one by one
    public int getAllotmentCount()
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






    public void getJSON()
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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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





    //Convert pixels to density independent pixels
    public int pxToDp(int px,Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }




    //Convert density independent pixels to pixels
    public int dpToPx(int dp,Context context)
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
    @SuppressLint("NewApi")
    public void dynamicQuestion(LinearLayout fragmentLinearLayout, final Context context, View v, final List<QuestionList> questionList)
    {
        sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);

         answer = new String[questionList.size()];
         questionID=new String[questionList.size()];
         categoryID=new String[questionList.size()];
         questionDescription=new String[questionList.size()];
         size= questionList.size();
        
         final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
         params.gravity=Gravity.CENTER_HORIZONTAL;

         final SharedPreferences sharedPreferences=mContext.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);

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
            if (questionList.get(j).getFieldType().equals("C"))
            {
                final RadioButton[] rb = new RadioButton[2];
                final RadioGroup rg = new RadioGroup(context); //create the RadioGroup
                rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
                String[] options = context.getResources().getStringArray(R.array.radio_options_yes_no);

                for (int i = 0; i < options.length; i++)
                {
                    rb[i] = new RadioButton(context);
                    rb[i].setText(options[i]);
                    rb[i].setId(i + 100);
                    rg.addView(rb[i]);
                }

                questionHolderLinearLayout.addView(rg);
                final int finalJ = j;

                    // no radio buttons are checked
                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            int radioButtonID = rg.getCheckedRadioButtonId();
                            View radioButton = rg.findViewById(radioButtonID);
                            int idx = rg.indexOfChild(radioButton);

                            RadioButton r = (RadioButton) rg.getChildAt(idx);

                            answer[finalJ] = r.getText().toString();
                            questionID[finalJ] = questionList.get(finalJ).getQuestionId();
                            categoryID[finalJ] = questionList.get(finalJ).getCategoryId();
                            questionDescription[finalJ] = questionList.get(finalJ).getDescription();
                            // Toast.makeText(context, "" + answer[finalJ], Toast.LENGTH_SHORT).show();
                        }

                    });


            }


            //textFields
            else if (questionList.get(j).getFieldType().equals("T"))
            {
                final EditText editText=new EditText(context);
                if(questionList.get(j).getQuestionId().equals("35")||questionList.get(j).getQuestionId().equals("17"))
                {
                    popUpDatePicker(context,editText);
                }
                //editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                questionHolderLinearLayout.addView(editText);

                final int finalJ1 = j;

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {

                    }

                    @Override
                    public void afterTextChanged(Editable s)
                    {
                        answer[finalJ1] = editText.getText().toString();
                        questionID[finalJ1] = questionList.get(finalJ1).getQuestionId();
                        categoryID[finalJ1] = questionList.get(finalJ1).getCategoryId();
                        questionDescription[finalJ1] = questionList.get(finalJ1).getDescription();
                        //Toast.makeText(context, "" + answer[finalJ1], Toast.LENGTH_SHORT).show();
                    }
                });


                View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
                editText.setOnFocusChangeListener(ofcListener);
            }
            
            //listViews
            else if (questionList.get(j).getFieldType().equals("D"))
            {

                final Spinner spinner=new Spinner(context);

                String str = context.getResources().getString(R.string.spinner_default_item)+questionList.get(j).getFieldData();
                final List<String> spinnerList = Arrays.asList(str.split(","));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);
                questionHolderLinearLayout.addView(spinner);

                final int finalJ2 = j;


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        if(position==0)
                        {
                            answer[finalJ2]=null;
                        }


                       else
                           {
                            answer[finalJ2] = spinner.getSelectedItem().toString();
                            questionID[finalJ2] = questionList.get(finalJ2).getQuestionId();
                            categoryID[finalJ2] = questionList.get(finalJ2).getCategoryId();
                            questionDescription[finalJ2] = questionList.get(finalJ2).getDescription();
                            //Toast.makeText(context, "" + answer[finalJ2], Toast.LENGTH_SHORT).show();
                           }
                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
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

        save.setBackground(context.getResources().getDrawable(R.drawable.blue_rectangle));
        save.setWidth(60);
        save.setHeight(30);
        save.setTextColor(context.getResources().getColor(R.color.white));
        save.setText(R.string.save);

        prev.setHeight(dpToPx(30,context));
        prev.setBackground(context.getResources().getDrawable(R.drawable.rectangle));
        prev.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        prev.setPadding(pad,pad,pad,pad);
        prev.setText(R.string.prev);


        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(questionList.get(0).getCategoryId())>0) {
                    int fragPrev=Integer.parseInt(questionList.get(0).getCategoryId());
                    ((SurveyActivity) context).setCurrentItem( fragPrev-2, true);
                }
            }
        });


        save.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ApplySharedPref")
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

                int count=0;

                for(int i=0;i<questionList.size();i++)
                {

                    if(answer[i]!=null)
                    {
                        count++;
                    }

                }
                sharedPreferences.edit().putInt(Constants.CYLINDER_SAVE,0).commit();

                if(count==questionList.size())
                {
                    for(int j=0;j<questionList.size();j++)
                    {
                        databaseHelperUser.putAnswerEntryInDatabase(answer[j], questionID[j], questionDescription[j], categoryID[j], allotmentDate, areaName, consumerName, consumerNo, isCompleted, uniqueConsumerId, allottedId);
                    }
                        sharedPreferences.edit().putInt(Constants.CYLINDER_SAVE,1).commit();

                 /*   switch (categoryID[0])
                    {
                        case "1":
                            sharedPreferences.edit().putBoolean(Constants.CYLINDER_SAVE, true).commit();
                            break;
                        case "2":
                            sharedPreferences.edit().putBoolean(Constants.REGULATOR_SAVE, true).commit();
                            break;
                        case "3":
                            sharedPreferences.edit().putBoolean(Constants.RUBBER_HOSE_SAVE, true).commit();
                            break;
                        case "4":
                            sharedPreferences.edit().putBoolean(Constants.STOVE_SAVE, true).commit();
                            break;
                        case "5":
                            sharedPreferences.edit().putBoolean(Constants.GENERAL_SAVE, true).commit();
                            break;
                    }*/

                    Toast.makeText(context, "Saved SuccessFully", Toast.LENGTH_SHORT).show();
                    ((SurveyActivity)context).setCurrentItem(Integer.parseInt(questionList.get(0).getCategoryId()), true);

                }

                else
                    {
                        sharedPreferences.edit().putInt(Constants.CYLINDER_SAVE,0);
                        //sharedPreferences.edit().putBoolean(Constants.CYLINDER_SAVE,false);
                        Toast.makeText(context, "Answer all questions ", Toast.LENGTH_SHORT).show();
                    }
            }

        });




        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((SurveyActivity)context).setCurrentItem(Integer.parseInt(questionList.get(0).getCategoryId()), true);
            }
        });


      LinearLayout buttonLayout=new LinearLayout(context);
      //  final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
      buttonLayout.setPadding(pad,pad,pad,pad);
      buttonLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
      buttonLayout.setLayoutParams(params);

      buttonLayout.addView(prev);
      buttonLayout.addView(save);
      buttonLayout.addView(next);

      fragmentLinearLayout.addView(buttonLayout);

    }





    public void popUpDatePicker(final Context context, final EditText editText)
    {
        editText.setFocusable(false);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                editText.setText(sdf.format(myCalendar.getTime()));

            }

        };



        editText.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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








