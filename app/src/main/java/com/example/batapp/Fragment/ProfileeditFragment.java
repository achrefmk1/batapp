package com.example.batapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.batapp.Model.UserModel;
import com.example.batapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

public class ProfileeditFragment extends Fragment {

    private TextView username;
    private TextView email;
    private TextView phone;
    private String id;
    private Array datas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profileedit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.tv_username);
        email = view.findViewById(R.id.tv_email);
        phone = view.findViewById(R.id.tv_phone);

        id = FirebaseAuth.getInstance().getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/Users/"+id);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data ",dataSnapshot.toString());
                UserModel get_values = dataSnapshot.getValue(UserModel.class);

                if (get_values != null){
                    username.setText(get_values.getUser());
                    email.setText(get_values.getEmail());
                    phone.setText(get_values.getPhone());
                }
                else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
