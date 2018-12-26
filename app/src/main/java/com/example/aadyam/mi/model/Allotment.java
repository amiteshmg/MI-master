package com.example.aadyam.mi.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Allotment
    {

    @SerializedName("AllotmentListResult")
    @Expose
    private  List<AllotmentList> allotmentListResult;

    public List<AllotmentList> getAllotmentListResult()
    {
        return allotmentListResult;
    }


    public void setAllotmentListResult(List<AllotmentList> allotmentListResult)
    {
        this.allotmentListResult = allotmentListResult;
    }


        @NonNull
        @Override
        public String toString() {
            return "[ POJO Allotment : { "+allotmentListResult+" } ]";
        }

    }

