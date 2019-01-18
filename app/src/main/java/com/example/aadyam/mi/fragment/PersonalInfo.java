package com.example.aadyam.mi.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.SurveyActivity;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.model.QuestionList;
import java.util.List;

public class PersonalInfo extends Fragment {


    DatabaseHelperUser databaseHelperUser;

    private EditText mobileNo_editText,emailId_editText,dateOfBirth_editText,fatherSpouseName_editText,familyMembers_editText,twoWheelers_editText,fourWheelers_editText,panCard_editText,passport_editText,voterId_editText,drivingLicense_editText;
    Spinner gender_spinner,fatherSpouse_spinner,cylinder_gap_spinner,refillBookingMode_spinner,houseAccomodation_spinner,rationCardAffidavit_spinner;
    Button save,next;
    CheckBox vip_checkbox,using_credit_card_checkbox,high_consumption_consumer_checkbox,usedPipeGas_checkbox;
    String mobileNo,emailId,dateOfBirth,fatherSpouseName,familyMembers,twoWheelers,fourWheelers,panCard,rationCardNo,affidavitNo,consumerNo;
    String passport,voterId,drivingLicense,genderResult,fatherSpouseResult,cylinderGapResults;
    String refillBookingModeResult,houseAccommodationResult,rationCardAffidavitResult;
    String vipResult,usingCreditCardResult,highConsumptionConsumerResult,usedPipeGasResult;
    EditText motherName_editText,rationAffidavit_EditText;
    TextView rationAffidavit_tv;

    Context context;

    private EditText date;
    private String motherNameResult;

    public PersonalInfo()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_info, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        databaseHelperUser = new DatabaseHelperUser(getContext());

        rationAffidavit_tv=view.findViewById(R.id.rationAffidavit_tv);
        mobileNo_editText = view.findViewById(R.id.mobileNo_edit);
        emailId_editText = view.findViewById(R.id.emailId_edit);
        dateOfBirth_editText = view.findViewById(R.id.dateOfBirth_edit);
        vip_checkbox = view.findViewById(R.id.vip_checkBox);
        using_credit_card_checkbox = view.findViewById(R.id.credit_card_checkBox);
        high_consumption_consumer_checkbox = view.findViewById(R.id.high_consumption_checkbox);
        motherName_editText = view.findViewById(R.id.motherName_editText);
        rationAffidavit_EditText = view.findViewById(R.id.rationAffidavit_EditText);
        fatherSpouse_spinner = view.findViewById(R.id.spinner_father_spouse);
        gender_spinner = view.findViewById(R.id.spinner_gender);
        fatherSpouseName_editText = view.findViewById(R.id.card_edit_editText_Father_spouse_name);
        familyMembers_editText = view.findViewById(R.id.card_edit_editText_family_members);

        cylinder_gap_spinner = view.findViewById(R.id.spinner_cylinder_gap);
        refillBookingMode_spinner = view.findViewById(R.id.spinner_cylinder_booking_mode);

        houseAccomodation_spinner = view.findViewById(R.id.spinner_accommodation);
        rationCardAffidavit_spinner = view.findViewById(R.id.spinner_ration_affidavit);
        usedPipeGas_checkbox = view.findViewById(R.id.piped_gas_checkBox);

        twoWheelers_editText = view.findViewById(R.id.card_edit_editText_two_wheeler);
        fourWheelers_editText = view.findViewById(R.id.card_edit_editText_four_wheeler);

        panCard_editText = view.findViewById(R.id.card_edit_editText_pan_no);
        passport_editText = view.findViewById(R.id.card_edit_editText_passport);

        voterId_editText = view.findViewById(R.id.card_edit_editText_voter_ID);
        drivingLicense_editText = view.findViewById(R.id.card_edit_editText_Driving_license);

        save = view.findViewById(R.id.button_save);
        next = view.findViewById(R.id.button_next);

        MyGlobals myGlobals = new MyGlobals(context);
        myGlobals.popUpDatePicker(context, dateOfBirth_editText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.genderList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender_spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.fatherSpouseList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fatherSpouse_spinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.rationCardAffidavitList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rationCardAffidavit_spinner.setAdapter(adapter2);

        rationCardAffidavit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if (rationCardAffidavit_spinner.getSelectedItemId() == 2)
                {
                    rationAffidavit_tv.setText("Affidavit Date");
                    rationAffidavit_EditText.setHint("DD/MM/YYYY");
                    rationAffidavit_EditText.setInputType(InputType.TYPE_CLASS_DATETIME);
                    new MyGlobals(context).popUpDatePicker(context,rationAffidavit_EditText);
                }

                else
                    {
                    rationAffidavit_tv.setText("Ration card no.");
                    rationAffidavit_EditText.setHint("Ration card no.");
                    rationAffidavit_EditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.houseAccomodationList));
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        houseAccomodation_spinner.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.refillBookingModeList));
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        refillBookingMode_spinner.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getContext().getResources().getStringArray(R.array.cylinder_gap_spinnerList));
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        cylinder_gap_spinner.setAdapter(adapter5);


        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dateOfBirth=  dateOfBirth_editText.getText().toString();
                emailId = emailId_editText.getText().toString();


                  if(!dateOfBirth.isEmpty())
                    {
                        dateOfBirth = dateOfBirth_editText.getText().toString();
                        mobileNo = mobileNo_editText.getText().toString();

                        motherNameResult = motherName_editText.getText().toString();
                        fatherSpouseName=fatherSpouseName_editText.getText().toString();


                if(rationCardAffidavit_spinner.getSelectedItemId()==1)
                {
                    rationCardNo=rationAffidavit_tv.getText().toString();
                }

                else if(rationCardAffidavit_spinner.getSelectedItemId()==2)
                {
                    affidavitNo=rationAffidavit_EditText.getText().toString();
                }

                else
                    {
                    rationCardNo=null;
                    affidavitNo=null;
                    }

                familyMembers=familyMembers_editText.getText().toString();

                if (vip_checkbox.isChecked())
                {
                    vipResult = vip_checkbox.getText().toString();
                }

                if (using_credit_card_checkbox.isChecked())
                {
                    usingCreditCardResult = using_credit_card_checkbox.getText().toString();
                }

                if (high_consumption_consumer_checkbox.isChecked())
                {
                    highConsumptionConsumerResult = high_consumption_consumer_checkbox.getText().toString();
                }


                genderResult = gender_spinner.getSelectedItem().toString();


                if (genderResult.equals("Select"))
                {
                    genderResult = null;
                }


                fatherSpouseResult = fatherSpouse_spinner.getSelectedItem().toString();

                if (fatherSpouseResult.equals("Select"))
                {
                    fatherSpouseResult=null;
                }


                cylinderGapResults=cylinder_gap_spinner.getSelectedItem().toString();
                if(cylinderGapResults.equals("Select"))
                {
                    cylinderGapResults=null;
                }


                refillBookingModeResult=refillBookingMode_spinner.getSelectedItem().toString();
                if(refillBookingModeResult.equals("Select"))
                {
                    refillBookingModeResult=null;
                }

                houseAccommodationResult=houseAccomodation_spinner.getSelectedItem().toString();
                if(houseAccommodationResult.equals("Select"))
                {
                    houseAccommodationResult=null;
                }

                rationCardAffidavitResult=rationCardAffidavit_spinner.getSelectedItem().toString();
                if(rationCardAffidavitResult.equals("Select"))
                {
                    rationCardAffidavitResult=null;
                }

                if(usedPipeGas_checkbox.isChecked())
                {
                    usedPipeGasResult=usedPipeGas_checkbox.getText().toString();
                }

                familyMembers=familyMembers_editText.getText().toString();
                twoWheelers=twoWheelers_editText.getText().toString();
                fourWheelers=fourWheelers_editText.getText().toString();
                panCard=panCard_editText.getText().toString();
                passport=passport_editText.getText().toString();
                voterId=voterId_editText.getText().toString();
                drivingLicense=drivingLicense_editText.getText().toString();

                if(emailId.isEmpty() || emailId!=null && isValidEmail(emailId) )
                {

                    boolean isSuccess = databaseHelperUser.putInformationEntryInDatabase(dateOfBirth, vipResult, genderResult, usingCreditCardResult, motherNameResult, highConsumptionConsumerResult, fatherSpouseResult, refillBookingModeResult, fatherSpouseName, refillBookingModeResult, familyMembers, houseAccommodationResult, rationCardAffidavitResult, twoWheelers, affidavitNo, fourWheelers, rationCardNo, usedPipeGasResult, panCard, passport, voterId, drivingLicense, mobileNo, emailId, consumerNo);

                    SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
                    sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);

                    databaseHelperUser.setFragmentStatusSaved(sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null),6);
                    if (isSuccess)
                    {
                        ((SurveyActivity) context).setCurrentItem(6, true);
                    }
                }

                    else
                    {
                        Toast.makeText(context, "Enter valid e-mail address", Toast.LENGTH_SHORT).show();
                    }
                }


                else
                    {
                        Toast.makeText(context, "Date of birth is must", Toast.LENGTH_SHORT).show();
                    }
            }

        });



        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((SurveyActivity)context).setCurrentItem(6, true);
            }
        });
    }


    public static boolean isValidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
