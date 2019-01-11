package com.example.aadyam.mi;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.soap.InspectionDataSoapHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class InspectionDataService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.aadyam.mi.action.FOO";
    private static final String ACTION_BAZ = "com.example.aadyam.mi.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.aadyam.mi.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.aadyam.mi.extra.PARAM2";

    public Context mcontex;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;
    ArrayList<String> allotedIdList = new ArrayList<>();
    JSONObject jsonObjBasicInfo;
    JSONObject jsonObjPersonalInfo;
    JSONArray jsonArrPersonalInfo;
    JSONArray jsonArrayBasicInfo;
    JSONArray jsonArrayQuestions;
    String questionsString = "";
    String consumerString, personalInfoString = "";
    String inspdate;
    //ArrayList<LogAllModel> logArrayList = new ArrayList<>();



    public InspectionDataService() {
        super("InspectionDataService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, InspectionDataService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, InspectionDataService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }



  /*  private void inspectionWebService() {

        try
        {
            String strAllotmentId = "";
            mcontex = InspectionDataService.this;
            //allotedIdList = QuestionAnswerDB.getInstance(this).getAllotedId();
            // System.out.println("consumerAllotedId = " + allotedIdList);

            InspectionDataSoapHelper helper = new InspectionDataSoapHelper();

            for (int i = 0; i < allotedIdList.size(); i++) {

                strAllotmentId = allotedIdList.get(i);



                if (
                        *//*CustomerListDB.getInstance(InspectionDataService.this).getMobileCompleted(strAllotmentId*//*)) {

                    // Mobile completed flag service
                    AQuery aQuery = new AQuery(this);
                    String url = Constants.InspCompletedFlagInMobile + "AllotmentId=" + strAllotmentId + "&" + "IsCompleteFlag=" + "1";
                    Date currentTime = Calendar.getInstance().getTime();
                    String date = currentTime.toString();
                    //Constants.printResponseLog(InspectionDataService.this, " InspCompletedFlagInMobile Service - " + "AllotmentId=" + strAllotmentId, "Request -", date.toString(), " Seq - 001");
                    aQuery.ajax(url, JSONObject.class, 60000, new AjaxCallback<JSONObject>() {
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
                            timeout(60000);
                            System.out.println("ResponseInspectionMobileFlag" + object);
                            if (object != null)
                            {
                                Date currentTime = Calendar.getInstance().getTime();
                                String date = currentTime.toString();
                                // Constants.printResponseLog(InspectionDataService.this, " InspCompletedFlagInMobile Service - " + "", "Response - Success", date.toString(), " Seq - 001");
                            }
                        }
                    });


                    //Consumer Info
                    TodayAllotedList todayAllotedList = CustomerListDB.getInstance(this).getTodayAllotedList(strAllotmentId);

                    jsonArrayBasicInfo = new JSONArray();
                    jsonObjBasicInfo = new JSONObject();
                    try {

                        String strSatisfy = todayAllotedList.getIsSatisfactory();
                        if (strSatisfy.equalsIgnoreCase("") || strSatisfy.equalsIgnoreCase("null") || strSatisfy.equalsIgnoreCase(null)) {

                            strSatisfy = "true";

                        } else {

                            strSatisfy = todayAllotedList.getIsSatisfactory();
                        }

                        String strInsDate = todayAllotedList.getLastinspectiondate();
                        if (strInsDate.equalsIgnoreCase("") || strInsDate.equalsIgnoreCase("null") || strInsDate.equalsIgnoreCase(null)) {

                            strInsDate = inspdate;

                        } else {

                            strInsDate = todayAllotedList.getLastinspectiondate();
                        }

                        jsonObjBasicInfo.put("AltId", strAllotmentId);
                        jsonObjBasicInfo.put("Lat", todayAllotedList.getLatitude());
                        jsonObjBasicInfo.put("Long", todayAllotedList.getLongitude());
                        jsonObjBasicInfo.put("Stisfy", strSatisfy);
                        jsonObjBasicInfo.put("Rmrk", todayAllotedList.getInstruction());
                        jsonObjBasicInfo.put("IDt", strInsDate);

                        jsonArrayBasicInfo.put(jsonObjBasicInfo);
                        consumerString = jsonArrayBasicInfo.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();

                        try {

                            LogAllModel logDetailModel = new LogAllModel();
                            logDetailModel.setLogPriority("High");
                            logDetailModel.setLogFor("Exception");
                            logDetailModel.setLogClass("Inspection Data Service");
                            logDetailModel.setLog_method("Consumer Info");
                            logDetailModel.setLog_data("");
                            logDetailModel.setLog_status("");
                            logDetailModel.setLogSubmited("");
                            logDetailModel.setException(e.toString());
                            logDetailModel.setLogKey("");
                            logArrayList.add(logDetailModel);
                            new Logger(InspectionDataService.this).saveLog(logArrayList);

                        } catch (Exception ex) {

                            ex.printStackTrace();
                            System.out.println("Exception@@" + ex);
                        }
                    }


                    // Personnal Info
                    PersonalInfoList personalInfoLists = PersonalnfoDB.getInstance(this).getPersonalInformationList(strAllotmentId);
                    jsonObjPersonalInfo = new JSONObject();
                    jsonArrPersonalInfo = new JSONArray();

                    try {

                        if (personalInfoLists != null) {

                            System.out.println("DateOfBirthFormat@@@" + personalInfoLists.getDateofbirth());
                            String strBirthDate = personalInfoLists.getDateofbirth().trim();
                            DateValidator dateValidator = new DateValidator();

                            if (dateValidator.validate(strBirthDate)) {

                                jsonObjPersonalInfo.put("DOB", personalInfoLists.getDateofbirth());

                            } else {

                                String dateOfBirth = personalInfoLists.getDateofbirth().substring(0, 4);
                                if (!dateOfBirth.contains("-")) {

                                    if (Integer.parseInt(dateOfBirth) > 1899) {
                                        jsonObjPersonalInfo.put("DOB", personalInfoLists.getDateofbirth());
                                    } else {
                                        String dateString = personalInfoLists.getDateofbirth().replace(dateOfBirth, "1975");
                                        System.out.println("New Date String == " + dateString);
                                        jsonObjPersonalInfo.put("DOB", dateString);
                                    }

                                } else {

                                    jsonObjPersonalInfo.put("DOB", personalInfoLists.getDateofbirth());

                                }


                            }


                            // AFFIDIVATE DATE

                            String strAffDate = personalInfoLists.getAffidavitdate().trim();
                            if (dateValidator.validate(strAffDate)) {

                                strAffDate = personalInfoLists.getAffidavitdate();
                            } else {

                                if (strAffDate.contains("/")) {

                                    strAffDate = strAffDate.replace("/", "-");
                                }
                            }

                            jsonObjPersonalInfo.put("VIP", personalInfoLists.getVip());
                            jsonObjPersonalInfo.put("Gndr", personalInfoLists.getGender());
                            jsonObjPersonalInfo.put("CCrd", personalInfoLists.getUsingcreditcard());
                            jsonObjPersonalInfo.put("MMNme", personalInfoLists.getMothername());
                            jsonObjPersonalInfo.put("HCC", personalInfoLists.getHighconsumer());
                            jsonObjPersonalInfo.put("FSp", personalInfoLists.getFatherspousename());
                            jsonObjPersonalInfo.put("Gp2Refil", personalInfoLists.getRefills());
                            jsonObjPersonalInfo.put("Hwfil", personalInfoLists.getBook_refill());
                            jsonObjPersonalInfo.put("NoOfFM", personalInfoLists.getFamilymember());
                            jsonObjPersonalInfo.put("TypOfHA", personalInfoLists.getTypeofhouse());
                            jsonObjPersonalInfo.put("RtCrdAff", personalInfoLists.getRationcard());
                            jsonObjPersonalInfo.put("TWhelr", personalInfoLists.getTwo_wheeler());
                            jsonObjPersonalInfo.put("AffDt", strAffDate);
                            jsonObjPersonalInfo.put("FWhelr", personalInfoLists.getFour_wheeler());
                            jsonObjPersonalInfo.put("RCNo", personalInfoLists.getRationcardno());
                            jsonObjPersonalInfo.put("UsePipGs", personalInfoLists.getPipegas());
                            jsonObjPersonalInfo.put("PCNo", personalInfoLists.getPancardno());
                            jsonObjPersonalInfo.put("PsprtNo", personalInfoLists.getPassportno());
                            jsonObjPersonalInfo.put("VCNo", personalInfoLists.getVoterid());
                            jsonObjPersonalInfo.put("DriLicNo", personalInfoLists.getLicenseno());
                            jsonObjPersonalInfo.put("MobNo", personalInfoLists.getMobileno());
                            jsonObjPersonalInfo.put("EmailId", personalInfoLists.getEmailid());

                            jsonArrPersonalInfo.put(jsonObjPersonalInfo);
                            personalInfoString = jsonArrPersonalInfo.toString();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();

                        try {

                            LogAllModel logDetailModel = new LogAllModel();
                            logDetailModel.setLogPriority("High");
                            logDetailModel.setLogFor("Exception");
                            logDetailModel.setLogClass("Inspection Data Service");
                            logDetailModel.setLog_method("Personal Info");
                            logDetailModel.setLog_data("");
                            logDetailModel.setLog_status("");
                            logDetailModel.setLogSubmited("");
                            logDetailModel.setException(e.toString());
                            logDetailModel.setLogKey("");
                            logArrayList.add(logDetailModel);
                            new Logger(InspectionDataService.this).saveLog(logArrayList);

                        } catch (Exception ex) {

                            ex.printStackTrace();
                            System.out.println("Exception@@" + ex);
                        }
                    }

                    //questions string
                    ArrayList<QuestionAnswerList> queAnsList = QuestionAnswerDB.getInstance(this).getAnswerList(strAllotmentId);
                    System.out.println("queAnsList @@" + queAnsList);

                    jsonArrayQuestions = new JSONArray();
                    JSONObject jsonObjectQuestions = null;

                    for (int j = 0; j < queAnsList.size(); j++) {
                        try {
                            jsonObjectQuestions = new JSONObject();
                            jsonObjectQuestions.put("QId", queAnsList.get(j).getQuestionid());
                            jsonObjectQuestions.put("Ans", queAnsList.get(j).getAnswers());

                            jsonArrayQuestions.put(jsonObjectQuestions);
                            System.out.println("jsonArrayQuestions@@@@@@@@" + jsonArrayQuestions);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Exception Question Upload Record");

                            try {

                                LogAllModel logDetailModel = new LogAllModel();
                                logDetailModel.setLogPriority("High");
                                logDetailModel.setLogFor("Exception");
                                logDetailModel.setLogClass("Inspection Data Service");
                                logDetailModel.setLog_method("Upload Question");
                                logDetailModel.setLog_data("");
                                logDetailModel.setLog_status("");
                                logDetailModel.setLogSubmited("");
                                logDetailModel.setException(e.toString());
                                logDetailModel.setLogKey("");
                                logArrayList.add(logDetailModel);
                                new Logger(InspectionDataService.this).saveLog(logArrayList);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Exception@@" + ex);
                            }
                        }
                    }

                    questionsString = jsonArrayQuestions.toString();
                    System.out.println("strQueAns=" + questionsString);


                    // Upload Images
                    ArrayList<PhotoList> photoLists = PhotoDB.getInstance(this).getImagesList(strAllotmentId);
                    ArrayList<byte[]> imageByteList = new ArrayList<>();
                    ArrayList<String> photoNameList = new ArrayList<String>();

                    for (int j = 0; j < photoLists.size(); j++) {
                        try {

                            String strFile = String.valueOf(photoLists.get(j));

                            if (strFile.contains("/storage")) {

                                *//* Get file pathfrom table photos *//*
                                try {
                                    Bitmap bm = BitmapFactory.decodeFile(photoLists.get(j).getFilestream());
                                    if (bm != null) {
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        bm.compress(Bitmap.CompressFormat.PNG, 65, baos); //bm is the bitmap object
                                        byte[] imageByteArr = baos.toByteArray();
                                        if (imageByteArr != null) {
                                            imageByteList.add(j, imageByteArr);
                                            photoNameList.add(j, photoLists.get(j).getFilename());
                                        }


                                    }
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            } else {

                                *//*get string from photo table*//*
                                Bitmap bitmap = null;
                                String string = photoLists.get(j).getFilestream();
                                if (string != null) {
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    try {
                                        byte[] encodeByte = Base64.decode(string, Base64.DEFAULT);
                                        bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                                        bitmap.compress(Bitmap.CompressFormat.PNG, 65, baos); //bm is the bitmap object
                                        byte[] imageByteArr = baos.toByteArray();

                                        imageByteList.add(j, imageByteArr);
                                        photoNameList.add(j, photoLists.get(j).getFilename());

                                    } catch (Exception e) {
                                        e.getMessage();
                                    }
                                }
                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                            try {
                                LogAllModel logDetailModel = new LogAllModel();
                                logDetailModel.setLogPriority("High");
                                logDetailModel.setLogFor("Exception");
                                logDetailModel.setLogClass("Inspection Data Service");
                                logDetailModel.setLog_method("Upload Images");
                                logDetailModel.setLog_data("");
                                logDetailModel.setLog_status("");
                                logDetailModel.setLogSubmited("");
                                logDetailModel.setException(e.toString());
                                logDetailModel.setLogKey("");
                                logArrayList.add(logDetailModel);
                                new Logger(InspectionDataService.this).saveLog(logArrayList);

                            }catch(Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Exception@@" + ex);
                            }
                        }


                    }

                    LinkedHashMap recordHashMap = new LinkedHashMap<>();

                    recordHashMap.put("ConsumerInfo", consumerString);
                    recordHashMap.put("PersonalInfo", personalInfoString);
                    recordHashMap.put("QuestionsInfo", questionsString);

                    if (photoNameList.size() > 4) {

                        recordHashMap.put("Img1", photoNameList.get(1));
                        recordHashMap.put("Img2", photoNameList.get(2));
                        recordHashMap.put("Img3", photoNameList.get(3));
                        recordHashMap.put("Img4", photoNameList.get(4));
                        recordHashMap.put("Img5", photoNameList.get(0));
                    }
                   *//* else {
                        recordHashMap.put("Img1", "");
                        recordHashMap.put("Img2", "");
                        recordHashMap.put("Img3","");
                        recordHashMap.put("Img4", "");
                        recordHashMap.put("Img5","");
                    }*//*

                    if (imageByteList.size() > 4) {

                        recordHashMap.put("img1Byt", imageByteList.get(1));
                        recordHashMap.put("img2Byt", imageByteList.get(2));
                        recordHashMap.put("img3Byt", imageByteList.get(3));
                        recordHashMap.put("img4Byt", imageByteList.get(4));
                        recordHashMap.put("img5Byt", imageByteList.get(0));
                    }
                   *//* else {
                        recordHashMap.put("img1Byt", "");
                        recordHashMap.put("img2Byt", "");
                        recordHashMap.put("img3Byt", "");
                        recordHashMap.put("img4Byt", "");
                        recordHashMap.put("img5Byt", "");
                    }*//*

                    helper.getSoapRequest(InspectionDataService.this, ConfigPG.NAMESPACE, ConfigPG.METHOD_INSPECTION_DATA_PostFile, ConfigPG.ServerUrl_Soap, ConfigPG.SOAP_ACTION_INSPECTION_DATA, recordHashMap);

                }
            }


        } catch (Exception e) {

            e.printStackTrace();

            try {
                LogAllModel logDetailModel = new LogAllModel();
                logDetailModel.setLogPriority("High");
                logDetailModel.setLogFor("Exception");
                logDetailModel.setLogClass("Inspection Data Service");
                logDetailModel.setLog_method("InspectionData");
                logDetailModel.setLog_data("");
                logDetailModel.setLog_status("");
                logDetailModel.setLogSubmited("");
                logDetailModel.setException(e.toString());
                logDetailModel.setLogKey("");
                logArrayList.add(logDetailModel);
                new Logger(InspectionDataService.this).saveLog(logArrayList);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Exception@@" + ex);
            }
        }

    }*/
}
