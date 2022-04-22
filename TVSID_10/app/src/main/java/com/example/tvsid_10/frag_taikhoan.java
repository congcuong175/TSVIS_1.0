package com.example.tvsid_10;

import static com.example.tvsid_10.Home.chipNavigationBar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsid_10.Common.Common;

import de.hdodenhof.circleimageview.CircleImageView;

public class frag_taikhoan extends Fragment {

    TextView txt_name,txt_masv,txt_lop,txt_nganhhoc,txt_dangxuat,txt_suu;
    LinearLayout tv_thongtinlichhoc_taikhoan,tv_ketquahoctap_taikhoan;
    CircleImageView profile_image;
    public frag_taikhoan() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_name=view.findViewById(R.id.txt_name);
        txt_masv=view.findViewById(R.id.txt_masv);
        txt_lop=view.findViewById(R.id.txt_lop);
        txt_nganhhoc=view.findViewById(R.id.txt_nganhhoc);
        txt_suu=view.findViewById(R.id.txt_suu);
        profile_image=view.findViewById(R.id.profile_image);
        txt_dangxuat=view.findViewById(R.id.txt_dangxuat);
        tv_thongtinlichhoc_taikhoan=view.findViewById(R.id.tv_thongtinlichhoc_taikhoan);
        tv_ketquahoctap_taikhoan=view.findViewById(R.id.tv_ketquahoctap_taikhoan);
        txt_name.setText("Họ Tên: "+Common.sinhVien.getHoTen());
        txt_masv.setText("Mã SV: "+Common.sinhVien.getID()+"");
        txt_lop.setText("Mã Lớp: "+Common.sinhVien.getLop()+"");
        txt_nganhhoc.setText("Ngành: "+Common.sinhVien.getNganhHoc());
        Glide.with(getContext()).load(Common.sinhVien.getAvatar()).into(profile_image);
        tv_thongtinlichhoc_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setItemSelected(R.id.feag_lich,true);
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.feag_lich);

            }
        });
        tv_ketquahoctap_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipNavigationBar.setItemSelected(R.id.frag_home,true);
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.frag_home);

            }
        });
        txt_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Dangnhap.class));
            }
        });
        txt_suu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),UpdateThongTin.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_taikhoan, container, false);
    }
}