package com.example.aadyam.mi.rest;



import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.DeniedInspection;
import com.example.aadyam.mi.model.Distributor;
import com.example.aadyam.mi.model.InspectionDataList;
import com.example.aadyam.mi.model.PersonalInfoList;
import com.example.aadyam.mi.model.Question;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface
{

    /*@GET("SendOTPSMS?MobileNo=9503232500")
    Call<Distributor>getDistributorDetails();
*/

    @GET("SendOTPSMS")
    Call<Distributor>getDistributorDetails(@Query("MobileNo") String number);

    @GET("QuestionnaireList")
    Call<Question> getQuestionDetails();

   /* @POST("/InspectionData")
    @FormUrlEncoded
    Call<InspectionDataList> savePost(@Field("title")JsonObject allotment,
                                      @Field("body") String body,
                                      @Field("userId") long userId);
*/
    @GET("InspectionDenied")
    Call<DeniedInspection>denyInspection(@Query("AllotmentId") String AllotmentId, @Query("Deniedflag") int Deniedflag, @Query("notAvailableflag") int notAvailableflag);//?AllotmentId={ALLOTMENTID}&Deniedflag={DENIEDFLAG}&notAvailableflag={NOTAVAILABLEFLAG}

    @GET("AllotmentList?DistributorId=8193&StaffRefNo=11710819300000002&ConsumerCount=100")
    Call<Allotment>getAllotmentList();


    @GET("UnsafeQueListForAllInspId?InspFormId=6517")
    Call<Question> getUnsafeQuestionDetails(/*@Query("InspFormId")String inspectionFormId*/);
    /*@GET("AllotmentList")
    Call<Allotment>getAllotmentList(@Query("DistributorId") int Dist_id,@Query("StaffRefNo") String Staff_Ref_No,@Query("StaffRefNo") int Cons_count);
    */

    //synchronize
}


