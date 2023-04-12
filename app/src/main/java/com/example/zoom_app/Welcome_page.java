package com.example.zoom_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome_page extends AppCompatActivity {
    Button btn_joinmain;
    Button btn_sidemenu;

    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        btn_sidemenu=findViewById(R.id.btn_sidemenu);
        btn_joinmain=findViewById(R.id.btn_join);

        btn_sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Welcome_page.this,sidemenu.class);
                startActivity(i);
            }
        });

        btn_joinmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Welcome_page.this,meeting_screen.class);
                startActivity(i);
            }
        });


    }
    protected void onStart() {
        super.onStart();
        if (user == null){
            startActivity(new Intent(this,Signin.class));
            finish();
        }
    }
}