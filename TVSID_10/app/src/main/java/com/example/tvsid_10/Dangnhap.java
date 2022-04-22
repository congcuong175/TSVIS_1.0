package com.example.tvsid_10;

import static com.example.tvsid_10.MainActivity.down;
import static com.example.tvsid_10.MainActivity.up;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Entity.SinhVien;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dangnhap extends Fragment {


    TextView btn_register_login;
    TextInputEditText edt_account_login,edt_password_login;
    Dialog dialog;
    public Dangnhap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dangnhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_register_login=view.findViewById(R.id.btn_register_login);
        edt_password_login=view.findViewById(R.id.edt_password_login);
        Animation animator= AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        view.findViewById(R.id.logofood).startAnimation(animator);
        edt_account_login=view.findViewById(R.id.edt_account_login);
        view.findViewById(R.id.imageView).startAnimation(animator);
        view.findViewById(R.id.btn_login_login).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.findViewById(R.id.btn_login_login).startAnimation(up);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        view.findViewById(R.id.btn_login_login).startAnimation(down);
                        showDialog();
                        ApiService.apiservice.getSinhVienById(edt_account_login.getText().toString(),edt_password_login.getText().toString()).enqueue(new Callback<SinhVien>() {
                            @Override
                            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                                if(response.body()!=null){

                                        Common.sinhVien= response.body();
                                        dialog.dismiss();
                                        startActivity(new Intent(getActivity(),Home.class));
                                }
                                else {
                                    dialog.dismiss();
                                    Toast.makeText(getActivity(),"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SinhVien> call, Throwable t) {
                                dialog.dismiss();
                                Log.e("ERRO2",t.toString());
                            }
                        });

                    }
                }
                return true;
            }
        });
        btn_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dangnhap_to_dangkytaikhoanFragment);
            }
        });
    }
    public void showDialog(){
        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.itemloaddata);
        dialog.setCancelable(true);
        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        layoutParams.gravity= Gravity.CENTER;
        window.setAttributes(layoutParams);
        dialog.show();
    }
}