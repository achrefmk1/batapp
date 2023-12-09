package com.example.batapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.batapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Connexion extends AppCompatActivity {
    private TextView error_msg;
    private EditText user;
    private EditText pass;
    private long backtime;
    private Toast backtoast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connexion);
        this.user = findViewById(R.id.username);
        this.pass = findViewById(R.id.password);

    }


    public void actionLogin (View view) {

        LoginUser();



    }

    public void actionSignup (View view) {

        Intent obj = new Intent(Connexion.this,Signup_Activity.class);
        startActivity(obj);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {


        if (backtime + 2000 > System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backtoast = Toast.makeText(getBaseContext(),"Press Again To exit",Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backtime = System.currentTimeMillis();
    }

    private void LoginUser(){

        final String username = user.getEditableText().toString();
        String password = pass.getEditableText().toString();

        if ((!username.isEmpty())&&(!password.isEmpty())){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Connexion.this, "Hello ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Connexion.this,HomeActivity.class));
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("failed task ",e.getMessage());
                    Toast.makeText(Connexion.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (username.isEmpty()){
            Toast.makeText(this, "Username is Empty", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
        }

    }
}




