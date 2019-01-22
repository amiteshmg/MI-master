package com.example.aadyam.mi.Utils;

import android.content.Context;
import android.os.Bundle;

public class Constants
{
    public static final String PREFS_NAME ="MI_PREF" ;
    public static final int TOTAL_ALLOTTED_PENDING = 1000;
    public static final int TOTAL_UNSAFE = 1001;
    public static final int TOTAL_DENIED = 1002;
    public static final int TOTAL_NOT_AVAILABLE  = 1003;
    public static final int TOTAL_REALLOTTED_UNSAFE = 1004;
    public static final int TOTAL_REALLOTTED_DENIED = 1005;
    public static final int TOTAL_REALLOTTED_NOT_AVAILABLE = 1006;
    public static final int TOTAL_TOTAL = 1007;
    public static final int TOTAL_AGAINST_UNSAFE = 1008;
    public static final int TOTAL_AGAINST_DENIED = 1009;
    public static final int TOTAL_AGAINST_NOT_AVAILABLE = 1010;
    public static final String CLICK_CODE ="Click_code" ;
    public static final String CONSUMER_NO = "ConsumerNo";
    public static final String AREA_NAME ="AreaName" ;
    public static final String CONSUMER_NAME ="ConsumerName" ;
    public static final String ALLOTMENT_DATE = "AllotmentDate";
    public static final String UNIQUE_CONSUMER_NO ="UniqueConsumerNo" ;
    public static final String IS_COMPLETED = "isCompleted";
    public static final String ALLOTED_ID = "allottedId";
    public static final String ANSWER = "Answer";
    public static final String PREF_CONSUMER_DETAILS = "ConsumerPrefCurrent";
    public static final String MOBILE_NO = "mobileNo";
    public static final String FIRST_RUN = "firstRun";
    public static final String ARRAY_LENGTH = "arrayLength";
    public static final String TAG = "MI_debugData";
    public static final String INSPECTION_ID = "InspectionId";
    public static final int SIGNATURE = 5;
    public static final String FRAG_TYPE = "fragmentType";
    public static final String CYLINDER_SAVE = "cylinderSave";
    public static final String REGULATOR_SAVE = "regulatorSave";
    public static final String RUBBER_HOSE_SAVE = "rubberHoseSave";
    public static final String STOVE_SAVE  = "stoveSave";
    public static final String GENERAL_SAVE = "generalSave";
    public static final String IMAGE_ARRAY = "imageArray";


    public static  String SERVER_URL="http://hpgasprodweb1t.hpcl.co.in/hp.svc/QuestionnaireList";
    public static  String JSON_OBJECT="QuestionnaireListResult";
    public static final String Time_Out_Exception = "Timeout Exception!";
    public static final String Server_Error = "Server Error";
    public static final String OK = "OK";
    public static final String warning="Warning";
    public static long StaffRefNo;

    public static String FONT_REGULAR = "fonts/OpenSans-Regular.ttf";
    public static String FONT_BOLD = "fonts/OpenSans-Bold.ttf";

    //Live Server

    /* public static final String ServerUrl = "http://lpgmandatoryinspection.hpcl.co.in/MandatoryInspectionWebService/HP.svc/";
    public static final String ServerUrl_Soap = "http://lpgmandatoryinspection.hpcl.co.in/MandatoryInspectionWebService/UploadFilesService.asmx";
    */
    //Local server

   /*public static final String ServerUrl = "http://192.168.2.21/HP.svc/";
    public static final String ServerUrl_Soap = "http://http://192.168.2.21/UploadFilesService.asmx";*/



    //Test Aadyam server

  /*public static final String ServerUrl = "http://aclpune.com/HPCLINSPSERVICE/HP.svc/";
    public static final String ServerUrl_Soap = "http://aclpune.com/HPCLINSPSERVICE/UploadFilesService.asmx";
*/

 // UAT server
    private static final String ServerUrl = "http://hpgasprodweb1t.hpcl.co.in/hp.svc/";
    public static final String ServerUrl_Soap = "http://hpgasprodweb1t.hpcl.co.in/uploadfilesservice.asmx";

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String AllotmentList = ServerUrl + "AllotmentList?";
    public static final String QuestionnaireList = ServerUrl + "QuestionnaireList";
    public static final String SendOTPSMS = ServerUrl + "SendOTPSMS?";
    public static final String NAMESPACE = "http://tempuri.org/";

    //Sandeep
    public static final String ActivateUserMechanic = ServerUrl + "ActivateUserMechanic?";
    public static final String InspectionDenied = ServerUrl + "InspectionDenied?";

    public static final String InspCompletedFlagInMobile = ServerUrl + "InspCompletedFlagInMobile?";
    public static final String GET_UNSAFE_QUESTIONNAIRE = ServerUrl + "UnsafeQueListForAllInspId?";

    public static final String IS_IN_MOBILE_METHOD_PostFile = "SetIsInMobileFlag";
    public static final String IS_IN_MOBILE_SOAP_ACTION_PostFile = "http://tempuri.org/SetIsInMobileFlag";

    public static final String LOG_ANALYTICS_METHOD_PostFile = "Analytics";
    public static final String LOG_ANALYTICS_SOAP_ACTION_PostFile = "http://tempuri.org/Analytics";

    public static final String METHOD_INSPECTION_DATA_PostFile = "InspectionData";
    public static final String SOAP_ACTION_INSPECTION_DATA = "http://tempuri.org/InspectionData";

    public static final String METHOD_UNSAFE_INSPECTION_DATA_PostFile = "UnsafeInspectionData";
    public static final String SOAP_ACTION_UNSAFE_INSPECTION_DATA = "http://tempuri.org/UnsafeInspectionData";
    public static final String GetReAllotment = ServerUrl + "GetReAllotment?";  // Delete reallocated ids from consumer table


    public static final int CYLINDER_FRAG_CODE=1;
    public static final int REGULATOR_FRAG_CODE=2;
    public static final int RUBBER_HOSE_FRAG_CODE=3;
    public static final int STOVE_FRAG_CODE=4;
    public static final int GENERAL_FRAG_CODE=5;
    public static final int PERSONAL_INFO_FRAG_CODE=6;
    public static final int UPLOAD_PHOTO_FRAG_CODE=7;


    public static int ConsumerCount;
    public static int DistributorId;
    public static String strTrue="true";
    public static boolean intentFlag=false;
    static String loginFlag="loginFlag";
    public static String Otp = "otp";
    public static int AllotmentCount;
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;


    public static final int REGULATOR_CODE = 1;
    public static final int STOVE_CODE = 2;
    public static final int HOSE_CODE = 3;
    public static final int INSTALLATION_CODE = 4;


    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;

    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    public static final String VIDEO_EXTENSION = "mp4";

    public static String imageStoragePath;

    public static Bundle savBundle;





}
