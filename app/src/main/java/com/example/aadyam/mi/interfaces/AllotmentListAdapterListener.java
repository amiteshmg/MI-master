package com.example.aadyam.mi.interfaces;

import android.view.View;

import com.example.aadyam.mi.model.AllotmentList;

public interface AllotmentListAdapterListener
{
    void onContactSelected(AllotmentList allotmentList);

    void onItemClicked(int position, View v,AllotmentList allotmentList);
}
