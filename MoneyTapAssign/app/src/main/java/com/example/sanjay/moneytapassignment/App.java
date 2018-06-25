package com.example.sanjay.moneytapassignment;

import android.app.Application;
import android.content.Context;

import com.example.sanjay.moneytapassignment.rest.ApiClient;

public class App extends Application {

    private static ApiClient ourInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = new ApiClient(this);
    }


    public static ApiClient getInstance() {
        return ourInstance;
    }

}
