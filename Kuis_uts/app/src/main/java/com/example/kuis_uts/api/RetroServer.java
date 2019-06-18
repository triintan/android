package com.example.kuis_uts.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {

    //url
    private static final String base_url=
            "http://192.168.43.86/db_hewan/";
    private static Retrofit retrofit;

    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            retrofit= new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}