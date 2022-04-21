package com.example.tvsid_10;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvsid_10.Common.Common;

import java.util.ArrayList;

public class frag_home extends Fragment {

    RecyclerView item1,item2;
    diem_adpater diem_adpater;
    ImageView img_capture_ttcn;
    diem_hoc_adpater diem_hoc_adpater;
    TextView tv_name_ttcn,tv_date_ttcn,tv_faculty_ttcn,tv_classroom_ttcn,tv_scholastic_ttcn,tv_id_ttcn;
    ArrayList<String> arrayList=new ArrayList<>();
    Dialog dialog;
    AutoCompleteTextView atctv_hocky_ttcn,atctv_namhoc_ttcn,atctv_nganhhoc_ttcn;
    String[] hockys={"1","2"};
    String[] namhocs={"2014-2015","2015-2016","2016-2017","2017-2018","2018-2019","2019-2020","2020-2021","2021-2022","2022-2023","2023-2024"};
    String[] nganhHocs={"Chuyên ngành chính","Chuyên ngành 2"};
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
        dialog=new Dialog(getActivity());
        arrayList.add("3");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("3");
        diem_adpater=new diem_adpater(arrayList,getContext());
        item1.setHasFixedSize(true);
        item1.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        item1.setAdapter(diem_adpater);
        diem_adpater.notifyDataSetChanged();
        diem_hoc_adpater=new diem_hoc_adpater(arrayList,getContext(),dialog);
        item2.setHasFixedSize(true);
        item2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        item2.setAdapter(diem_hoc_adpater);
        diem_hoc_adpater.notifyDataSetChanged();

        ArrayAdapter arrayHocKy=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,hockys);
        atctv_hocky_ttcn.setAdapter(arrayHocKy);
        ArrayAdapter arrayNamHoc=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,namhocs);
        atctv_namhoc_ttcn.setAdapter(arrayNamHoc);
        ArrayAdapter arrayNganhHoc=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,nganhHocs);
        atctv_nganhhoc_ttcn.setAdapter(arrayNganhHoc);

    }
    public void setData(){
        tv_name_ttcn.setText(Common.sinhVien.getHoTen());
        tv_date_ttcn.setText(Common.sinhVien.getNgaySinh());
        tv_faculty_ttcn.setText(Common.sinhVien.getNganhHoc());
        tv_classroom_ttcn.setText(Common.sinhVien.getLop());
        tv_scholastic_ttcn.setText(Common.sinhVien.getKhoaHoc());
        tv_id_ttcn.setText("Mã SV: "+Common.sinhVien.getID());
    }
    public void Init(View view){
        tv_name_ttcn=view.findViewById(R.id.tv_name_ttcn);
        tv_date_ttcn=view.findViewById(R.id.tv_date_ttcn);
        tv_faculty_ttcn=view.findViewById(R.id.tv_faculty_ttcn);
        tv_classroom_ttcn=view.findViewById(R.id.tv_classroom_ttcn);
        tv_scholastic_ttcn=view.findViewById(R.id.tv_scholastic_ttcn);
        tv_id_ttcn=view.findViewById(R.id.tv_id_ttcn);
        item1=view.findViewById(R.id.item1);
        item2=view.findViewById(R.id.item2);
        atctv_hocky_ttcn=view.findViewById(R.id.atctv_hocky_ttcn);
        atctv_namhoc_ttcn=view.findViewById(R.id.atctv_namhoc_ttcn);
        atctv_nganhhoc_ttcn=view.findViewById(R.id.atctv_nganhhoc_ttcn);
        img_capture_ttcn=view.findViewById(R.id.img_capture_ttcn);
    }
}