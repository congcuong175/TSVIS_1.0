package com.example.tvsid_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvsid_10.Entity.LichHoc;

import java.util.List;

public class lich_hoc_adpater extends RecyclerView.Adapter<lich_hoc_adpater.lich_hoc_Viewholder>{
    List<LichHoc>list;
    Context context;

    public lich_hoc_adpater( Context context) {
        this.context = context;
    }
    public void setData(List<LichHoc>list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public lich_hoc_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lich_hoc, parent, false);
        return new lich_hoc_adpater.lich_hoc_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lich_hoc_Viewholder holder, int position) {
        LichHoc s=list.get(position);
        holder.thu1.setText("Thứ: "+s.getThu());
        holder.tv_ngay_lichoc.setText(s.getNgay());
        holder.tv_thoigian_lichhoc.setText(s.getThoiGian());
        holder.tv_tiet_buoihoc.setText(s.getTiet());
        holder.tv_tenmon_lichhoc.setText(s.getTenHocPhan());
        holder.tv_phong_lichhoc.setText(s.getPhong());
        holder.tv_giaovien_lichhoc.setText(s.getPhong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class lich_hoc_Viewholder extends RecyclerView.ViewHolder{
        TextView thu1,tv_ngay_lichoc,tv_buoi_lichhoc,tv_tiet_buoihoc,tv_thoigian_lichhoc,tv_tenmon_lichhoc,tv_phong_lichhoc,tv_giaovien_lichhoc;
        public lich_hoc_Viewholder(@NonNull View itemView) {
            super(itemView);
            thu1=itemView.findViewById(R.id.thu1);
            tv_ngay_lichoc=itemView.findViewById(R.id.tv_ngay_lichoc);
            tv_buoi_lichhoc=itemView.findViewById(R.id.tv_buoi_lichhoc);
            tv_tiet_buoihoc=itemView.findViewById(R.id.tv_tiet_buoihoc);
            tv_thoigian_lichhoc=itemView.findViewById(R.id.tv_thoigian_lichhoc);
            tv_tenmon_lichhoc=itemView.findViewById(R.id.tv_tenmon_lichhoc);
            tv_phong_lichhoc=itemView.findViewById(R.id.tv_phong_lichhoc);
            tv_giaovien_lichhoc=itemView.findViewById(R.id.tv_giaovien_lichhoc);
        }
    }
}
