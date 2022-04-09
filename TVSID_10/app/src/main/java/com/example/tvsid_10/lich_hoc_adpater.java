package com.example.tvsid_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class lich_hoc_adpater extends RecyclerView.Adapter<lich_hoc_adpater.lich_hoc_Viewholder>{
    List<String>list;
    Context context;

    public lich_hoc_adpater(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public lich_hoc_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lich_hoc, parent, false);
        return new lich_hoc_adpater.lich_hoc_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lich_hoc_Viewholder holder, int position) {
        String s=list.get(position);
        holder.thu1.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class lich_hoc_Viewholder extends RecyclerView.ViewHolder{
        TextView thu1;
        public lich_hoc_Viewholder(@NonNull View itemView) {
            super(itemView);
            thu1=itemView.findViewById(R.id.thu1);
        }
    }
}
