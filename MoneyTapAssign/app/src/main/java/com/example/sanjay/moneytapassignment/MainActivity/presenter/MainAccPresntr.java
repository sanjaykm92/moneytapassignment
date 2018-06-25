package com.example.sanjay.moneytapassignment.MainActivity.presenter;

import com.example.sanjay.moneytapassignment.MainActivity.view.MainActivityView;
import com.example.sanjay.moneytapassignment.common.presenter.BasePresenter;

public interface MainAccPresntr extends BasePresenter<MainActivityView> {

    void onStart();

    void PingServer(String string);

    void onDestroy();

    void setAsAdapter();

}
