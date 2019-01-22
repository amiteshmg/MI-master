package com.example.aadyam.mi.model;

import com.google.gson.JsonObject;

import java.io.ByteArrayInputStream;

class InspectionDataList
{
    JsonObject ConsumerInfo;
    JsonObject PersonalInfo;
    //  gender,usingcreditcard,mothername,high consumer,father/spouse name,refills,,book refills,family members,typeofhouse,rationcard,two wheeler

    JsonObject QuestionsInfo;

    String image1Name;
    String image2Name;
    String image3Name;
    String image4Name;
    String image5Name;

    byte[] image1;
    byte[] image2;
    byte[] image3;
    byte[] image4;
    byte[] image5;


}
