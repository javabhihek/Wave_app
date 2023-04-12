package com.example.zoom_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signup extends AppCompatActivity {
    EditText edt_nameup,edt_emailup, edt_passup,edt_repassup;
    EditText edt_mobup,edt_dobup,edt_genderup;
    Button btn_signup;
    CheckBox chk_termsup;

    TextView txt_login;

    String name, email ,pass, repass,mobile,gender, dob;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(signup.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage(" We are Creating Account");

        chk_termsup = findViewById(R.id.chk_termsup);
        edt_nameup = findViewById(R.id.edt_nameup);
        edt_emailup = findViewById(R.id.edt_emailup);
        edt_passup = findViewById(R.id.edt_passup);
        edt_repassup = findViewById(R.id.edt_repassup);
        edt_mobup = findViewById(R.id.edt_mobup);
        edt_dobup = findViewById(R.id.edt_dobup);
        edt_genderup = findViewById(R.id.edt_genderup);

        btn_signup = findViewById(R.id.btn_signup);

        txt_login = findViewById(R.id.txt_login);
        chk_termsup.setOnClickListener((view -> {
            startActivity(new Intent(signup.this, Terms_Condition.class));
        }));

        btn_signup.setOnClickListener((view -> { validateUser(); }));

        txt_login.setOnClickListener((view ->{
         startActivity(new Intent(signup.this,Signin.class));
        }));

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() !=null){
            startActivity(new Intent(this,Welcome_page.class));
            finish();
        }
    }

    private void validateUser() {
        name=edt_nameup.getText().toString();
        email=edt_emailup.getText().toString();
        pass=edt_passup.getText().toString();
        repass=edt_repassup.getText().toString();
        mobile=edt_mobup.getText().toString();
        gender=edt_genderup.getText().toString();
        dob=edt_dobup.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(this, "Please enter name.", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter email id.", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty()) {
            Toast.makeText(this, "Please enter passowrd.", Toast.LENGTH_SHORT).show();
        } else if (repass.isEmpty()) {
            Toast.makeText(this, "Please enter re-password", Toast.LENGTH_SHORT).show();
        } else if (mobile.isEmpty()) {
            Toast.makeText(this, "enter mobile no.", Toast.LENGTH_SHORT).show();
        } else if (gender.isEmpty()) {
            Toast.makeText(this, "Please enter gneder(M/F)", Toast.LENGTH_SHORT).show();
        } else if (dob.isEmpty()) {
            Toast.makeText(this, "Please enter dob(DD/MM/YYYY)", Toast.LENGTH_SHORT).show();
        } else {
            registerUser();
            progressDialog.show();
        }
    }

    private void registerUser() {
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            updateUser();
                        }else {
                            Toast.makeText(signup.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void updateUser() {
                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        auth.getCurrentUser().updateProfile(changeRequest);
                        auth.signOut();
                        openLogin();
                    }

                    private void openLogin() {
                        startActivity(new Intent(signup.this,Signin.class));
                        finish();
                    }
                });
    }

}