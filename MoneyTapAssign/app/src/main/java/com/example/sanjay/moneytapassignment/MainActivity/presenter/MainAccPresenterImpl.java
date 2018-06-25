package com.example.sanjay.moneytapassignment.MainActivity.presenter;

import android.util.Log;

import com.example.sanjay.moneytapassignment.App;
import com.example.sanjay.moneytapassignment.MainActivity.adapter.MainAccAdapter;
import com.example.sanjay.moneytapassignment.MainActivity.model.ResponseObj;
import com.example.sanjay.moneytapassignment.MainActivity.view.MainActivityView;
import com.example.sanjay.moneytapassignment.rest.APIError;
import com.example.sanjay.moneytapassignment.rest.APIinterface;
import com.example.sanjay.moneytapassignment.rest.ApiClient;

import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAccPresenterImpl implements MainAccPresntr {

    private MainActivityView view;
    private MainAccAdapter mAdaptr;
    private Call<ResponseObj> call;


    @Override
    public void init(MainActivityView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        mAdaptr = new MainAccAdapter(view.getContext1());

    }

    @Override
    public void PingServer(String string) {
        APIinterface apiService = App.getInstance().getClient().create(APIinterface.class);
        call = apiService.getSearchResult(string);
        call.enqueue(new Callback<ResponseObj>(){

            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {

                int statusCode = response.code();
                if (statusCode == 200) {
                        if (response.body().getQuery()!=null
                                && response.body().getQuery().getPages()!=null) {

                            mAdaptr.setData(response.body().getQuery().getPages());
                            setAsAdapter();
                        }
                }else {
                    APIError error = new APIError();
                    error.setStatusCode(response.code());
                    if (view != null) {
                        view.showTaost(error.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                try {
                    throw (t);
                } catch (UnknownHostException e) {
                    if (view != null)
                        view.showTaost("No Internet Connection");
                } catch (SSLHandshakeException e) {
                    // ssl handshake exception
                } catch (Exception e) {
                    // unknown error
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setAsAdapter() {
        if (view != null) {
            if (view.isAdapterNuull())
                view.setRecycAdapter(mAdaptr.getAdpter());
            else {
                view.refreshAdapter();
            }
        }
    }
}
