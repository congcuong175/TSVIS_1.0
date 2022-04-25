package com.example.tvsid_10.Api;

import com.example.tvsid_10.Entity.Diem;
import com.example.tvsid_10.Entity.LichHoc;
import com.example.tvsid_10.Entity.SinhVien;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("https://tsvid-api-kt7.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
            .build()
            .create(ApiService.class);

    @GET("api/SinhViens/GetSV")
    Call<SinhVien> getSinhVienById(@Query("id") String id,@Query("pass") String pass);

    @GET("api/Diems/GetDiem")
    Call<List<Diem>> getAllDiemTheo(@Query("idSV") int idSV, @Query("ky") int ky, @Query("nam") String nam);

    @POST("api/SinhViens/Register")
    Call<Integer> register(@Body SinhVien sinhVien);

    @GET("api/Diems/GetDiemAll")
    Call<List<Diem>> getAllDiemAll(@Query("idSV") int idSV);
    @GET("api/LichHos/GetLichHocByTuan")
    Call<List<LichHoc>> getLicHoc(@Query("tuan")String tuan, @Query("id") int id,@Query("nam") String nam);

    @PUT("api/SinhViens/Update")
    Call<Integer> upDate(@Query("id") int id,@Body SinhVien sv);


    ApiService api = new Retrofit.Builder().baseUrl("http://192.168.43.59:5000").
            addConverterFactory(GsonConverterFactory.create(gson)).
            client(okHttpClient).
            build().
            create(ApiService.class);

    @Multipart
    @POST("/")
    Call<SinhVien> detectIDSV(@Part MultipartBody.Part image);

}
