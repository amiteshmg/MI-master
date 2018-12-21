package com.example.aadyam.mi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;


import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.Question;
import com.example.aadyam.mi.model.QuestionList;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Aadyam on 10/22/2018.
 */

public class DatabaseHelperUser extends SQLiteOpenHelper
{

    //User table table details

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userinfo.db";

    //Question table details
    private static final String TABLE_NAME_QUESTION = "QuestionList";
    private static final String COL1_1 = "Active";
    private static final String COL1_2 = "Category_ID";
    private static final String COL1_3 = "Description";
    private static final String COL1_4 = "Description_Hindi";
    private static final String COL1_5 = "Description_Marathi";
    private static final String COL1_6 = "Field_Data";
    private static final String COL1_7 = "Field_Type";
    private static final String COL1_8 = "Remark";
    private static final String COL1_9 = "Question_ID";


    //Distributor table details
    private static final String TABLE_NAME_DISTRIBUTOR = "DistributorDetails";
    private static final String COL2_2 = "ConsumerNo";
    private static final String COL2_3 = "ContactNo";
    private static final String COL2_4 = "ConsumerName";
    private static final String COL2_5 = "Address";
    private static final String COL2_6 = "DistributorAddress";


    //Allotment table details
    private static final String TABLE_NAME_ALLOTMENT = "AllotmentList";
    private static final String COL3_1 = "id";
    private static final String COL3_2 = "inspectionId";
    private static final String COL3_3 = "address";
    private static final String COL3_4 = "allotteddate";
    private static final String COL3_5 = "areaname";
    private static final String COL3_6 = "arearefno";
    private static final String COL3_7 = "consumername";
    private static final String COL3_8 = "consumerno";
    private static final String COL3_9 = "isCompleted";
    private static final String COL3_10 = "isUnsafe";
    private static final String COL3_11 = "isUnsafeReAllot";
    private static final String COL3_12 = "denied";
    private static final String COL3_13 = "not_available";
    private static final String COL3_14 = "pre_denied";
    private static final String COL3_15 = "pre_not_available";
    private static final String COL3_16 = "ref_ins_formId";
    private static final String COL3_17= "ref_allotment_id";
    private static final String COL3_18= "mobileno";
    private static final String COL3_19= "uniqueConsumerId";
    private static final String COL3_20 = "allotedid";
    private static final String COL3_21 = "inspection_no";
    private static final String COL3_22 = "instruction";
    private static final String COL3_23 = "longitude";
    private static final String COL3_24= "latitude";
    private static final String COL3_25 = "lastinspection";
    private static final String COL3_26 = "cylinder_save";
    private static final String COL3_27 = "regulator_save";
    private static final String COL3_28 = "rubberhose_save";
    private static final String COL3_29 = "stove_save";
    private static final String COL3_30 = "general_save";
    private static final String COL3_31 = "personal_save";
    private static final String COL3_32 = "unsafe_save";
    private static final String COL3_33 = "satisfactory";
    private static final String COL3_34 = "mobile_completed";
    private static final String COL3_35 = "web_mobile_completed";
    private static final String COL3_36 = "SYNC_completed";
    private static final String COL3_37 = "internet";
    private static final String COL3_38 = "json_data";



    public static final int API_QUESTIONS=1;
    public static final int API_ALLOTMENT=2;


    public int count=0;

    private static final String TABLE_NAME_ANSWERS ="Answers";

    //photos table

    private static final String TABLE_NAME_PHOTOS = "Photos";
    private static final String COL6_1 = "id";
    private static final String COL6_2 = "flag";
    private static final String COL6_3 = "filestream";
    private static final String COL6_4 = "distributor_id";
    private static final String COL6_5 = "filename";
    private static final String COL6_6 = "Inspectionformid";
    private static final String COL6_7 = "ref_Inspectionformid";
    private static final String COL6_8 = "unsafe_id";
    private static final String COL6_9 = "uniqueConsumerId";
    private static final String COL6_10 = "allotedId";
    private static final String COL6_11 = "imageType";
    private static final String COL6_12 = "json_data";






    //Answers table

    private static final String SQL_CREATE_TABLE_ANSWERS = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_ANSWERS+" ( id INTEGER PRIMARY KEY,questionid TEXT,questiondesc TEXT,answer TEXT,allotteddate TEXT, areaname TEXT, consumername TEXT,consumerno TEXT,isCompleted TEXT,uniqueConsumerId TEXT,alloted_id TEXT,categoryid TEXT,fieldtype TEXT,inspectionid TEXT,unsafe_inspectionid TEXT,json_data TEXT);";
    private static final String COL4_1 = "id";
    private static final String COL4_2 = "questionid";
    private static final String COL4_3 = "questiondesc";
    private static final String COL4_4 = "answer";
    private static final String COL4_5 = "allotteddate";
    private static final String COL4_6 = "areaname";
    private static final String COL4_7 = "consumername";
    private static final String COL4_8 = "consumerno";
    private static final String COL4_9 = "isCompleted";
    private static final String COL4_10 = "uniqueConsumerId";
    private static final String COL4_11 = "alloted_id";
    private static final String COL4_12 = "categoryid";
    private static final String COL4_13 = "fieldtype";
    private static final String COL4_14 = "inspectionid";
    private static final String COL4_15 = "unsafe_inspectionid";
    private static final String COL4_16 = "json_data";


    //CREATE TABLE deniedNotAvl( id INTEGER PRIMARY KEY,allotedId TEXT,deniedFlag TEXT,notAvlFlag TEXT,json_data TEXT)




    //PersonalInfo table
    private static final String TABLE_NAME_PERSONAL_INFO ="PersonalInfo" ;
    private static final String COL5_1 = "id";
    private static final String COL5_2 = "unique_consumer_id";
    private static final String COL5_3 = "inspectionid";
    private static final String COL5_4 = "dateofbirth";
    private static final String COL5_5 = "vip";
    private static final String COL5_6 = "gender";
    private static final String COL5_7 = "usingcreditcard";
    private static final String COL5_8 = "mothername";
    private static final String COL5_9 = "highconsumer";
    private static final String COL5_10 = "father";
    private static final String COL5_11 = "refills";
    private static final String COL5_12 = "fatherspousename";
    private static final String COL5_13 = "book_refill";
    private static final String COL5_14 = "familymember";
    private static final String COL5_15 = "typeofhouse";
    private static final String COL5_16 = "rationcard";
    private static final String COL5_17 = "two_wheeler";
    private static final String COL5_18 = "affidavitdate";
    private static final String COL5_19 = "four_wheeler";
    private static final String COL5_20 = "rationcardno";
    private static final String COL5_21 = "pipegas";
    private static final String COL5_22 = "pancardno";
    private static final String COL5_23 = "passportno";
    private static final String COL5_24 = "voterid";
    private static final String COL5_25 = "licenseno";
    private static final String COL5_26 = "mobileno";
    private static final String COL5_27 = "emailid";
    private static final String COL5_28 = "json_data";


    //create table queries


    private static final String SQL_CREATE_TABLE_QUESTIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_QUESTION + " (Active TEXT,Category_ID TEXT,Description TEXT,Description_Hindi TEXT,Description_Marathi TEXT,Field_Data TEXT,Field_Type TEXT,Remark TEXT,Question_ID INTEGER PRIMARY KEY) ";
    private static final String SQL_CREATE_TABLE_DISTRIBUTOR = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_DISTRIBUTOR + " (id INTEGER PRIMARY KEY AUTOINCREMENT,ContactPhone1 TEXT,DistributorName TEXT,Result TEXT,StaffName TEXT,StaffRefNo TEXT,StaffStatusCode TEXT,StatusCode TEXT,WhetherDeliveryBoyND TEXT,WhetherDeliveryND TEXT,WhetherESMechanic TEXT,WhetherEmployee TEXT,WhetherMechanic TEXT,WhetherOther TEXT) ";
    private static final String SQL_CREATE_TABLE_ALLOTMENT_LIST =  "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_ALLOTMENT+" ( id INTEGER PRIMARY KEY ,inspectionId TEXT,address TEXT,allotteddate TEXT, areaname TEXT, arearefno TEXT,consumername TEXT,consumerno TEXT,isCompleted TEXT,isUnsafe TEXT,isUnsafeReAllot TEXT,denied TEXT,not_available TEXT,pre_denied TEXT,pre_not_available TEXT,ref_ins_formId TEXT,ref_allotment_id TEXT,mobileno TEXT,uniqueConsumerId TEXT,allotedid TEXT,inspection_no TEXT,instruction TEXT,longitude TEXT,latitude TEXT,lastinspection TEXT,cylinder_save TEXT,regulator_save TEXT,rubberhose_save TEXT,stove_save TEXT,general_save TEXT,personal_save TEXT,unsafe_save TEXT,satisfactory TEXT,mobile_completed TEXT,web_mobile_completed TEXT,SYNC_completed TEXT,internet TEXT,json_data TEXT,"+" UNIQUE("+COL2_2+") ON CONFLICT IGNORE);";
    private static final String SQL_CREATE_TABLE_PERSONAL_INFO =  "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_PERSONAL_INFO+" ( id INTEGER PRIMARY KEY AUTOINCREMENT,unique_consumer_id TEXT,allotedid TEXT,inspectionid TEXT, dateofbirth TEXT, vip TEXT,gender TEXT,usingcreditcard TEXT,mothername TEXT,highconsumer TEXT,father TEXT,refills TEXT,fatherspousename TEXT,book_refill TEXT, familymember TEXT, typeofhouse TEXT,rationcard TEXT,two_wheeler TEXT,affidavitdate TEXT,four_wheeler TEXT,rationcardno TEXT,pipegas TEXT,pancardno TEXT,passportno TEXT,voterid TEXT,licenseno TEXT,mobileno TEXT,emailid TEXT,json_data TEXT);";
    private static final String SQL_CREATE_TABLE_PHOTOS =  "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_PHOTOS+" ( id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,filestream TEXT, distributor_id TEXT, filename TEXT,Inspectionformid TEXT,ref_Inspectionformid TEXT,unsafe_id TEXT,uniqueConsumerId TEXT,allotedId TEXT,imageType TEXT,json_data TEXT);";
    private static final String TABLE_NAME_UNSAFE_QUESTIONS = "UnsafeQuestions";
    private static final String SQL_CREATE_TABLE_UNSAFE_QUESTIONS =  "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_UNSAFE_QUESTIONS+" ( id INTEGER PRIMARY KEY,active TEXT,categoryid TEXT, description TEXT, descriptionHindi TEXT, descriptionMarathi TEXT, fielddata TEXT,fieldtype TEXT,isremark TEXT,questionid TEXT,ref_ins_formId TEXT,json_data TEXT);";
    private  Context context;


    private static final String TABLE_NAME_LAYOUT_COUNT = "layoutCount";
    private static final String SQL_CREATE_TABLE_LAYOUT_COUNT =  "CREATE TABLE IF NOT EXISTS "+TABLE_NAME_LAYOUT_COUNT+" ( id INTEGER PRIMARY KEY,Count INTEGER);";


    public DatabaseHelperUser(Context context)
    {
        super(context, Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }



    public void createTables()
    {SQLiteDatabase db=this.getWritableDatabase();


        db.execSQL(SQL_CREATE_TABLE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_DISTRIBUTOR);
        db.execSQL(SQL_CREATE_TABLE_ALLOTMENT_LIST);
        db.execSQL(SQL_CREATE_TABLE_PERSONAL_INFO);
        db.execSQL(SQL_CREATE_TABLE_UNSAFE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_ANSWERS);
        db.execSQL(SQL_CREATE_TABLE_PHOTOS);
        db.execSQL(SQL_CREATE_TABLE_LAYOUT_COUNT);
        getQuestion();
        getAllotment();



    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // question=new Question();
       /* db.execSQL(SQL_CREATE_TABLE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_DISTRIBUTOR);
        db.execSQL(SQL_CREATE_TABLE_ALLOTMENT_LIST);
        db.execSQL(SQL_CREATE_TABLE_PERSONAL_INFO);
        db.execSQL(SQL_CREATE_TABLE_UNSAFE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_ANSWERS);
        db.execSQL(SQL_CREATE_TABLE_PHOTOS);
        getQuestion();
*/
        //getAllotment();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DISTRIBUTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ALLOTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PHOTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PERSONAL_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UNSAFE_QUESTIONS);
        onCreate(db);
    }




    public void createQuestionTable()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(SQL_CREATE_TABLE_QUESTIONS);
    }




    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public String getDatabaseName()
    {
        return super.getDatabaseName();
    }





    //TODO-----------------------------------------QUESTION FUNCTIONS----------------------------------------------

    public List<QuestionList> getQuestionEntries(int fragmentID)
    {
        List<QuestionList> questionList = new ArrayList<QuestionList>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_NAME_QUESTION;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            switch (fragmentID)
            {
                case R.layout.fragment_cylinder:

                    do
                    {
                        if (cursor.getString(1).equals("1"))
                        {
                            QuestionList question0 = new QuestionList();
                            question0.setActive(cursor.getString(0));
                            question0.setCategoryId(cursor.getString(1));
                            question0.setDescription(cursor.getString(2));
                            question0.setDescriptionHindi(cursor.getString(3));
                            question0.setDescriptionMarathi(cursor.getString(4));
                            question0.setFieldData(cursor.getString(5));
                            question0.setFieldType(cursor.getString(6));
                            question0.setIsRemark(cursor.getString(7));
                            question0.setQuestionId(cursor.getString(8));
                            // Adding question object to list
                            questionList.add(question0);
                        }
                    } while (cursor.moveToNext());

                    break;

                case R.layout.fragment_regulator:
                    do
                    {
                        if (cursor.getString(1).equals("2"))
                        {
                            QuestionList question1 = new QuestionList();
                            question1.setActive(cursor.getString(0));
                            question1.setCategoryId(cursor.getString(1));
                            question1.setDescription(cursor.getString(2));
                            question1.setDescriptionHindi(cursor.getString(3));
                            question1.setDescriptionMarathi(cursor.getString(4));
                            question1.setFieldData(cursor.getString(5));
                            question1.setFieldType(cursor.getString(6));
                            question1.setIsRemark(cursor.getString(7));
                            question1.setQuestionId(cursor.getString(8));
                            // Adding question object to list
                            questionList.add(question1);
                        }
                    } while (cursor.moveToNext());

                    break;

                case R.layout.fragment_rubberhose:
                    do {
                        if (cursor.getString(1).equals("3"))
                        {
                            QuestionList question2 = new QuestionList();
                            question2.setActive(cursor.getString(0));
                            question2.setCategoryId(cursor.getString(1));
                            question2.setDescription(cursor.getString(2));
                            question2.setDescriptionHindi(cursor.getString(3));
                            question2.setDescriptionMarathi(cursor.getString(4));
                            question2.setFieldData(cursor.getString(5));
                            question2.setFieldType(cursor.getString(6));
                            question2.setIsRemark(cursor.getString(7));
                            question2.setQuestionId(cursor.getString(8));

                            // Adding question object to list
                            questionList.add(question2);
                        }

                    } while (cursor.moveToNext());

                    break;



                case R.layout.fragment_stove:

                    do {
                        if (cursor.getString(1).equals("4")) {

                            QuestionList question3 = new QuestionList();
                            question3.setActive(cursor.getString(0));
                            question3.setCategoryId(cursor.getString(1));
                            question3.setDescription(cursor.getString(2));
                            question3.setDescriptionHindi(cursor.getString(3));
                            question3.setDescriptionMarathi(cursor.getString(4));
                            question3.setFieldData(cursor.getString(5));
                            question3.setFieldType(cursor.getString(6));
                            question3.setIsRemark(cursor.getString(7));
                            question3.setQuestionId(cursor.getString(8));
                            // Adding question object to list
                            questionList.add(question3);
                        }
                    } while (cursor.moveToNext());
                    break;


                case R.layout.fragment_general:
                    do {
                        if (cursor.getString(1).equals("5"))
                        {
                            QuestionList question4 = new QuestionList();
                            question4.setActive(cursor.getString(0));
                            question4.setCategoryId(cursor.getString(1));
                            question4.setDescription(cursor.getString(2));
                            question4.setDescriptionHindi(cursor.getString(3));
                            question4.setDescriptionMarathi(cursor.getString(4));
                            question4.setFieldData(cursor.getString(5));
                            question4.setFieldType(cursor.getString(6));
                            question4.setIsRemark(cursor.getString(7));
                            question4.setQuestionId(cursor.getString(8));
                            // Adding question object to list
                            questionList.add(question4);
                        }
                    } while (cursor.moveToNext());
                    break;
            }



        }
        // return filled QuestionList
        return questionList;
    }





    //method to abstract questions from the retrofit body and pass to addQuestion method one by one

    public void getQuestion()
    {
        //Retrofit hits here
        ApiInterface apiService;
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Question> call = apiService.getQuestionDetails();

        //enqueue - used to execute calls asynchronously
        call.enqueue(new Callback<Question>()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response)
            {


                assert response.body() != null;


                int size=response.body().getQuestionList().size();

                for(int i=0;i<size;i++)
                {
                    System.out.println("Data : "+response.body().getQuestionList().get(i).getDescription());
                    String s1,s2,s3,s4,s5,s6,s7,s8,s9;

                    s1=response.body().getQuestionList().get(i).getActive();
                    s2=response.body().getQuestionList().get(i).getCategoryId();
                    s3=response.body().getQuestionList().get(i).getDescription();
                    s4=response.body().getQuestionList().get(i).getDescriptionHindi();
                    s5=response.body().getQuestionList().get(i).getDescriptionMarathi();
                    s6=response.body().getQuestionList().get(i).getFieldData();
                    s7=response.body().getQuestionList().get(i).getFieldType();
                    s8=response.body().getQuestionList().get(i).getIsRemark();
                    s9=response.body().getQuestionList().get(i).getQuestionId();

                    addQuestion(s1,s2,s3,s4,s5,s6,s7,s8,s9);



                }



            }


            @Override
            public void onFailure(@NonNull Call<Question> call, @NonNull Throwable t)
            {
                Log.e(TAG, "ERROR FOUND :" + t.toString());
            }
        });

    }





    //method to insert question entry into SQLiteDatabase
    private void addQuestion(String Active, String CategoryID, String Description, String DescriptionHindi, String DescriptionMarathi, String FieldData, String FieldType, String Remark, String QuestionID)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();



        contentValues.put(COL1_1, Active);
        contentValues.put(COL1_2, CategoryID);
        contentValues.put(COL1_3, Description);
        contentValues.put(COL1_4, DescriptionHindi);
        contentValues.put(COL1_5, DescriptionMarathi);
        contentValues.put(COL1_6, FieldData);
        contentValues.put(COL1_7, FieldType);
        contentValues.put(COL1_8, Remark);
        contentValues.put(COL1_9, QuestionID);

        long result = db.insert(TABLE_NAME_QUESTION, null, contentValues);


        System.out.println("Data: " + result);



    }


//---------------------------------------------QUESTION SECTION ENDS---------------------------------------------------------










    //TODO ---------------------------------------------ALLOTMENT METHODS---------------------------------------------------------


    //method to abstract allotmentList from the retrofit body and pass to addQuestion method one by one
    public void getAllotment()
    {

        ApiInterface apiInterface;
        apiInterface=ApiClient.getClient().create(ApiInterface.class);

        //AllotmentList?UserId=8193&StaffRefNo=11710819300000002&ConsumerCount=0
        Call<Allotment> call = apiInterface.getAllotmentList();

        call.enqueue(new Callback<Allotment>() {
            @Override
            public void onResponse(@NonNull Call<Allotment> call, @NonNull Response<Allotment> response)
            {

                Toast.makeText(context, ""+response.body().getAllotmentListResult().size(), Toast.LENGTH_SHORT).show();
                System.out.println(" DEMO "+response.body().getAllotmentListResult().get(1).getAddress());


                assert response.body() != null;



                int size=response.body().getAllotmentListResult().size();
                for(int i=0;i<size;i++)
                {
                    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39;

                    s1=response.body().getAllotmentListResult().get(i).getInspFormId();
                    s2=response.body().getAllotmentListResult().get(i).getAddress();
                    s3=response.body().getAllotmentListResult().get(i).getAllottedDate();
                    s4=response.body().getAllotmentListResult().get(i).getAreaName();
                    s5=response.body().getAllotmentListResult().get(i).getAreaRefNo().toString();
                    s6=response.body().getAllotmentListResult().get(i).getConsumerName();
                    s7=response.body().getAllotmentListResult().get(i).getConsumerNo().toString();
                    s8=response.body().getAllotmentListResult().get(i).getIsCompleted().toString();
                    s9=response.body().getAllotmentListResult().get(i).getIsUnsafe().toString();
                    s10=response.body().getAllotmentListResult().get(i).getIsUnsafeReallot().toString();
                    s11=response.body().getAllotmentListResult().get(i).getIsDenied().toString();
                    s12=response.body().getAllotmentListResult().get(i).getIsNotAvailable().toString();
                    s13=response.body().getAllotmentListResult().get(i).getIsPrevDenied().toString();
                    s14=response.body().getAllotmentListResult().get(i).getIsPrevNotAvailable().toString();
                    s15=response.body().getAllotmentListResult().get(i).getRefInspFormId().toString();
                    s16=response.body().getAllotmentListResult().get(i).getRefAllotmentId().toString();
                    s17=response.body().getAllotmentListResult().get(i).getMobileNo().toString();
                    s18=response.body().getAllotmentListResult().get(i).getUniqueConsumerId().toString();
                    s19=response.body().getAllotmentListResult().get(i).getAllotmentId().toString();
                    s20=response.body().getAllotmentListResult().get(i).getInspFormId();

                    count++;
                    putAllotment(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20);

                }

                setAllotmentCount(count);

            }

            @Override
            public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t)
            {

                Log.d("ERROR",t.getMessage());

            }
        });

    }

    private void setAllotmentCount(int count) {
        this.count=count;
    }


    public int getAllotmentCount()
    {
        return count;
    }

    public void putAllotment(String inspectionId ,String address,String allotteddate, String areaname, String arearefno ,String consumername ,String consumerno,String isCompleted ,String isUnsafe ,String isUnsafeReAllot ,String denied ,String not_available,String pre_denied ,String pre_not_available ,String ref_ins_formId ,String ref_allotment_id ,String mobileno ,String uniqueConsumerId ,String allotedid ,String inspection_no)
    {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3_2, inspectionId);
        contentValues.put(COL3_3, address);
        contentValues.put(COL3_4, allotteddate);
        contentValues.put(COL3_5, areaname);
        contentValues.put(COL3_6, arearefno);
        contentValues.put(COL3_7, consumername);
        contentValues.put(COL3_8, consumerno);
        contentValues.put(COL3_9, isCompleted);
        contentValues.put(COL3_10, isUnsafe);
        contentValues.put(COL3_11, isUnsafeReAllot);
        contentValues.put(COL3_12, denied);
        contentValues.put(COL3_13, not_available);
        contentValues.put(COL3_14, pre_denied);
        contentValues.put(COL3_15, pre_not_available);
        contentValues.put(COL3_16, ref_ins_formId);
        contentValues.put(COL3_17, ref_allotment_id);
        contentValues.put(COL3_18, mobileno);
        contentValues.put(COL3_19, uniqueConsumerId);
        contentValues.put(COL3_20, allotedid);
        contentValues.put(COL3_21, inspection_no);

        // contentValues.put(COL3_22, instruction);
        // contentValues.put(COL3_23, longitude);
        // contentValues.put(COL3_24, latitude);
        // contentValues.put(COL3_25, lastinspection);
        // contentValues.put(COL3_26, cylinder_save);
        // contentValues.put(COL3_27, regulator_save);
        // contentValues.put(COL3_28, rubberhose_save);
        // contentValues.put(COL3_29, stove_save);
        // contentValues.put(COL3_30, general_save);
        // contentValues.put(COL3_31, personal_save);

        // contentValues.put(COL3_32, unsafe_save);
        // contentValues.put(COL3_33, satisfactory);
        // contentValues.put(COL3_34, mobile_completed);
        // contentValues.put(COL3_35, web_mobile_completed);
        // contentValues.put(COL3_36, SYNC_completed);
        // contentValues.put(COL3_37, internet);
        // contentValues.put(COL3_38, json_data);


        long result = db.insert(TABLE_NAME_ALLOTMENT, null, contentValues);

        System.out.println("Data: " + result);
        count++;

    }





    //fetching allotment entries from the mobile SQLiteDatabase
    public List<AllotmentList> getAllotmentEntries(int CLICK_CODE)
    {
        List<AllotmentList> allotmentLists = new ArrayList<AllotmentList>();
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        switch (CLICK_CODE)
        {

//TODO Apply logic here

            case Constants.TOTAL_ALLOTTED_PENDING:
                String selectQuery = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_9+"=0 AND "+COL3_12+"=0 and "+COL3_13+"=0 ;";
                cursor = db.rawQuery(selectQuery, null);
                break;


            case Constants.TOTAL_UNSAFE:
                String selectQuery1 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_10+"=1 AND "+COL3_33+"='false';";
                cursor = db.rawQuery(selectQuery1, null);
                break;


            case Constants.TOTAL_DENIED:
                String selectQuery2= "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_12+"=1";
                cursor = db.rawQuery(selectQuery2, null);
                break;


            case Constants.TOTAL_NOT_AVAILABLE:
                String selectQuery3 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_13+"=1";
                cursor = db.rawQuery(selectQuery3, null);
                break;


            case Constants.TOTAL_REALLOTTED_UNSAFE:
                String selectQuery4 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_11+"=1;";
                cursor = db.rawQuery(selectQuery4, null);
                break;


            case Constants.TOTAL_REALLOTTED_DENIED:
                String selectQuery5 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_12+"=0 AND "+COL3_14+"=1 ;";
                cursor = db.rawQuery(selectQuery5, null);
                break;


            case Constants.TOTAL_REALLOTTED_NOT_AVAILABLE:
                String selectQuery6 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_15+"=1";
                cursor = db.rawQuery(selectQuery6, null);
                break;


            case Constants.TOTAL_TOTAL:
                String selectQuery7 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_9+"=1;";
                cursor = db.rawQuery(selectQuery7, null);
                break;

            case Constants.TOTAL_AGAINST_UNSAFE:
                String selectQuery8 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_10+"=1;";
                cursor = db.rawQuery(selectQuery8, null);
                break;


            case Constants.TOTAL_AGAINST_DENIED:
                String selectQuery9 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_14+"=1;";
                cursor = db.rawQuery(selectQuery9, null);
                break;



            case Constants.TOTAL_AGAINST_NOT_AVAILABLE:
                String selectQuery10 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_15+"=1;";
                cursor = db.rawQuery(selectQuery10, null);
                break;


            default:
                String selectQuery0 = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT;
                cursor = db.rawQuery(selectQuery0, null);


        }




        // looping through all rows and adding to list
        if(cursor.moveToFirst())
        {
            do
            {

                AllotmentList allotment = new AllotmentList();

                allotment.setId(cursor.getInt(0));
                allotment.setInspFormId(cursor.getString(1));
                allotment.setAddress(cursor.getString(2));
                allotment.setAllottedDate(cursor.getString(3));
                allotment.setAreaName(cursor.getString(4));
                allotment.setAreaRefNo(cursor.getLong(5));
                allotment.setConsumerName(cursor.getString(6));
                allotment.setConsumerNo(cursor.getInt(7));
                allotment.setIsCompleted(cursor.getInt(8));
                allotment.setIsUnsafe(cursor.getInt(9));
                allotment.setIsUnsafeReallot(cursor.getInt(10));
                allotment.setIsDenied(cursor.getInt(11));
                allotment.setIsNotAvailable(cursor.getInt(12));
                allotment.setIsPrevDenied(cursor.getInt(13));
                allotment.setIsPrevNotAvailable(cursor.getInt(14));
                allotment.setRefInspFormId(cursor.getInt(15));
                allotment.setRefAllotmentId(cursor.getInt(16));
                allotment.setMobileNo(cursor.getLong(17));
                allotment.setUniqueConsumerId(cursor.getLong(18));
                allotment.setAllotmentId(cursor.getInt(19));
                allotmentLists.add(allotment);

            } while (cursor.moveToNext());
        }

        // return contact list
        return allotmentLists;

    }



   /* public long getLayoutCount(int LAYOUT_CODE)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        int count1;
        //long numRows = DatabaseUtils.queryNumEntries(db, TABLE_NAME_LAYOUT_COUNT);

        Cursor cursor=db.rawQuery("SELECT * from "+TABLE_NAME_LAYOUT_COUNT,null);


        if(cursor.moveToFirst())
        {

            do {

                return cursor.getInt(1);
            }while (cursor.moveToNext());
        }

        else
        {
            return 0;
        }

    }*/



    //---------------------------------ALLOTMENT SECTION ENDS------------------------------------------------





    //TODO----------------------------------------ANSWERS TABLE-----------------------------------------------------




    public void putAnswers(String areaName,String consumerName, String consumerNo,String UniqueConumerId,String AllottedId)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

       // contentValues.put(COL4_2, questionId);
       // contentValues.put(COL4_3, questionDescription);
       // contentValues.put(COL4_4, answer);
        contentValues.put(COL4_6, areaName);
        contentValues.put(COL4_7, consumerName);
        contentValues.put(COL4_8, consumerNo);
        contentValues.put(COL4_10, UniqueConumerId);
        contentValues.put(COL4_11, AllottedId);
       // contentValues.put(COL4_12, category);
       // contentValues.put(COL4_13, fieldType);


        long result = db.insert(TABLE_NAME_ANSWERS, null, contentValues);

        System.out.println("ANSWERS Data: " + result);


    }





    //TODO----------------------------------------PHOTOS TABLE-----------------------------------------------------

    public void setPhotos(String filename,String encodedImage,int CODE)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL6_3,encodedImage);

        //contentValues.put(COL6_4,getAllotmentEntries().get());

        contentValues.put(COL6_5,filename);


        //TODO Get number to be filled in GET ????

        //contentValues.put(COL6_6,getAllotmentEntries().get().getInspFormId());

        //contentValues.put(COL6_7,getAllotmentEntries().get().getRefInspFormId());

        //contentValues.put(COL6_8,getAllotmentEntries().get().getIsUnsafe());

        //contentValues.put(COL6_9,getAllotmentEntries().get().getUniqueConsumerId());

        //contentValues.put(COL6_10,getAllotmentEntries().get().getAllotmentId());

        contentValues.put(COL6_11,CODE);

        long result = db.insert(TABLE_NAME_PHOTOS, null, contentValues);

        System.out.println("Photos Data: " + result);

        //id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,filestream TEXT, distributor_id TEXT, filename TEXT,Inspectionformid TEXT,ref_Inspectionformid TEXT,unsafe_id TEXT,uniqueConsumerId TEXT,allotedId TEXT,imageType TEXT,json_data TEXT);";

    }

    public void setAllotmentEntries(List<AllotmentList> allotmentLists) {

        int size=allotmentLists.size();

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();



        //CREATE TABLE consumer( id INTEGER PRIMARY KEY,inspectionId TEXT,address TEXT,allotteddate TEXT, areaname TEXT, arearefno TEXT,consumername TEXT,consumerno TEXT,isCompleted TEXT,isUnsafe TEXT,isUnsafeReAllot TEXT,denied TEXT,not_available TEXT,pre_denied TEXT,pre_not_available TEXT,ref_ins_formId TEXT,ref_allotment_id TEXT,mobileno TEXT,uniqueConsumerId TEXT,allotedid TEXT,inspection_no TEXT,instruction TEXT,longitude TEXT,latitude TEXT,lastinspection TEXT,cylinder_save TEXT,regulator_save TEXT,rubberhose_save TEXT,stove_save TEXT,general_save TEXT,personal_save TEXT,unsafe_save TEXT,satisfactory TEXT,mobile_completed TEXT,web_mobile_completed TEXT,SYNC_completed TEXT,internet TEXT,json_data TEXT)

        for(int i=0;i<size;i++)
        {
            //allotmentLists.get(i).setInspFormId();
            contentValues.put(COL3_2,allotmentLists.get(i).getAllotmentId());
            contentValues.put(COL3_3,allotmentLists.get(i).getAddress());
            contentValues.put(COL3_4,allotmentLists.get(i).getAllottedDate());
            contentValues.put(COL3_5,allotmentLists.get(i).getAreaName());
            contentValues.put(COL3_6,allotmentLists.get(i).getAreaRefNo());
            contentValues.put(COL3_7,allotmentLists.get(i).getConsumerName());
            contentValues.put(COL3_8,allotmentLists.get(i).getConsumerNo());
            contentValues.put(COL3_9,allotmentLists.get(i).getIsCompleted());
            contentValues.put(COL3_10,allotmentLists.get(i).getIsUnsafe());
            contentValues.put(COL3_11,allotmentLists.get(i).getIsUnsafeReallot());
            contentValues.put(COL3_12,allotmentLists.get(i).getIsDenied());
            contentValues.put(COL3_13,allotmentLists.get(i).getIsNotAvailable());
            contentValues.put(COL3_14,allotmentLists.get(i).getIsPrevDenied());
            contentValues.put(COL3_15,allotmentLists.get(i).getIsPrevNotAvailable());
            contentValues.put(COL3_16,allotmentLists.get(i).getRefInspFormId());
            contentValues.put(COL3_17,allotmentLists.get(i).getRefAllotmentId());
            contentValues.put(COL3_18,allotmentLists.get(i).getMobileNo());
            contentValues.put(COL3_19,allotmentLists.get(i).getUniqueConsumerId());
            contentValues.put(COL3_20,allotmentLists.get(i).getAllotmentId());
            contentValues.put(COL3_21,allotmentLists.get(i).getInspFormId().toString());

            long result=db.insert(TABLE_NAME_ALLOTMENT,null,contentValues);

        }
    }

    public int getAnswerCount()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String countQuery="SELECT * FROM "+TABLE_NAME_ANSWERS;
        Cursor cursor=db.rawQuery(countQuery,null);
        int count=cursor.getCount();
        cursor.close();
        return  count;


    }


    public void putOfflineAnswers()
    {


    }

    public boolean ifQuestionTableExists()
    {

        SQLiteDatabase db=this.getWritableDatabase();

        String checkQuery="select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + TABLE_NAME_QUESTION + "'";

        //SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='Question';

        Cursor cursor=db.rawQuery(checkQuery,null);

        Log.d("CURSOR",""+cursor.getCount());

        if(cursor.getCount()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public String getPhotoEntry(int imageViewCode)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        String getQuery="SELECT * FROM "+ TABLE_NAME_PHOTOS +" WHERE "+COL6_11+"="+imageViewCode;
        Cursor cursor=db.rawQuery(getQuery,null);
        String imageString;

        if(cursor.moveToFirst())
        {
            do {
                //cursor.getString()
                return cursor.getString(8);


                }

            while(cursor.moveToNext());
        }

        else
            {
            return null;
            }



    }


    public void updateAnswerEntryInDatabase(int FRAGMENT_CODE, Map<Integer,String> answer,String quesId,String quesDescription,String categoryId)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        switch (FRAGMENT_CODE)
        {

            case Constants.CYLINDER_FRAG_CODE:

                ContentValues contentValues=new ContentValues();
                //db.getSyncedTables();
                //String questionId,String questionDescription,String answer,String areaName,String consumerName, String consumerNo,String UniqueConumerId,String AllottedId,String category,String fieldType
                for(int i=0;i<answer.size();i++)
                {
                    ContentValues cv = new ContentValues();
                    cv.put(COL4_2,quesId); //These Fields should be your String values of actual column names
                    cv.put(COL4_3,quesDescription);
                    cv.put(COL4_12,categoryId);
                    cv.put(COL4_4,answer.toString());



                //TODO update condition here --- no categoryId
                long result=db.update(TABLE_NAME_ANSWERS, cv, "_id="+categoryId, null);

                    //long result=db.insert(TABLE_NAME_ANSWERS,null,contentValues);
                    System.out.print("ANSWER RESULT "+result);

                }



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

    public void moveEntryToDenied(Integer consumerNo)
    {


        ContentValues cv = new ContentValues();
        cv.put(COL3_12,"1"); //These Fields should be your String values of actual column names
        //cv.put("Field2","19");
        //cv.put("Field2","Male");


        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE_NAME_ALLOTMENT, cv, COL3_8+"="+consumerNo, null);
        Toast.makeText(context, "Updated Successfully as Denied", Toast.LENGTH_SHORT).show();


    }

    //CUSTOMIZATION LATER ON

  /*  public void setLayoutCount( int LAYOUT_CODE)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        int co = 0;
        Cursor cursor;

        switch (LAYOUT_CODE)
        {

        case Constants.TOTAL_ALLOTTED_PENDING:
         cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

        if(cursor.moveToFirst())
        {
            do {
            co++;
            }while(cursor.moveToNext());

        }
        contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
        contentValues.put("Count",co);
        db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
        contentValues.clear();

        break;


            case Constants.TOTAL_UNSAFE:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_UNSAFE);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;



            case Constants.TOTAL_DENIED:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_DENIED);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;



          *//*  case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;




            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;




            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;




            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;



            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;






            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;





            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();

                break;






            case Constants.TOTAL_ALLOTTED_PENDING:
                 cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ALLOTMENT,null);

                if(cursor.moveToFirst())
                {
                    do {
                        co++;
                    }while(cursor.moveToNext());
                }
                contentValues.put("id",Constants.TOTAL_ALLOTTED_PENDING);
                contentValues.put("Count",co);
                db.insert(TABLE_NAME_LAYOUT_COUNT,null,contentValues);
                contentValues.clear();
                break;
*//*    }


    }*/
}