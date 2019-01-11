package com.example.aadyam.mi.database;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //PersonalInfoList table

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

    ProgressDialog mProgressDialog;
    //private Activity activityContext;

    public DatabaseHelperUser(Context context)
    {
        super(context, /*Environment.getExternalStorageDirectory() + File.separator + */DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }



    public void createTables()
    {


        //getQuestion();
        //getQuestion();
        /*if(new MyGlobals(context).isNetworkConnected())
        {

            getAllotment();
        }
*/

    }


 /*   public void updateTables()
    {
        getAllotment();
        getQuestion();

    }
*/


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_TABLE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_DISTRIBUTOR);
        db.execSQL(SQL_CREATE_TABLE_ALLOTMENT_LIST);
        db.execSQL(SQL_CREATE_TABLE_PERSONAL_INFO);
        db.execSQL(SQL_CREATE_TABLE_UNSAFE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_ANSWERS);
        db.execSQL(SQL_CREATE_TABLE_PHOTOS);
        db.execSQL(SQL_CREATE_TABLE_LAYOUT_COUNT);
        //db.execSQL();
        //getAllotmentOnCreate();
        //getAllotmentBeta();

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
                    Log.d(Constants.TAG,""+response.body().getQuestionList().get(i).getDescription());
                    String s1,s2,s3,s4,s5,s6,s7,s8,s9;

                    if(response.body().getQuestionList().get(i).getActive().equalsIgnoreCase("true"))
                    {
                        s1 = response.body().getQuestionList().get(i).getActive();
                        s2 = response.body().getQuestionList().get(i).getCategoryId();
                        s3 = response.body().getQuestionList().get(i).getDescription();
                        s4 = response.body().getQuestionList().get(i).getDescriptionHindi();
                        s5 = response.body().getQuestionList().get(i).getDescriptionMarathi();
                        s6 = response.body().getQuestionList().get(i).getFieldData();
                        s7 = response.body().getQuestionList().get(i).getFieldType();
                        s8 = response.body().getQuestionList().get(i).getIsRemark();
                        s9 = response.body().getQuestionList().get(i).getQuestionId();
                        addQuestion(s1, s2, s3, s4, s5, s6, s7, s8, s9);

                    }

                }

                Log.d(Constants.TAG, "Questions Loaded!");

            }


            @Override
            public void onFailure(@NonNull Call<Question> call, @NonNull Throwable t)
            {
                Log.d(TAG, "ERROR FOUND :" + t.toString());
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

        //long result = db.insert(TABLE_NAME_QUESTION, null, contentValues);

        long result =  db.insertWithOnConflict(TABLE_NAME_QUESTION, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1)
        {
            db.update(TABLE_NAME_QUESTION, contentValues, COL1_9+"="+QuestionID,null/* new String[] {"1"}*/);  // number 1 is the _id here, update to variable for your code
        }


        Log.d(Constants.TAG,"QuestionInserted : " + result);



    }


//---------------------------------------------QUESTION SECTION ENDS---------------------------------------------------------










    //TODO ---------------------------------------------ALLOTMENT METHODS---------------------------------------------------------

    public void putAllotmentBeta(List<AllotmentList> list)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int size=list.size();

        ContentValues contentValues;

        for(int i=0;i<size;i++)
        {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_ANSWERS + " WHERE " + COL3_8 + "=" + list.get(i).getConsumerNo(), null);
            int c = cursor.getCount();

            contentValues= new ContentValues();

            contentValues.put(COL3_2, list.get(i).getInspFormId());
            contentValues.put(COL3_3, list.get(i).getAddress());
            contentValues.put(COL3_4, list.get(i).getAllottedDate());
            contentValues.put(COL3_5, list.get(i).getAreaName());
            contentValues.put(COL3_6, list.get(i).getAreaRefNo());
            contentValues.put(COL3_7, list.get(i).getConsumerName());
            contentValues.put(COL3_8, list.get(i).getConsumerNo());
            contentValues.put(COL3_9, list.get(i).getIsCompleted());
            contentValues.put(COL3_10, list.get(i).getIsUnsafe());
            contentValues.put(COL3_11, list.get(i).getIsUnsafeReallot());
            contentValues.put(COL3_12, list.get(i).getIsDenied());
            contentValues.put(COL3_13, list.get(i).getIsNotAvailable());
            contentValues.put(COL3_14, list.get(i).getIsPrevDenied());
            contentValues.put(COL3_15, list.get(i).getIsPrevNotAvailable());
            contentValues.put(COL3_16, list.get(i).getRefInspFormId());
            contentValues.put(COL3_17, list.get(i).getRefAllotmentId());
            contentValues.put(COL3_18, list.get(i).getMobileNo());
            contentValues.put(COL3_19, list.get(i).getUniqueConsumerId());
            contentValues.put(COL3_20, list.get(i).getAllotmentId());
            contentValues.put(COL3_21, list.get(i).getInspFormId());
            contentValues.put(COL3_21, list.get(i).getInspFormId());
            contentValues.put(COL3_25,list.get(i).getLastInspDate());
            contentValues.put(COL3_33,list.get(i).getIsSatisfactory());
            contentValues.put(COL3_34,list.get(i).getIsMobCompleted());
            //contentValues.put(COL3_35,list.get(i).getIsSatisfactory());
            contentValues.put(COL3_36,list.get(i).getIsSyncCompleted());
            contentValues.put(COL3_37,list.get(i).getIsInMobile());
            //contentValues.put(COL3_38,list.get(i).getIsSatisfactory());


            if (c == 1)
            {
                //long result = db.update(TABLE_NAME_ALLOTMENT, contentValues, COL3_20 + " = " + list.get(i).getAllotmentId(), null);
                long result = db.replace(TABLE_NAME_ALLOTMENT, null,contentValues);
                Log.i(Constants.TAG, "putAllotmentBeta" + result);

                if (result != -1)
                {
                    Log.d(Constants.TAG, "Updated" + list.get(i).getConsumerName());
                    Toast.makeText(context, "Updated!"+list.get(i).getConsumerName(), Toast.LENGTH_SHORT).show();
                }
            }


            else
            {
                long result=db.insert(TABLE_NAME_ALLOTMENT,  null,contentValues);
                Log.d(Constants.TAG,"ALLOTMENT RESULT "+result);

                if(result!=-1)
                {
                    Log.d(Constants.TAG, "Inserted!" + c);
                }

            }


        }

    }





    //method to abstract allotmentList from the retrofit body and pass to addQuestion method one by one
    public boolean getAllotment()
    {
        final boolean[] token = new boolean[1];
        ApiInterface apiInterface;
        apiInterface=ApiClient.getClient().create(ApiInterface.class);

        //AllotmentList?UserId=8193&StaffRefNo=11710819300000002&ConsumerCount=0
        Call<Allotment> call = apiInterface.getAllotmentList();

        call.enqueue(new Callback<Allotment>() {
            @Override
            public void onResponse(@NonNull Call<Allotment> call, @NonNull Response<Allotment> response)
            {
                // hideDialog();
                int size= 0;
                if (response.body() != null)
                {
                    size = response.body().getAllotmentListResult().size();
                }

                Toast.makeText(context, ""+size, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Connected. Fetched Allotment updates successfully! ", Toast.LENGTH_SHORT).show();

                assert response.body() != null;
                Log.d(Constants.TAG," getAllotment Entered : DEMO DATA RESPONSE : "+response.body().getAllotmentListResult().get(1).getAddress());

                assert response.body() != null;
                Random rand = new Random();

                // Generate random integers in range 0 to 999
                /*int inspectionNo = rand.nextInt(50000);
                int rand_int2 = rand.nextInt(50000);*/
                List<AllotmentList> list=response.body().getAllotmentListResult();
                putAllotmentBeta(list);
                token[0] =true;

            }

            @Override
            public void onFailure(@NonNull Call<Allotment> call, @NonNull Throwable t)
            {
                //hideDialog();
                token[0]=false;
                Toast.makeText(context, "No Response : "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return token[0];
    }





    //fetching allotment entries from the mobile SQLiteDatabase
    public List<AllotmentList> getAllotmentEntries(int CLICK_CODE)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        List<AllotmentList> allotmentLists = new ArrayList<AllotmentList>();

        Cursor cursor;

        switch (CLICK_CODE)
        {
            //TODO Apply logic here

            case Constants.TOTAL_ALLOTTED_PENDING:
                String selectQuery = "SELECT  * FROM " + TABLE_NAME_ALLOTMENT+" WHERE "+COL3_9+"=0 AND "+COL3_12+"=0 and "+COL3_13+"=0 and "+COL3_10+"=0";
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
                allotment.setLastInspDate(cursor.getString(24));

                allotmentLists.add(allotment);
//                Toast.makeText(context, ""+json, Toast.LENGTH_SHORT).show();

            } while (cursor.moveToNext());
        }

        // return contact list
        return allotmentLists;

    }


    //---------------------------------ALLOTMENT SECTION ENDS------------------------------------------------






    //TODO----------------------------------------PHOTOS TABLE-----------------------------------------------------

    public void setPhotos(ArrayList<String> filename,ArrayList<String> encodedImage,String[] CODE)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
        String UniqueNo=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
        String  AllottedId=sharedPreferences.getString(Constants.ALLOTED_ID,null);


        for(int i=0;i<filename.size();i++)
        {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL6_3,encodedImage.get(i));
            contentValues.put(COL6_5,filename.get(i));
            contentValues.put(COL6_9,UniqueNo);
            contentValues.put(COL6_10,AllottedId);
            contentValues.put(COL6_10,AllottedId);
            contentValues.put(COL6_11,CODE[i]);

            Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_PHOTOS+" WHERE "+COL6_9+"="+UniqueNo+" AND "+COL6_11+"="+CODE[i],null);
            int count=cursor.getCount();

            if(count==1)
            {
                long result=db.update(TABLE_NAME_PHOTOS,contentValues,""+COL6_9+"="+UniqueNo+" AND "+COL6_11+"="+CODE[i],null);
                if(result!=-1)
                    Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                Log.d(Constants.TAG, "Updated!");
                //long result1=db.replace(TABLE_NAME_PHOTOS,null,contentValues);
            }

            else
            {
                long result = db.insert(TABLE_NAME_PHOTOS, null, contentValues);
                if(result!=-1)
                    Toast.makeText(context, "Inserted!", Toast.LENGTH_SHORT).show();
                Log.d(Constants.TAG, "Inserted!");

            }

        }
        //id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,filestream TEXT, distributor_id TEXT, filename TEXT,Inspectionformid TEXT,ref_Inspectionformid TEXT,unsafe_id TEXT,uniqueConsumerId TEXT,allotedId TEXT,imageType TEXT,json_data TEXT);";
    }



    //retrieves base64string from database w.r.t imageviewCODE and allotedId
    public String getPhotoEntry(int imageViewCode,String allottedId)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        String getQuery="SELECT * FROM "+ TABLE_NAME_PHOTOS +" WHERE "+COL6_11+"="+imageViewCode+" AND "+COL6_10+"="+allottedId;
        Cursor cursor=db.rawQuery(getQuery,null);
        String imageString;

        if(cursor.getCount()==1)
            return cursor.getString(2);

        else
            return null;

    }



    //inserts induvidual answer entry into Database
    public void putAnswerEntryInDatabase(String answer,String questionId,String quesDescription,String categoryId,String allottedDate,String areaName,String consumerName, String consumerNo,String isCompleted,String uniqueConsumerId,String allottedId)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_ANSWERS+" WHERE "+COL4_8+"="+consumerNo+" AND "+COL4_2+"="+questionId ,null);

        int c=cursor.getCount();

        ContentValues cv = new ContentValues();
        cv.put(COL4_2, questionId);
        cv.put(COL4_3, quesDescription);
        cv.put(COL4_12, categoryId);
        cv.put(COL4_4, answer);
        cv.put(COL4_6, areaName);
        cv.put(COL4_7, consumerName);
        cv.put(COL4_5,allottedDate);
        cv.put(COL4_8, consumerNo);
        cv.put(COL4_9,isCompleted);
        cv.put(COL4_10, uniqueConsumerId);
        cv.put(COL4_11, allottedId);

        System.out.println("Before update Count : "+count);

        if(c==1)
        {
            long result=db.update(TABLE_NAME_ANSWERS,  cv,COL2_2+" = "+consumerNo+" AND "+COL4_2+" = "+questionId,null);
            Log.i(Constants.TAG,""+result);
            if(result!=-1)
            {
                Log.i(Constants.TAG,"Updated");
                //Toast.makeText(context, "Updated!"+c, Toast.LENGTH_SHORT).show();

            }

        }


        else
        {
            long result=db.insert(TABLE_NAME_ANSWERS,  null,cv);
            Log.i(Constants.TAG,"ANSWER RESULT "+result);
            if(result!=-1)
            {
                Log.i(Constants.TAG,"Inserted!");
            }
        }
    }


    //temporarily updates the allotment entry to denied till reflected to server
    public void moveEntryToDenied(Integer consumerNo)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL3_12,"1");
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE_NAME_ALLOTMENT, cv, COL3_8+"="+consumerNo, null);
        Toast.makeText(context, "Updated Successfully as Denied", Toast.LENGTH_SHORT).show();
    }


    //temporarily updates the allotment entry to not Available till reflected to server
    public void moveEntryToNotAvailable(Integer consumerNo)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL3_13,"1");
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE_NAME_ALLOTMENT, cv, COL3_8+"="+consumerNo, null);
        Log.i(Constants.TAG, "Updated Successfully as Not Available");
    }




    //Stores the personalinfo table with the infromation gathered from the respective user
    public boolean putInformationEntryInDatabase(String dateofbirth, String vip,String gender,String usingcreditcardcheckbox,String motherName ,String highConsumerCheckBox ,String fatherName, String refillGap ,String fatherSpouseName, String bookRefill, String familyMember ,String typeOfHouse ,String rationCardAffidavit ,String twoWheeler ,String affidavitDate ,String fourWheeler ,String rationCardNo ,String pipeGas ,String panCardNo,String passportNo ,String voterId ,String licenseNo,String mobileNo ,String emailId , String consumerNo)
    {
        //boolean isSuccess=false;

        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
        String uniqueConsumerId=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
        consumerNo=sharedPreferences.getString(Constants.CONSUMER_NO,null);

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_PERSONAL_INFO+" WHERE "+COL5_2+"="+uniqueConsumerId,null);

        //unique_consumer_id TEXT,allotedid TEXT,inspectionid TEXT, dateofbirth TEXT, vip TEXT,gender TEXT,usingcreditcard TEXT,mothername TEXT,highconsumer TEXT,father TEXT,refills TEXT,fatherspousename TEXT,book_refill TEXT, familymember TEXT, typeofhouse TEXT,rationcard TEXT,two_wheeler TEXT,affidavitdate TEXT,four_wheeler TEXT,rationcardno TEXT,pipegas TEXT,pancardno TEXT,passportno TEXT,voterid TEXT,licenseno TEXT,mobileno TEXT,emailid TEXT,json_data TEXT)
        int c=cursor.getCount();

        //unique_consumer_id TEXT,allotedid TEXT,inspectionid TEXT, dateofbirth TEXT, vip TEXT,gender TEXT,usingcreditcard TEXT,mothername TEXT,highconsumer TEXT,father TEXT,refills TEXT,fatherspousename TEXT,book_refill TEXT, familymember TEXT, typeofhouse TEXT,rationcard TEXT,two_wheeler TEXT,affidavitdate TEXT,four_wheeler TEXT,rationcardno TEXT,pipegas TEXT,pancardno TEXT,passportno TEXT,voterid TEXT,licenseno TEXT,mobileno TEXT,emailid TEXT,json_data TEXT)
        ContentValues cv = new ContentValues();

        JSONObject jsonObject=new JSONObject();
        try
        {
            jsonObject.put("VIP",vip);
            jsonObject.put("Gndr",gender);
            jsonObject.put("CCrd",usingcreditcardcheckbox);
            jsonObject.put("MMNme",motherName);
            jsonObject.put("HCC",highConsumerCheckBox);
            jsonObject.put("FSp",fatherSpouseName);
            jsonObject.put("Gp2Refil",refillGap);
            jsonObject.put("Hwfil",bookRefill);
            jsonObject.put("NoOfFM",familyMember);
            jsonObject.put("TypOfHA",typeOfHouse);
            jsonObject.put("RtCrdAff",rationCardAffidavit);
            jsonObject.put("TWhelr",twoWheeler);
            jsonObject.put("AffDt",affidavitDate);
            jsonObject.put("FWhelr",fourWheeler);
            jsonObject.put("RCNo",rationCardNo);
            jsonObject.put("UsePipGs",pipeGas);
            jsonObject.put("PCNo",panCardNo);
            jsonObject.put("PsprtNo",passportNo);
            jsonObject.put("VCNo",voterId);
            jsonObject.put("DriLicNo",licenseNo);
            jsonObject.put("MobNo",mobileNo);
            jsonObject.put("EmailId",emailId);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
        //vip, gender, using credit_card,mother_name,high_consumption,father_spouse_name,refill_gap,booking_mode,family_member_count,accomodation,ration_affidavit,two_wheeler,four_wheeler,ration_card_no,using_piped_gas,pan_card_no,passport,voter_id,license,mobileNo,email

        String jsonData=jsonObject.toString();

        // cv.put(COL5_1,sharedPreferences.getString(Constants) );
        cv.put(COL5_2,uniqueConsumerId );

        cv.put(COL5_3, sharedPreferences.getInt(Constants.INSPECTION_ID,0));
        cv.put(COL5_4, dateofbirth);
        cv.put(COL5_5, vip);
        cv.put(COL5_6, gender);
        cv.put(COL5_7, usingcreditcardcheckbox);
        cv.put(COL5_8, motherName);
        cv.put(COL5_9, highConsumerCheckBox);
        cv.put(COL5_10, fatherName);
        cv.put(COL5_11, refillGap);
        cv.put(COL5_12, fatherSpouseName);
        cv.put(COL5_13, bookRefill);
        cv.put(COL5_14, familyMember);
        cv.put(COL5_15, typeOfHouse);
        cv.put(COL5_16, rationCardAffidavit);
        cv.put(COL5_17, twoWheeler);
        cv.put(COL5_18, affidavitDate);
        cv.put(COL5_19, fourWheeler);
        cv.put(COL5_20, rationCardNo);
        cv.put(COL5_21, pipeGas);
        cv.put(COL5_22, panCardNo);
        cv.put(COL5_23, passportNo);
        cv.put(COL5_24, voterId);
        cv.put(COL5_25, licenseNo);
        cv.put(COL5_26, mobileNo);
        cv.put(COL5_27, emailId);
        cv.put(COL5_28, jsonData);

        System.out.println("Before update Count : "+count);

        if(c==1)
        {
            long result=db.update(TABLE_NAME_PERSONAL_INFO,  cv,COL5_2+" = "+uniqueConsumerId,null);
            Log.i(Constants.TAG,""+result);

            if(result!=-1)
            {
                Log.d(Constants.TAG,"Updated"+c);
                ///isSuccess=true;
                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                return true;
            }
            else
            {
                return false;
            }
        }

        else
        {
            long result=db.insert(TABLE_NAME_PERSONAL_INFO,  null,cv);
            Log.d(Constants.TAG,"INFORMATION RESULT "+result);

            if(result!=-1)
            {
                Toast.makeText(context, "Inserted!", Toast.LENGTH_SHORT).show();
                Log.d(Constants.TAG, "Inserted!"+c);
                return true;
            }

            else {
                return false;
            }
        }

    }


    //puts the extra dynamic information into the allotmentList table in database, which is needed to send to server along with ConsumerInfo JSONString
    public void putExtraAllotedUserData(String alloted_id,String inspection_no,String instruction, double latitude, double longitude)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL3_22,instruction);
        cv.put(COL3_23,longitude);
        cv.put(COL3_24,latitude);

        long result = db.update(TABLE_NAME_ALLOTMENT,cv,""+COL3_20+"="+alloted_id,null);

        if(result!=1)
        {
            Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
            Log.d(Constants.TAG,"Updated!");
        }


    }


    //converts the parameters into a JSON Array string to POST to server
    public String getConsumerJsonString(String allottedId,double latitude,double longitude,String instruction,String dateString)
    {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();

        try
        {
            jsonObject.put("AltId",allottedId);
            jsonObject.put("Lat",latitude);
            jsonObject.put("Long",longitude);
            jsonObject.put("Stisfy",true);
            jsonObject.put("Rmrk",instruction);
            jsonObject.put("IDt",dateString);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        jsonArray.put(jsonObject);

        return jsonArray.toString();
    }



    //get the json_data entry of the respective user from allotmentList table in database
    public String getPersonalJsonString(String uniqueNo)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME_PERSONAL_INFO+" where "+COL5_2+" = "+uniqueNo,null);

        if (cursor.moveToFirst()){
            do{
                return cursor.getString(28);
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();

        return null;
    }



    //fetch all QuestionAnswer entries of respective user from answers table in database
    public String getAnswerJsonString(String unique)
    {
        SQLiteDatabase db=this.getWritableDatabase();



        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME_ANSWERS+" where "+COL4_10+"="+unique,null);
        JSONArray jsonArrayQuestions=new JSONArray();
        if(cursor.moveToFirst())
        {


            do
            {

                try
                {
                    JSONObject jsonObjectAnswers=new JSONObject();
                    jsonObjectAnswers.put("QId",cursor.getString(1));
                    jsonObjectAnswers.put("Ans",cursor.getString(3));
                    jsonArrayQuestions.put(jsonObjectAnswers);
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }



            }while (cursor.moveToNext());


        }

        Log.d(Constants.TAG,"jsonArrayQuestions@@@@@@@@" + jsonArrayQuestions);
        return jsonArrayQuestions.toString();
    }

    public void deleteAllTableEntries(String allotted_id, String uniqueNo)
    {


    }
}