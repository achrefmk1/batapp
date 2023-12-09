package com.example.batapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.batapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000; //splash screen will be shown for 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (FirebaseAuth.getInstance().getUid() == null){
                    startActivity(new Intent(StartActivity.this,Connexion.class));
                }
                else {
                    startActivity(new Intent(StartActivity.this,HomeActivity.class));
                }

                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
