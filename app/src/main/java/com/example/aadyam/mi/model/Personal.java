package com.example.aadyam.mi.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Personal
{
    @SerializedName("InformationListResult")
    @Expose
    private List<PersonalInfoList> allotmentListResult;

    public List<PersonalInfoList> getAllotmentListResult()
    {
        return allotmentListResult;
    }


    public void setAllotmentListResult(List<PersonalInfoList> allotmentListResult)
    {
        this.allotmentListResult = allotmentListResult;
    }



    @NonNull
    @Override
    public String toString()
    {
        return super.toString();
    }
}
