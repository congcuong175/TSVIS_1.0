package com.example.tvsid_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home extends AppCompatActivity {
    public static ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.frag_home,true);
        bottommenu();
    }
    private void bottommenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.frag_home:
                        Navigation.findNavController(Home.this,R.id.nav_host_fragment).navigate(R.id.frag_home);
                        break;
                    case R.id.feag_lich:
                        Navigation.findNavController(Home.this,R.id.nav_host_fragment).navigate(R.id.feag_lich);
                        break;
                    case R.id.frag_tin_tuc:
                        Navigation.findNavController(Home.this,R.id.nav_host_fragment).navigate(R.id.frag_tin_tuc);
                        break;
                    case R.id.frag_taikhoan:
                        Navigation.findNavController(Home.this,R.id.nav_host_fragment).navigate(R.id.frag_taikhoan);
                        break;

                }
            }
        });
    }
}