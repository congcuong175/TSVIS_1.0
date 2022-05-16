package com.example.tvsid_10.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvsid_10.Activity.DiemDanhActivity;
import com.example.tvsid_10.Adapter.diem_hoc_adpater;
import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Entity.Diem;
import com.example.tvsid_10.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_home extends Fragment {

    RecyclerView item2;
    ImageView img_capture_ttcn;
    com.example.tvsid_10.Adapter.diem_hoc_adpater diem_hoc_adpater;
    Handler handler = new Handler();

    Runnable runnable;
    CircularProgressBar circularProgressBar, circularProgressBarH10;
    TextView tv_name_ttcn, tv_date_ttcn, tv_faculty_ttcn, tv_classroom_ttcn, tv_scholastic_ttcn, tv_id_ttcn, tv_diemH4_diem, tv_diemH10_diem;
    ArrayList<Diem> diemArrayList = new ArrayList<>();
    AutoCompleteTextView atctv_hocky_ttcn, atctv_namhoc_ttcn, atctv_nganhhoc_ttcn;
    String[] hockys = {"1", "2"};
    String[] namhocs = {"2014-2015", "2015-2016", "2016-2017", "2017-2018", "2018-2019", "2019-2020", "2020-2021", "2021-2022", "2022-2023", "2023-2024"};
    String[] nganhHocs = {"Chuyên ngành chính", "Chuyên ngành 2"};

    public frag_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Init(view);
        setData();

        diem_hoc_adpater = new diem_hoc_adpater(diemArrayList, getContext());
        item2.setHasFixedSize(true);
        item2.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        item2.setAdapter(diem_hoc_adpater);
        diem_hoc_adpater.notifyDataSetChanged();

        ArrayAdapter arrayHocKy = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, hockys);
        atctv_hocky_ttcn.setAdapter(arrayHocKy);
        ArrayAdapter arrayNamHoc = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, namhocs);
        atctv_namhoc_ttcn.setAdapter(arrayNamHoc);
        ArrayAdapter arrayNganhHoc = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, nganhHocs);
        atctv_nganhhoc_ttcn.setAdapter(arrayNganhHoc);
        circularProgressBar = view.findViewById(R.id.yourCircularProgressbarH4);
        circularProgressBarH10 = view.findViewById(R.id.yourCircularProgressbarH10);
        circularProgressBar.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        circularProgressBarH10.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        view.findViewById(R.id.btn_diem_danh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DiemDanhActivity.class));
            }
        });
    }

    public void setData() {
        tv_name_ttcn.setText(Common.sinhVien.getHoTen());
        tv_date_ttcn.setText(Common.sinhVien.getNgaySinh());
        tv_faculty_ttcn.setText(Common.sinhVien.getNganhHoc());
        tv_classroom_ttcn.setText(Common.sinhVien.getLop());
        tv_scholastic_ttcn.setText(Common.sinhVien.getKhoaHoc());
        tv_id_ttcn.setText("Mã SV: " + Common.sinhVien.getID());
        Glide.with(getContext()).load(Common.sinhVien.getAvatar()).into(img_capture_ttcn);
    }

    public void Init(View view) {
        tv_name_ttcn = view.findViewById(R.id.tv_name_ttcn);
        tv_date_ttcn = view.findViewById(R.id.tv_date_ttcn);
        tv_faculty_ttcn = view.findViewById(R.id.tv_faculty_ttcn);
        tv_classroom_ttcn = view.findViewById(R.id.tv_classroom_ttcn);
        tv_scholastic_ttcn = view.findViewById(R.id.tv_scholastic_ttcn);
        tv_id_ttcn = view.findViewById(R.id.tv_id_ttcn);
        tv_diemH4_diem = view.findViewById(R.id.tv_diemH4_diem);
        tv_diemH10_diem = view.findViewById(R.id.tv_diemH10_diem);
        item2 = view.findViewById(R.id.item2);
        atctv_hocky_ttcn = view.findViewById(R.id.atctv_hocky_ttcn);
        atctv_namhoc_ttcn = view.findViewById(R.id.atctv_namhoc_ttcn);
        atctv_nganhhoc_ttcn = view.findViewById(R.id.atctv_nganhhoc_ttcn);
        img_capture_ttcn = view.findViewById(R.id.img_capture_ttcn);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {

                int animationDuration = 2500; // 2500ms = 2,5s
                circularProgressBar.setProgressWithAnimation((diem_hoc_adpater.DiemTB() / 10) * 100, animationDuration); // Default duration = 1500ms
                circularProgressBarH10.setProgressWithAnimation((diem_hoc_adpater.DiemTB() / 10) * 100, animationDuration); // Default duration = 1500ms
                tv_diemH10_diem.setText(FormatDiem(diem_hoc_adpater.DiemTB()) + "");
                tv_diemH4_diem.setText(FormatDiem((diem_hoc_adpater.DiemTB() / 10) * 4) + "");
                handler.postDelayed(runnable, 3000);

                loadData();
            }
        }, 500);
    }

    public String FormatDiem(float diem) {
        return Math.ceil(diem * 100) / 100.0 + "";

    }

    public void loadData() {
        try {
            int ky = Integer.parseInt(atctv_hocky_ttcn.getText().toString().trim());
            String nam = atctv_namhoc_ttcn.getText().toString();
            ApiService.apiservice.getAllDiemTheo(Common.sinhVien.getID(), ky, nam).enqueue(new Callback<List<Diem>>() {
                @Override
                public void onResponse(Call<List<Diem>> call, Response<List<Diem>> response) {
                    if (response.body() != null) {
                        diemArrayList.clear();
                        for (Diem d : response.body()
                        ) {
                            diemArrayList.add(d);
                        }
                        diem_hoc_adpater.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<List<Diem>> call, Throwable t) {

                }
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ApiService.apiservice.getAllDiemAll(Common.sinhVien.getID()).enqueue(new Callback<List<Diem>>() {
                @Override
                public void onResponse(Call<List<Diem>> call, Response<List<Diem>> response) {
                    diemArrayList.clear();
                    for (Diem d : response.body()
                    ) {
                        diemArrayList.add(d);
                    }
                    diem_hoc_adpater.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Diem>> call, Throwable t) {

                }
            });

        }


    }


}