package com.example.tvsid_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;


public class feag_lich extends Fragment {

    RecyclerView list_item;
    lich_hoc_adpater lich_hoc_adpater;
    ArrayList<String>arrayList=new ArrayList<>();
    AutoCompleteTextView atctv_namhoc_lich,atctv_tuanhoc_lich;
    String[] namhocs={"2014-2015","2015-2016","2016-2017","2017-2018","2018-2019","2019-2020","2020-2021","2021-2022","2022-2023","2023-2024"};
    String[] tuanhocs={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45"};
    public feag_lich() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feag_lich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_item=view.findViewById(R.id.list_item);
        atctv_tuanhoc_lich=view.findViewById(R.id.atctv_tuanhoc_lich);
        atctv_namhoc_lich=view.findViewById(R.id.atctv_namhoc_lich);
        arrayList.add("Thứ 2");
        arrayList.add("Thứ 3");
        arrayList.add("Thứ 4");
        arrayList.add("Thứ 5");
        arrayList.add("Thứ 6");
        arrayList.add("Thứ 7");
        arrayList.add("Chủ nhật");
        lich_hoc_adpater=new lich_hoc_adpater(getContext());

        list_item.setHasFixedSize(true);
        list_item.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        list_item.setAdapter(lich_hoc_adpater);
        lich_hoc_adpater.setData(arrayList);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(list_item);
        lich_hoc_adpater.notifyDataSetChanged();
        ArrayAdapter arrayNamHoc=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,namhocs);
        atctv_namhoc_lich.setAdapter(arrayNamHoc);
        ArrayAdapter arrayTuanHoc=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,tuanhocs);
        atctv_tuanhoc_lich.setAdapter(arrayTuanHoc);
        list_item.smoothScrollToPosition(4);
    }
}