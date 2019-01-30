package com.example.aadyam.mi.adapter;

import android.view.View;

import com.example.aadyam.mi.model.AllotmentList;

interface ClickListener {
    void onItemClicked(int position, View v);

    void onContactSelected(AllotmentList allotmentList);
}