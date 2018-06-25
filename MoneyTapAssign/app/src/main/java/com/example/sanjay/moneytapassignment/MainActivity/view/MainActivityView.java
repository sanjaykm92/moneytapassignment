package com.example.sanjay.moneytapassignment.MainActivity.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.sanjay.moneytapassignment.common.view.BaseView;

public interface MainActivityView extends BaseView {

    boolean isAdapterNuull();

    void refreshAdapter();

    void setRecycAdapter(RecyclerView.Adapter adapter);

    Context getContext1();


}
