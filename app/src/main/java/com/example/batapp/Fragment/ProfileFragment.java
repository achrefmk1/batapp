package com.example.batapp.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.batapp.Activity.Connexion;
import com.example.batapp.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

Button profile,position,logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_profile, container, false);

        this.profile = v.findViewById(R.id.btn_profil);
        this.position = v.findViewById(R.id.btn_Positions);
        this.logout = v.findViewById(R.id.logout);
                getChildFragmentManager().
                beginTransaction().
                replace(R.id.profile_menu,new ProfileeditFragment(),"")
                .commit();
        this.profile.setTextColor(getResources().getColor(R.color.pink_dark,null));
        this.position.setTextColor(getResources().getColor(R.color.black,null));
        this.position.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.black,null)));
        this.profile.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink_dark,null)));
        this.profile.setBackground(getResources().getDrawable(R.color.whitesmoke));
        this.position.setBackground(null);

    // Inflate the layout for this fragment
//
//        menu = v.findViewById(R.id.pro_menu);
//        menu.setItemEnabled(R.id.profile_edit,true);
//        menu.setItemSelected(R.id.profile_edit,true);
//        getChildFragmentManager().
//                beginTransaction().
//                replace(R.id.profile_menu,new ProfileeditFragment(),"")
//                .commit();
//
//        menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int i) {
//                switch (i){
//
//                    case (R.id.profile_edit):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.profile_menu,new ProfileeditFragment(),"")
//                                .commit();
//                        break;
//                    case (R.id.profile_position):
//                        getChildFragmentManager().
//                                beginTransaction().
//                                replace(R.id.profile_menu,new PositionsFragment(),"")
//                                .commit();
//                        break;
//                }
//            }
//        });


        this.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setTextColor(getResources().getColor(R.color.pink_dark,null));
                position.setTextColor(getResources().getColor(R.color.black,null));
                profile.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink_dark,null)));
                position.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.black,null)));
                profile.setBackground(getResources().getDrawable(R.color.whitesmoke));
                position.setBackground(null);
                getChildFragmentManager().
                        beginTransaction().
                        replace(R.id.profile_menu,new ProfileeditFragment(),"")
                        .commit();
            }
        });

        this.position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position.setTextColor(getResources().getColor(R.color.pink_dark,null));
                profile.setTextColor(getResources().getColor(R.color.black,null));
                profile.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.black,null)));
                position.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink_dark,null)));
                position.setBackground(getResources().getDrawable(R.color.whitesmoke));
                profile.setBackground(null);
                getChildFragmentManager().
                        beginTransaction().
                        replace(R.id.profile_menu,new PositionsFragment(),"")
                        .commit();
            }
        });
    return v;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if (FirebaseAuth.getInstance().getUid()== null){
                    startActivity(new Intent(getActivity(), Connexion.class));
                    getActivity().finish();
                }
            }
        });




    }
}