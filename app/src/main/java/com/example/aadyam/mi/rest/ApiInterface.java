package com.example.aadyam.mi.rest;



import com.example.aadyam.mi.model.Allotment;
import com.example.aadyam.mi.model.AllotmentList;
import com.example.aadyam.mi.model.Distributor;
import com.example.aadyam.mi.model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
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



    @GET("AllotmentList?DistributorId=8193&StaffRefNo=11710819300000002&ConsumerCount=0")
    Call<Allotment>getAllotmentList();

  /*  @GET("AllotmentList")
    Call<Allotment>getAllotmentList(@Query("DistributorId") int Dist_id,@Query("StaffRefNo") String Staff_Ref_No,@Query("StaffRefNo") int Cons_count);*/
}


