package com.example.tvsid_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Entity.LichHoc;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class feag_lich extends Fragment {
    Handler handler=new Handler();
    String tuan="";
    Runnable runnable;
    RecyclerView list_item;
    lich_hoc_adpater lich_hoc_adpater;
    ArrayList<LichHoc>arrayList=new ArrayList<>();
    AutoCompleteTextView atctv_namhoc_lich,atctv_tuanhoc_lich;
    String[] namhocs={"2014-2015","2015-2016","2016-2017","2017-2018","2018-2019","2019-2020","2020-2021","2021-2022","2022-2023","2023-2024"};
    String[] tuanhocs={"Tuần 1 [Từ 23/08/2021 -- Đến 29/08/2021]",
            "Tuần 2 [Từ 23/08/2021 -- Đến 29/08/2021]",
            "Tuần 3 [Từ 30/08/2021 -- Đến 05/09/2021]",
            "Tuần 4 [Từ 06/08/2021 -- Đến 12/09/2021]",
            "Tuần 5 [Từ 20/09/2021 -- Đến 26/09/2021]",
            "Tuần 6 [Từ 27/09/2021 -- Đến 03/10/2021]",
            "Tuần 7 [Từ 04/10/2021 -- Đến 10/10/2021]",
            "Tuần 8 [Từ 11/10/2021 -- Đến 17/10/2021]",
            "Tuần 9 [Từ 18/10/2021 -- Đến 24/10/2021]",
            "Tuần 10 [Từ 25/10/2021 -- Đến 31/10/2021]",
            "Tuần 11 [Từ 01/11/2021 -- Đến 07/11/2021]",
            "Tuần 12 [Từ 08/11/2021 -- Đến 14/11/2021]",
            "Tuần 13 [Từ 15/11/2021 -- Đến 21/11/2021]",
            "Tuần 14 [Từ 22/11/2021 -- Đến 28/11/2021]",
            "Tuần 15 [Từ 29/11/2021 -- Đến 05/12/2021]",
            "Tuần 16 [Từ 06/12/2021 -- Đến 12/12/2021]",
            "Tuần 17 [Từ 13/12/2021 -- Đến 19/12/2021]",
            "Tuần 18 [Từ 20/12/2021 -- Đến 26/12/2021]",
            "Tuần 19 [Từ 27/12/2021 -- Đến 02/01/2022]",
            "Tuần 20 [Từ 03/01/2022 -- Đến 09/01/2022]",
            "Tuần 21 [Từ 10/01/2022 -- Đến 16/01/2022]",
            "Tuần 22 [Từ 17/01/2022 -- Đến 23/01/2022]",
            "Tuần 23 [Từ 24/01/2022 -- Đến 30/01/2022]",
            "Tuần 24 [Từ 31/01/2022 -- Đến 06/02/2022]",
            "Tuần 25 [Từ 07/02/2022 -- Đến 13/02/2022]",
            "Tuần 26 [Từ 14/02/2022 -- Đến 20/02/2022]",
            "Tuần 27 [Từ 21/02/2022 -- Đến 27/02/2022]",
            "Tuần 28 [Từ 28/02/2022 -- Đến 06/03/2022]",
            "Tuần 29 [Từ 07/03/2022 -- Đến 13/03/2022]",
            "Tuần 30 [Từ 14/03/2022 -- Đến 20/03/2022]]",
            "Tuần 31 [Từ 21/03/2022 -- Đến 27/03/2022]",
            "Tuần 32 [Từ 28/03/2022 -- Đến 03/04/2022]",
            "Tuần 33 [Từ 04/04/2022 -- Đến 10/04/2022]",
            "Tuần 34 [Từ 11/04/2022 -- Đến 17/04/2022]",
            "Tuần 35 [Từ 18/04/2022 -- Đến 24/04/2022]",
            "Tuần 36 [Từ 25/04/2022 -- Đến 01/05/2022]]",
            "Tuần 37 [Từ 02/05/2022 -- Đến 08/05/2022]",
            "Tuần 38 [Từ 09/05/2022 -- Đến 15/05/2022]",
            "Tuần 39 [Từ 16/05/2022 -- Đến 22/05/2022]",
            "Tuần 40 [Từ 23/05/2022 -- Đến 29/05/2022]",
            "Tuần 41 [Từ 30/05/2022 -- Đến 05/06/2022]",
            "Tuần 42 [Từ 06/06/2022 -- Đến 12/06/2022]",
            "Tuần 43 [Từ 13/06/2022 -- Đến 19/06/2022]",
            "Tuần 44 [Từ 20/06/2022 -- Đến 26/06/2022]",
            "Tuần 45 [Từ 27/06/2022 -- Đến 03/07/2022]",
            "Tuần 46 [Từ 04/07/2022 -- Đến 10/07/2022]",
            "Tuần 47 [Từ 11/07/2022 -- Đến 17/07/2022]",
            "Tuần 48 [Từ 18/07/2022 -- Đến 24/07/2022]",
            "Tuần 49 [Từ 25/07/2022 -- Đến 31/07/2022]",
            "Tuần 50 [Từ 01/08/2022 -- Đến 07/08/2022]",
    };
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
        atctv_tuanhoc_lich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tuan=(i+1)+"";
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable=new Runnable() {
            @Override
            public void run() {
                loadData();
                handler.postDelayed(runnable,3000);
            }
        },500);
    }


    public void loadData(){
        ApiService.apiservice.getLicHoc(tuan, Common.sinhVien.getID(),atctv_namhoc_lich.getText().toString()).enqueue(new Callback<List<LichHoc>>() {
            @Override
            public void onResponse(Call<List<LichHoc>> call, Response<List<LichHoc>> response) {
                if(response.body()!=null){
                    arrayList.clear();
                    for (LichHoc lh:response.body()
                         ) {
                        arrayList.add(lh);
                    }
                    lich_hoc_adpater.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<LichHoc>> call, Throwable t) {

            }
        });
    }
}