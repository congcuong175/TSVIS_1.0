package com.example.tvsid_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class feag_lich extends Fragment {

    RecyclerView list_item;
    lich_hoc_adpater lich_hoc_adpater;
    ArrayList<String>arrayList=new ArrayList<>();
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
        arrayList.add("Thứ 2");
        arrayList.add("Thứ 3");
        arrayList.add("Thứ 4");
        arrayList.add("Thứ 5");
        arrayList.add("Thứ 6");
        arrayList.add("Thứ 7");
        arrayList.add("Chủ nhật");
        lich_hoc_adpater=new lich_hoc_adpater(arrayList,getContext());
        list_item.setHasFixedSize(true);
        list_item.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        list_item.setAdapter(lich_hoc_adpater);
        lich_hoc_adpater.notifyDataSetChanged();
    }
}