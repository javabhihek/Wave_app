package com.example.zoom_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForotScreen extends AppCompatActivity {

    Button btn_forgot;
    EditText edt_emailforgot;
    TextView txt_login;
    String email;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forot_screen);
        auth=FirebaseAuth.getInstance();

        txt_login=findViewById(R.id.txt_login);
        btn_forgot=findViewById(R.id.btn_forgot);
        edt_emailforgot=findViewById(R.id.edt_emailforgot);

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData() {
        email=edt_emailforgot.getText().toString();
        if (email.isEmpty()){
            edt_emailforgot.setError("Required");
        }
        else{
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForotScreen.this, "check your email.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForotScreen.this,Signin.class));
                            finish();
                        }else {
                            Toast.makeText(ForotScreen.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}