package com.example.tvsid_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class diem_adpater extends RecyclerView.Adapter<diem_adpater.diem_Viewholder> {
    List<String> list;
    Context context;

    public diem_adpater(List<String> list, Context context) {
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
        String s=list.get(position);
        holder.txt_diem.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class diem_Viewholder extends RecyclerView.ViewHolder{
        TextView txt_diem;
        public diem_Viewholder(@NonNull View itemView) {
            super(itemView);
            txt_diem=itemView.findViewById(R.id.txt_diem);
        }
    }
}
