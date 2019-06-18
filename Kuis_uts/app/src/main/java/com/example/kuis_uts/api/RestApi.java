package com.example.kuis_uts.api;

import com.example.kuis_uts.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;

public interface RestApi {
    //insert
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("kode") String kode,
                                    @Field("nama") String nama,
                                    @Field("kategori") String kategori);

    //read
    @GET("read.php")
    Call<ResponseModel> getBiodata();

}
