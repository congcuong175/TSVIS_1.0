package com.example.tvsid_10.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import com.bumptech.glide.Glide;
import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Api.FirebaseAPI;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Entity.SinhVien;
import com.example.tvsid_10.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateThongTin extends AppCompatActivity {

    EditText edt_name, edt_id, edt_date, edt_faculty, edt_classroom, edt_scholastic;
    Button btn_confirm;
    ImageView img_avatar;
    FirebaseAPI firebaseAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_thong_tin);
        initView();
        loadData();
        onClick();
    }

    private void initView() {
        edt_name = findViewById(R.id.edt_name_update);
        edt_id = findViewById(R.id.edt_id_update);
        edt_id.setEnabled(false);
        edt_date = findViewById(R.id.edt_date_update);
        edt_scholastic = findViewById(R.id.edt_scholastic_update);
        edt_classroom = findViewById(R.id.edt_classroom_update);
        edt_faculty = findViewById(R.id.edt_faculty_update);
        img_avatar = findViewById(R.id.img_avatar_update);
        btn_confirm = findViewById(R.id.btn_confirm_update);
        firebaseAPI = new FirebaseAPI();
    }

    private void onClick() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(UpdateThongTin.this);
                dialog.setTitle("Đang cập nhật tài khoản");
                String masv = edt_id.getText().toString();
                String hoten = edt_name.getText().toString();
                String ngaysinh = edt_date.getText().toString();
                String lop = edt_classroom.getText().toString();
                String khoahoc = edt_scholastic.getText().toString();
                String nganh = edt_faculty.getText().toString();
                Log.e("TAG", Common.sinhVien.getAvatar());
                SinhVien sv = new SinhVien(Common.sinhVien.getAvatar(), hoten, Integer.parseInt(masv), khoahoc, lop, masv, nganh, ngaysinh, true);
                dialog.show();
                ApiService.apiservice.upDate(Common.sinhVien.getID(),sv).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            if (response.body() > 0) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                Common.sinhVien=sv;
                                startActivity(new Intent(getApplicationContext(), Home.class));
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
                ActivityCompat.requestPermissions(UpdateThongTin.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY);
            }
        });
    }

    private void loadData() {
        if (Common.sinhVien != null) {
            edt_name.setText(Common.sinhVien.getHoTen()+"");
            edt_id.setText(Common.sinhVien.getID() + "");
            edt_date.setText(Common.sinhVien.getNgaySinh());
            edt_scholastic.setText(Common.sinhVien.getKhoaHoc());
            edt_classroom.setText(Common.sinhVien.getLop());
            edt_faculty.setText(Common.sinhVien.getNganhHoc());
            Glide.with(UpdateThongTin.this).load(Common.sinhVien.getAvatar()).into(img_avatar);
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