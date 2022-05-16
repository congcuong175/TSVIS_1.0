package com.example.tvsid_10.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvsid_10.Entity.Diem;
import com.example.tvsid_10.R;

import java.util.List;

public class diem_hoc_adpater extends RecyclerView.Adapter<diem_hoc_adpater.diem_hoc_Viewholder> {
    List<Diem> list;
    Context context;

    public diem_hoc_adpater(List<Diem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public diem_hoc_adpater.diem_hoc_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diemhoc, parent, false);
        return new diem_hoc_adpater.diem_hoc_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diem_hoc_adpater.diem_hoc_Viewholder holder, int position) {
        Diem s=list.get(position);
        if (s.getDiem()<5){
            holder.layout.setBackground(context.getDrawable(R.drawable.card_do));
        }
        holder.txt_diem_diemhoc.setText(s.getDiem()+"");
       // tv_mahp_diemhoc,tv_tenhp_diemhoc,tv_tinchi_diemhoc,txt_diem_diemhoc,tv_diemchu_diemhoc
        holder.tv_mahp_diemhoc.setText(s.getID()+"");
        holder.tv_tenhp_diemhoc.setText(s.getTenHocPhan());
        holder.tv_tinchi_diemhoc.setText(s.getSoTinChi()+"");
        if(s.getDiem()>=8){
            holder.tv_diemchu_diemhoc.setText("A");
        }
        else if (s.getDiem()>=7){
            holder.tv_diemchu_diemhoc.setText("B");
        }
        else if(s.getDiem()>=5){
            holder.tv_diemchu_diemhoc.setText("C");
        }
        else {
            holder.tv_diemchu_diemhoc.setText("F");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class diem_hoc_Viewholder extends RecyclerView.ViewHolder{
        TextView tv_mahp_diemhoc,tv_tenhp_diemhoc,tv_tinchi_diemhoc,txt_diem_diemhoc,tv_diemchu_diemhoc;
        LinearLayout layout;
        public diem_hoc_Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_mahp_diemhoc=itemView.findViewById(R.id.tv_mahp_diemhoc);
            tv_tenhp_diemhoc=itemView.findViewById(R.id.tv_tenhp_diemhoc);
            tv_tinchi_diemhoc=itemView.findViewById(R.id.tv_tinchi_diemhoc);
            tv_diemchu_diemhoc=itemView.findViewById(R.id.tv_diemchu_diemhoc);
            txt_diem_diemhoc=itemView.findViewById(R.id.txt_diem_diemhoc);
            layout=itemView.findViewById(R.id.layout);
        }
    }
    float Tong=0;
    public float DiemTB(){
        Tong=0;
        for (Diem d:list
             ) {
            Tong+=d.getDiem();
        }
        if(Tong>0){
            return Tong/list.size();
        }
        else {
            return 0;
        }
    }
}
