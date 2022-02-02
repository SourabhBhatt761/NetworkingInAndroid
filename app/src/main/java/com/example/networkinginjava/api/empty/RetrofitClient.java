package com.example.networkinginjava.api.empty;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    String BASE_URL = "https://reqres.in";
    private static RetrofitClient instance = null;
    private final NetworkInterface myApi;

    private RetrofitClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())     //to convert json to gson.
                .build();
        myApi = retrofit.create(NetworkInterface.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public NetworkInterface getMyApi() {
        return myApi;
    }
}
