package com.example.aadyam.mi.model;

import com.google.gson.annotations.SerializedName;

public class DeniedInspection
{
    /**
     * InspectionDeniedResult : Success
     */

    @SerializedName("InspectionDeniedResult")
    private String InspectionDeniedResult;

    public String getInspectionDeniedResult()
    {
        return InspectionDeniedResult;
    }

    public void setInspectionDeniedResult(String InspectionDeniedResult) {
        this.InspectionDeniedResult = InspectionDeniedResult;
    }
}
