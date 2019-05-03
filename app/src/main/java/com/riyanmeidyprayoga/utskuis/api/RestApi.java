package com.riyanmeidyprayoga.utskuis.api;

import com.riyanmeidyprayoga.utskuis.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    //insert
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("nim") String nim,
                                    @Field("nik") String nik,
                                    @Field("nama") String nama,
                                    @Field("alamat") String alamat,
                                    @Field("no") String no);
    //read
    @GET("read.php")
    Call<ResponseModel> getBiodata();

    //update menggunakan 3 parameter
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> updateData(@Field("id") String id,
                                   @Field("nim") String nim,
                                   @Field("nik") String nik,
                                   @Field("nama") String nama,
                                   @Field("alamat") String alamat,
                                   @Field("no") String no);
    //delete menggunakan parameter id
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> deleteData(@Field("id") String id);
}
