package com.example.batapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.batapp.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class PartageFragment extends Fragment {

ChipNavigationBar menu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_partage, container, false);




//        menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int i) {
//                switch (i){
//
//                    case (R.id.profile_edit):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.fragment_menu_container,new AddzoneFragment(),"")
//                                .commit();
//                        break;
//                    case (R.id.profile_position):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.fragment_menu_container,new PartageFragment(),"")
//                                .commit();
//                        break;
//                }
//            }
//        });

        return v;
    }}
//
//    public ChipNavigationBar getMenu(View v) {
//        menu = v.findViewById(R.id.pro_menu);
//        menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int i) {
//                switch (i){
//
//                    case (R.id.profile_edit):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.fragment_menu_container,new AddzoneFragment(),"")
//                                .commit();
//                        break;
//                    case (R.id.profile_position):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.fragment_menu_container,new PartageFragment(),"")
//                                .commit();
//                        break;
//                }
//            }
//        });
//        return menu;
//    }
//}
