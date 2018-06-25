package com.example.sanjay.moneytapassignment.rest;

import com.example.sanjay.moneytapassignment.MainActivity.model.ResponseObj;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIinterface {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api.php?action=query&format=json" +
            "&prop=pageimages%7Cpageterms"+
            "&generator=prefixsearch" +
            "&redirects=1&formatversion=2"+
            "&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=20")
    Call<ResponseObj> getSearchResult(@Query("gpssearch") String searchkey);

}
