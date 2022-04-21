package com.example.tvsid_10.Api;

import com.example.tvsid_10.Entity.Diem;
import com.example.tvsid_10.Entity.SinhVien;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiservice=new Retrofit.Builder()
            .baseUrl("https://tsvid-api-kt7.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("api/SinhViens/GetSV")
    Call<SinhVien> getSinhVienById(@Query("id") String id);
    @GET("api/Diems/GetDiem")
    Call<List<Diem>> getAllDiemTheo(@Query("idSV") int idSV,@Query("ky") int ky ,@Query("nam") String nam);
    @GET("api/Diems/GetDiemAll")
    Call<List<Diem>> getAllDiemAll(@Query("idSV") int idSV);
}
