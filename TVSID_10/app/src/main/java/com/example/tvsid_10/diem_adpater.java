package com.example.tvsid_10;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvsid_10.Entity.TBDEntity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;

public class diem_adpater extends RecyclerView.Adapter<diem_adpater.diem_Viewholder> {
    List<TBDEntity> list;
    Context context;

    public diem_adpater(List<TBDEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public diem_adpater.diem_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diem, parent, false);
        return new diem_adpater.diem_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diem_adpater.diem_Viewholder holder, int position) {
        TBDEntity s=list.get(position);

        holder.tv_diem_diem.setText(s.getDiem()+"");
        holder.tv_tenloaitc_diem.setText(s.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class diem_Viewholder extends RecyclerView.ViewHolder{

        TextView tv_diem_diem,tv_tenloaitc_diem;
        public diem_Viewholder(@NonNull View itemView) {
            super(itemView);

            tv_diem_diem=itemView.findViewById(R.id.tv_diem_diem);
            tv_tenloaitc_diem=itemView.findViewById(R.id.tv_tenloaitc_diem);
        }
    }
}
