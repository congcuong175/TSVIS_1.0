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

import java.util.ArrayList;

public class frag_home extends Fragment {

    RecyclerView item1,item2;
    diem_adpater diem_adpater;
    diem_hoc_adpater diem_hoc_adpater;
    ArrayList<String> arrayList=new ArrayList<>();
    Dialog dialog;
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
        item1=view.findViewById(R.id.item1);
        item2=view.findViewById(R.id.item2);
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
    }
}