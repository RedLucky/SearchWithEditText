package com.bangkukayu.tolongrek.searchwithinputtext.api;

import com.bangkukayu.tolongrek.searchwithinputtext.model.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoint {
    @GET("service/search")
    Call<ArrayList<Service>> getService(@Query("q") String query);
}
