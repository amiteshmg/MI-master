package com.example.aadyam.mi.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientLive
{
    private static Retrofit retrofit=null;
    //public  static Gson gson;

    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            // Gson=new GsonBuilder().setLenient().create();
            //http://lpgmandatoryinspection.hpcl.co.in/mandatoryinspectionwebservice/hp.svc/
            String BASE_URL = "http://lpgmandatoryinspection.hpcl.co.in/MandatoryInspectionWebService/HP.svc/";
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;

    }
}
