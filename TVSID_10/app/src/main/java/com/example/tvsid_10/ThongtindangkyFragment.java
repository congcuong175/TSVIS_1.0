package com.example.tvsid_10;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Common.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongtindangkyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thongtindangky, container, false);
    }

    EditText edt_name, edt_id, edt_date, edt_faculty, edt_classroom, edt_scholastic;
    Button btn_confirm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        onClick();
    }

    private void initView(View view) {
        edt_name = view.findViewById(R.id.edt_name);
        edt_id = view.findViewById(R.id.edt_id);
        edt_date = view.findViewById(R.id.edt_date);
        edt_scholastic = view.findViewById(R.id.edt_scholastic);
        edt_classroom = view.findViewById(R.id.edt_classroom);
        edt_faculty = view.findViewById(R.id.edt_faculty);
        btn_confirm = view.findViewById(R.id.btn_confirm);
    }

    private void onClick() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setTitle("Đang đăng ký tài khoản");
                Common.sinhVien.setMatKhau(Common.sinhVien.getID() + "");
                dialog.show();
                ApiService.apiservice.register(Common.sinhVien).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            if (response.body() > 0) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigate(R.id.dangnhap);
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                    }
                });
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
}