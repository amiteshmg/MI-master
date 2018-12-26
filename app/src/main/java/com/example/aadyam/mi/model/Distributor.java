package com.example.aadyam.mi.model;


import android.support.annotation.NonNull;

import java.util.List;

public class Distributor {

    private List<DistributorList> distributorList;

    public List<DistributorList> getDistributorList() {
        return distributorList;
    }

    public void setDistributorList(List<DistributorList> distributorList) {
        this.distributorList = distributorList;
    }


    @NonNull
    @Override
    public String toString() {
        return "[ POJO:{"+distributorList+"}]";
    }
}
