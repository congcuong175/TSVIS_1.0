package com.example.tvsid_10;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class diem_hoc_adpater extends RecyclerView.Adapter<diem_hoc_adpater.diem_hoc_Viewholder> {
    List<String> list;
    Context context;
    Dialog dialog;

    public diem_hoc_adpater(List<String> list, Context context, Dialog dialog) {
        this.list = list;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public diem_hoc_adpater.diem_hoc_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diemhoc, parent, false);
        return new diem_hoc_adpater.diem_hoc_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diem_hoc_adpater.diem_hoc_Viewholder holder, int position) {
        String s=list.get(position);
        if (Integer.parseInt(s)<5){
            holder.layout.setBackground(context.getDrawable(R.drawable.card_do));
        }
        holder.txt_diem.setText(s);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.diem_chitiet);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams w = window.getAttributes();
                w.gravity = Gravity.CENTER;
                window.setAttributes(w);
                dialog.setCancelable(true);
                dialog.getWindow().setWindowAnimations(R.style.animtordialog);
                LinearLayout layout1=dialog.findViewById(R.id.layout1);
                if (Integer.parseInt(s)<5){
                    layout1.setBackground(context.getDrawable(R.drawable.card_do));
                }
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class diem_hoc_Viewholder extends RecyclerView.ViewHolder{
        TextView txt_diem;
        LinearLayout layout;
        public diem_hoc_Viewholder(@NonNull View itemView) {
            super(itemView);
            txt_diem=itemView.findViewById(R.id.txt_diem);
            layout=itemView.findViewById(R.id.layout);
        }
    }
}
