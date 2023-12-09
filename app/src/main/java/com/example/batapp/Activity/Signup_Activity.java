package com.example.batapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.batapp.Fragment.HomeFragment;
import com.example.batapp.Model.UserModel;
import com.example.batapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText password;
    private CheckBox terms;
    private Button login;

    public Button save;

    private FirebaseAuth mAuth;
    private  String adresse_email;
    private String mot_de_passe;


    private TextWatcher testtype = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String adresse = adresse_email;
            //save.setEnabled(!adresse.isEmpty());
            Toast.makeText(Signup_Activity.this, "here text changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_batapp);
        this.username =   findViewById(R.id.username);
        this.email =   findViewById(R.id.email_adresse);
        this.phone =   findViewById(R.id.phone);
        this.password =  findViewById(R.id.new_password);
        this.terms =  findViewById(R.id.checked_terms);
        this.login = findViewById(R.id.login_button);
        this.save = findViewById(R.id.button_button);
        login.setOnClickListener(this);

//        this.save.setEnabled(false);


        adresse_email = email.getEditableText().toString();
        mot_de_passe = password.getEditableText().toString();

        this.email.addTextChangedListener(testtype );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }



//    public void actionSignup (View view){
//
//        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
//        if ((adresse_email.equals("admin"))
//                && (mot_de_passe.equals("admin"))
//                && (this.terms.isChecked())) {
//            registerUser();
//            //startActivity(new Intent(Signup_Activity.this, Connexion.class));
//            Toast.makeText(Signup_Activity.this, "hello User",Toast.LENGTH_LONG).show();
//
//        }
//        else if (this.terms.isChecked() == false){
//            Toast.makeText(Signup_Activity.this, "Check Term of Use",Toast.LENGTH_LONG).show();
//        }
//        else if ((this.terms.isChecked()) && ((adresse_email != "admin")
//                || (mot_de_passe !="admin"))){
//            Toast.makeText(Signup_Activity.this, "Email or Passsword Incorrect",Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.login_button):
                Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Signup_Activity.this,Connexion.class));
                break;
        }
    }


    private void registerUser(){

        adresse_email = email.getEditableText().toString();
        mot_de_passe = password.getEditableText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(adresse_email,mot_de_passe)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Signup_Activity.this, "User Saved", Toast.LENGTH_SHORT).show();
                            UserdataSave();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("failed task ",e.getMessage());
                Toast.makeText(Signup_Activity.this, "Failed To Add User", Toast.LENGTH_SHORT).show();
            }
        });
    }

private void UserdataSave(){

    String email_value = email.getEditableText().toString();
    String user_value =username.getEditableText().toString();
    String phone_value =phone.getEditableText().toString();

    String id_value = FirebaseAuth.getInstance().getUid();

    UserModel zone = new UserModel(id_value,user_value,email_value,phone_value);

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/Users/"+id_value);

    reference.setValue(zone).addOnCompleteListener(this, new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            Toast.makeText(Signup_Activity.this, "Saving Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Signup_Activity.this,HomeActivity.class));
        }
    }).addOnFailureListener(this, new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(Signup_Activity.this, "Adding Zone Failed", Toast.LENGTH_SHORT).show();
        }
    });

}
}
