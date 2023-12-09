package com.example.batapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.batapp.Fragment.AddzoneFragment;
import com.example.batapp.Fragment.HomeFragment;
import com.example.batapp.Fragment.PartageFragment;
import com.example.batapp.Fragment.ProfileFragment;
import com.example.batapp.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ChipNavigationBar menu = findViewById(R.id.bottom_menu);

        menu.setMenuOrientation(ChipNavigationBar.MenuOrientation.HORIZONTAL);
        menu.setMenuResource(R.menu.bottom_menu);
        menu.setItemSelected(R.id.bottom_home,true);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_menu_container,new HomeFragment(),"")
                .commit();


        menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case (R.id.bottom_home):
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_menu_container,new  HomeFragment(),"")
                                .commit();
                        break;
                    case (R.id.bottom_profile):
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_menu_container,new ProfileFragment(),"")
                                .commit();
                        break;
                    case (R.id.bottom_map):
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_menu_container,new AddzoneFragment(),"")
                                .commit();
                        break;
                    case (R.id.bottom_partage):
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.fragment_menu_container,new PartageFragment(),"")
                                .commit();
                        break;
                }
            }
        });
    }
}
