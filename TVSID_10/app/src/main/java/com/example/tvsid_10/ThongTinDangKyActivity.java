package com.example.tvsid_10;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Api.FirebaseAPI;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Entity.SinhVien;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinDangKyActivity extends AppCompatActivity {

    EditText edt_name, edt_id, edt_date, edt_faculty, edt_classroom, edt_scholastic;
    Button btn_confirm;
    ImageView img_avatar;
    FirebaseAPI firebaseAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_dang_ky);
        initView();
        loadData();
        onClick();
    }

    private void initView() {
        edt_name = findViewById(R.id.edt_name);
        edt_id = findViewById(R.id.edt_id);
        edt_date = findViewById(R.id.edt_date);
        edt_scholastic = findViewById(R.id.edt_scholastic);
        edt_classroom = findViewById(R.id.edt_classroom);
        edt_faculty = findViewById(R.id.edt_faculty);
        img_avatar = findViewById(R.id.img_avatar);
        btn_confirm = findViewById(R.id.btn_confirm);
        firebaseAPI = new FirebaseAPI();
    }

    private void onClick() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(ThongTinDangKyActivity.this);
                dialog.setTitle("Đang đăng ký tài khoản");
                String masv = edt_id.getText().toString();
                String hoten = edt_name.getText().toString();
                String ngaysinh = edt_date.getText().toString();
                String lop = edt_classroom.getText().toString();
                String khoahoc = edt_scholastic.getText().toString();
                String nganh = edt_faculty.getText().toString();
                Log.e("TAG",Common.sinhVien.getAvatar());
                SinhVien sv = new SinhVien(Common.sinhVien.getAvatar(), hoten, Integer.parseInt(masv), khoahoc, lop, masv, nganh, ngaysinh, true);
                dialog.show();
                ApiService.apiservice.register(sv).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            if (response.body() > 0) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        img_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(ThongTinDangKyActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY);
            }
        });
    }

    private void loadData() {
        if (Common.sinhVien != null) {
            edt_name.setText(Common.sinhVien.getHoTen());
            edt_id.setText(Common.sinhVien.getID() + "");
            edt_date.setText(Common.sinhVien.getNgaySinh());
            edt_scholastic.setText(Common.sinhVien.getKhoaHoc());
            edt_classroom.setText(Common.sinhVien.getLop());
            edt_faculty.setText(Common.sinhVien.getNganhHoc());
        }
    }

    int REQUEST_GALERY = 123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_GALERY && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_GALERY);
        } else {
            Toast.makeText(getApplicationContext(), "Bạn chưa cấp quyền camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            img_avatar.setImageURI(uri);
            firebaseAPI.uploadImage(uri, Common.sinhVien);

        }
    }
}