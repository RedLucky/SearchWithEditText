package com.bangkukayu.tolongrek.searchwithinputtext.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL = "https://tolongrek.bangkukayu.com/api/customer/";

    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient = null;

    private static OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(logging).build();
        }
        return okHttpClient;
    }

    public static Retrofit request() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build();
        }
        return retrofit;
    }
}
